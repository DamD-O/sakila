package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.SalesByStore;

public class SalesByStoreDao {
	public List<SalesByStore> selectSalseStoreList(){
		List<SalesByStore> list = new ArrayList<SalesByStore>(); //객체생성 : list저장
		
		//DB자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//연결할 db호출
		conn=DBUtil.getConnection();
		//쿼리 생성
		String sql ="SELECT store, manager,total_sales totalSales FROM sales_by_store ORDER BY totalSales";
		//예외처리
		try {
			stmt = conn.prepareStatement(sql); //쿼리 실행
			rs = stmt.executeQuery(); //쿼리 실행결과 저장
			while(rs.next()) {
				//다음행이 있으면
				SalesByStore s = new SalesByStore(); //객체생성
				s.setStore(rs.getString("store"));
				s.setManager(rs.getString("manager"));
				s.setTotalSales(rs.getDouble("totalSales"));
				list.add(s); //list에 추가
				
				//debugging
				System.out.println(rs.getString("store"));
				System.out.println(rs.getString("manager"));
				System.out.println(rs.getDouble("totalSales"));
			}
		} catch (SQLException e) {
			//예외 호출
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
