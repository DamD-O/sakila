package dao;
import java.sql.*;
import java.util.*;

import util.DBUtil;

public class StaffDao {
	public List<Map<String, Object>> selctStaffList(){ //다형성, List라는 인터페이스로 받음
		//ArrayList는 인터페이스의 구현체 중 하나이다.(List가 부모이다)
		//HasMap은 Map 인터페이스의 구현체 중 하나이다
		List<Map<String, Object>> list = new ArrayList<>(); //다형성
		//db자원 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//예외처리
		try {
			Class.forName("org.mariadb.jdbc.Driver"); //드라이버 생성
			conn = DBUtil.getConnection();
			String sql = "SELECT s1.staff_id staffId, "
					+ " concat(s1.first_name,' ', s1.last_name) staffName,"
					+ " concat(a.address, IFNULL(a.address2,' '), district) staffAddress, "
					+ " s1.picture, s1.email, s1.store_id storeId, s1.active,"
					+ " s1.username userName, s1.last_update lastUpdate"
					+ " FROM staff s1"
					+ " INNER JOIN address a"
					+ " INNER JOIN store s2"
					+ " ON s1.address_id = a.address_id"
					+ " AND s1.store_id = s2.store_id";
			stmt = conn.prepareStatement(sql); //sql 실행
			rs = stmt.executeQuery(); //sql실행결과 저장
			
			while(rs.next()) { //다음행이 있으면 데이터 추가
				Map<String, Object> map = new HashMap<>(); //다형성
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("picture", rs.getString("picture"));
				map.put("email", rs.getString("email"));
				map.put("storeId", rs.getInt("storeId"));
				map.put("active", rs.getInt("active"));
				map.put("userName", rs.getString("userName"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				list.add(map);
			}
		} catch (Exception e) { //ClassNotFoundException, SQLException 두개의 예외를 부모타입 Exception으로 처리 -> 다형성
			e.printStackTrace();
			System.out.println("예외발생");
		} 
		finally { //위의 catch절과 상관없음
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
			StaffDao dao = new StaffDao();
			List<Map<String, Object>> list = dao.selctStaffList();
			for(Map m :list) {
				System.out.println("데이터출력? 디버깅");
				System.out.println(m.get("staffId")+ " ");
				System.out.println(m.get("staffName")+ " ");
				System.out.println(m.get("staffAddress")+ " ");
				System.out.println(m.get("picture")+ " ");
				System.out.println(m.get("email")+ " ");
				System.out.println(m.get("storeId")+ " ");
				System.out.println(m.get("active")+ " ");
				System.out.println(m.get("userName")+ " ");
				System.out.println(m.get("lastUpdate")+ " ");
				System.out.println("테스트 끝");
		}
	}
}
