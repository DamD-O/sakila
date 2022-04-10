package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;

public class RentalDao {
	public List<Map<String, Object>> selectRentalSearchList(int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
	
		//db연결
		try {
			conn = DBUtil.getConnection();
			//쿼리
			String sql = "SELECT r.rental_id rentalId, r.rental_date rentalDate, r.inventory_id inventoryId, r.customer_id customerId, "
					+ "	 CONCAT(c.first_name,' ',c.last_name) customerName, r.rental_date rentalDate, "
					+ "  r.return_date returnDate, "
					+ "  s.store_id storeId, r.staff_id staffId,r.last_update lastUpdate,  i.film_id filmId, f.title"
					+ "	 FROM rental r INNER JOIN customer c"
					+ "	 ON r.customer_id = c.customer_id"
					+ "	 INNER JOIN staff s"
					+ "	 ON r.staff_id = s.staff_id"
					+ "	 INNER JOIN inventory i"
					+ "	 ON r.inventory_id = i.inventory_id"
					+ "	 INNER JOIN film f"
					+ "	 ON i.film_id = f.film_id"
					+"	 WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";
	
			//디버깅
			System.out.println("storeId : "+storeId);
			System.out.println("customerName : "+customerName);
			System.out.println("beginDate : "+beginDate);
			System.out.println("endDate : "+endDate);
			System.out.println("beginRow : "+beginRow);
			System.out.println("rowPerPage : "+rowPerPage);

			if(storeId == -1 && beginDate.equals("") && endDate.equals("")) {  //모두 선택안됨
				sql += " ORDER BY r.rental_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,"%"+customerName+"%" );
				stmt.setInt(2, beginRow);
		        stmt.setInt(3, rowPerPage);

			} else if(storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { //모두선택

				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY r.rental_id LIMIT ?,?";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1,"%"+customerName+"%" );
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
		        stmt.setInt(6, rowPerPage);

			} else if(storeId != -1 && beginDate.equals("") && endDate.equals("")) { // storeId만 입력

				sql += " AND s.store_id=? ORDER BY r.rental_id LIMIT ?,?";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1,"%"+customerName+"%" );
				stmt.setInt(2, storeId);
				stmt.setInt(3, beginRow);
		        stmt.setInt(4, rowPerPage);


			} else if(storeId == -1 && !beginDate.equals("") && !endDate.equals("")) { // beginDate,endDate만 입력

				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY r.rental_id LIMIT ?,?";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1,"%"+customerName+"%" );
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
		        stmt.setInt(5, rowPerPage);
			}

			rs = stmt.executeQuery();

			while(rs.next()){ //다음행이 있을때 값가져오기
				Map<String, Object> map = new HashMap<>();
				map.put("rentalId", rs.getInt("rentalId"));
				map.put("rentalDate", rs.getString("rentalDate"));
				map.put("inventoryId", rs.getString("inventoryId"));
				map.put("customerId", rs.getInt("customerId"));
				map.put("returnDate", rs.getString("returnDate"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				map.put("customerName", rs.getString("customerName"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("filmId", rs.getInt("filmId"));
				map.put("title", rs.getString("title"));
				list.add(map);

			}
				} catch (Exception e) {
				e.printStackTrace(); //예외 발생
			 }
			return list;
			}

			public int totalRow (int storeId, String customerName, String beginDate, String endDate){ //전체행
			int count = 0;
			//db자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			conn = DBUtil.getConnection(); //db호출

			try {
				String sql = " SELECT"
						+ "  COUNT(*) cnt"
						+ "  FROM rental r INNER JOIN customer c"
						+ "  ON r.customer_id = c.customer_id"
						+ "  INNER JOIN staff s"
						+ "  ON r.staff_id = s.staff_id"
				        + "  INNER JOIN inventory i"
				        + "  ON r.inventory_id = i.inventory_id"
				        + "  INNER JOIN film f"
				        + "  ON i.film_id = f.film_id"
						+ "  WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";


				if(storeId == -1 && beginDate.equals("") && endDate.equals("")) {  // 모두 선택안됨
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");


				} else if(storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { // 모두 선택

					sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";

					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");
					stmt.setInt(2, storeId);
					stmt.setString(3, beginDate);
					stmt.setString(4, endDate);

				} else if(storeId != -1 && beginDate.equals("") && endDate.equals("")) { // storeId만 선택

					sql += " AND s.store_id=?";

					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");
					stmt.setInt(2, storeId);


				} else if(storeId == -1 && !beginDate.equals("") && !endDate.equals("")) { // beginDate, endDate만 선택

					sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,"%"+customerName+"%");
					stmt.setString(2, beginDate);
					stmt.setString(3, endDate);
				}

				rs = stmt.executeQuery();

				while(rs.next()) {
					count = rs.getInt("cnt");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 데이터베이스 자원 반환
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return count;
		
	}

}
