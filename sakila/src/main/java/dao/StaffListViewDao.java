package dao;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.*;
import java.sql.*;

public class StaffListViewDao {
	public List<StaffListView> selectStaffListView(){
		List<StaffListView> list = new ArrayList<StaffListView>(); //객체생성
		
		//db자원 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//db호출
		conn = DBUtil.getConnection();
		//쿼리생성
		String sql ="SELECT ID staffId, name, address, `zip code` postalCode, phone, city, country, SID storeId FROM staff_list order by ID";
		//쿼리 실행
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				StaffListView s = new StaffListView();
				s.setStaffId(rs.getInt("staffId"));
				s.setName(rs.getString("name"));
				s.setAddress(rs.getString("address"));
				s.setPostalCode(rs.getString("postalCode"));
				s.setPhone(rs.getString("phone"));
				s.setCity(rs.getString("city"));
				s.setCountry(rs.getString("country"));
				s.setStoreId(rs.getInt("storeID"));
				list.add(s);
				
				//디버깅
				System.out.println(rs.getInt("staffId"));
			}
		} catch (SQLException e) {
			//예외 호출
			e.printStackTrace();
		}finally {
			try {
				//db자원 종료
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
