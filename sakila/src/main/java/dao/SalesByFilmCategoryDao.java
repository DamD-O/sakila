package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;
public class SalesByFilmCategoryDao {
//페이징
	
	//list출력
	//메소드 생성
	public List<SalesByFilmCategory> selecByFilmList(int beginRow, int rowPerPage){
		List<SalesByFilmCategory> list = new ArrayList<SalesByFilmCategory>(); //list값 저장할 객체생성
		
		//DB구현
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//연결할 db호출
		conn = DBUtil.getConnection();
		//쿼리문 생성
		String sql="SELECT category, total_sales totalSales FROM sales_by_film_category ORDER BY total_sales LIMIT ?,?";
		//쿼리 실행
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs= stmt.executeQuery(); //쿼리 실행결과 저장
			
			//list에 추가
			while(rs.next()) {
				SalesByFilmCategory s = new SalesByFilmCategory();
				s.setCategory(rs.getString("category"));
				s.setTotalSales(rs.getDouble("totalSales"));
				list.add(s);
				
				//디버깅
				System.out.println(rs.getString("category") + " : 카테고리");
				System.out.println(rs.getDouble("totalSales")+ ": 카테고리 총 매출");
			}
		} catch (SQLException e) {
			//예외 호출
			e.printStackTrace();
		}finally {
			//db자원 종료
		}
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
		
	}
	//전체행
		public int totalRow() {
			int totalRow =0; //전체행의 개수
			//db자원 준비
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			conn = DBUtil.getConnection(); //DB호출
			String sql = "select count(*) cnt from sales_by_film_category";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				if(rs.next()) {
					totalRow = rs.getInt("cnt");
				}
			} catch (SQLException e) {
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
			
			return totalRow;
		}
}
