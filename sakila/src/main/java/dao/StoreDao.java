package dao;
import java.util.*;
import util.DBUtil;

import java.sql.*;
public class StoreDao {
	//store목록
	public List<Map<String, Object>> selctStoreList(){ //다형성, List라는 인터페이스로 받음
		//ArrayList는 인터페이스의 구현체 중 하나이다.(List가 부모이다)
		//HasMap은 Map 인터페이스의 구현체 중 하나이다
		List<Map<String, Object>> list = new ArrayList<>(); // <>생략하면 앞에꺼랑 같음, 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//예외처리
		try {
			//Class.forName("org.mariadb.jdbc.Driver"); //드라이버 생성
			//conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			conn = DBUtil.getConnection();
			String sql = "SELECT s1.store_id storeId, s1.manager_staff_id staffId,"
					+ " concat(s2.first_name,' ', s2.last_name) staffName,"
					+ " s1.address_id addressId, concat(a.address, IFNULL(a.address, ' '), district) storeAddress, "
					+ " s1.last_update lastUpdate"
					+ " FROM store s1"
					+ " INNER JOIN staff s2"
					+ " INNER JOIN address a"
					+ " ON s1.manager_staff_id = s2.staff_id"
					+ " AND s1.address_id = a.address_id";
			stmt = conn.prepareStatement(sql); //sql 실행
			rs = stmt.executeQuery(); //sql실행결과 저장
			while(rs.next()) { //다음행이 있으면 데이터 추가
				Map<String, Object> map = new HashMap<>(); //다형성
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("storeAddress", rs.getString("storeAddress"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
		} catch (Exception e) { //ClassNotFoundException, SQLException 두개의 예외를 부모타입 Exception으로 처리 -> 다형성
			e.printStackTrace(); //그대로 출력
			System.out.println("예외발생");
			
		}finally { //위의 catch절과 상관없음
			//DB연결에 사용한 자원 해지 ->try절에서 예외가 발생하면 자원해지 못한 상태에서 코드가 종료, finally절 필요
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	      return list;
	}
	//테스트 해보기 : selectStoreList() //단위테스트
	public static void main(String[]args) {
		StoreDao dao = new StoreDao();
		List<Map<String, Object>> list = dao.selctStoreList();
		for(Map m :list) {
			System.out.println("데이터출력? 디버깅");
			System.out.println(m.get("storeId")+ " ");
			System.out.println(m.get("staffId")+ " ");
			System.out.println(m.get("staffName")+ " ");
			System.out.println(m.get("addressId")+ " ");
			System.out.println(m.get("storeAddress")+ " ");
			System.out.println(m.get("lastUpdate")+ " ");
			System.out.println("테스트 끝");
		}
	}
	//storeId
	public List<Integer> selectStoreIdList(){
		List<Integer> list = new ArrayList<Integer>();
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Integer count = 0;
		//db호출
		conn=DBUtil.getConnection();
		//쿼리작선 및 실행
		try {
			String sql = " SELECT store_id storeId From store;";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				count = rs.getInt("storeId");
				list.add(count);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
		
	}
}