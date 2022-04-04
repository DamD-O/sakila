package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.CustomerList;

public class CustomerListDao {
	//총 행수 
	public int totalRow() {
		//전체행의 개수
		int totalRow =0;
		//db자원 준비
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); //db연결호출
		String sql = "select count(*) cnt from customer_list";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(); //쿼리실행 저장
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return totalRow;
		
	}
	
	//목록
	public List<CustomerList> selectCustomerList(int beginRow, int rowPerPage){
		List<CustomerList> list = new ArrayList<CustomerList>();
		
		//db자원 준비
		Connection conn =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//DB연결 호출
		conn = DBUtil.getConnection();
		String sql ="select customer_id customerId, conact(first_name, ' ',last_name) name, address, postal_code zipCode, phone, city, country, active notes, store_id storeId from customer_list order by customer_id LIMIT ?,? ";
		
		try {
			stmt = conn.prepareStatement(sql); //쿼리실행
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery(); //실행결과 저장 
			while(rs.next()) {
				CustomerList c = new CustomerList(); //객체생성
				c.setCustomerId(rs.getInt("customerId"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPostalCode(rs.getString("postalCode"));
				c.setPhone(rs.getString("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setActive(rs.getInt("active"));
				c.setStoreId(rs.getInt("storeId"));
				list.add(c); //c에 가져온값 저장
				
				//디버깅
				System.out.println(rs.getInt("customerId"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("address"));
				System.out.println(rs.getString("postalCode"));
				System.out.println(rs.getString("phone"));
				System.out.println(rs.getString("city"));
				System.out.println(rs.getString("country"));
				System.out.println(rs.getInt("active"));
				System.out.println(rs.getInt("storeId"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
