package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;
public class NicerButSlowerFilmListDao {
	//페이징 구현
	//매점 영화정보
	public List<NicerButSlowerFilmList> selectNicerFilmList(int beginRow, int rowPerPage ){
		List<NicerButSlowerFilmList> list = new ArrayList<NicerButSlowerFilmList>(); //객체생성
		
		//DB구현
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs =null;
		
		//db연결 호출
		conn = DBUtil.getConnection();
		//쿼리문 생성
		String sql = "SELECT FID filmId, title, description, category categoryName, price, length, rating, actors actorName from nicer_but_slower_film_list ORDER BY FID LIMIT ?,?";
		
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			//list.add
			while(rs.next()) {
				NicerButSlowerFilmList n = new NicerButSlowerFilmList();
				n.setFilmId(rs.getInt("filmId"));
				n.setTitle(rs.getString("title"));
				n.setDescription(rs.getString("description"));
				n.setCategory(rs.getString("categoryName"));
				n.setPrice(rs.getDouble("price"));
				n.setLength(rs.getInt("length"));
				n.setRating(rs.getString("rating"));
				n.setActors(rs.getString("actorName"));
				list.add(n);
				//디버깅
				System.out.println(rs.getInt("filmId"));
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("description"));
				System.out.println(rs.getString("categoryName"));
				System.out.println(rs.getDouble("price"));
				System.out.println(rs.getString("rating"));
				System.out.println(rs.getString("actorName"));
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
	
	//전체행
	public int totalRow() {
		int totalRow =0; //전체행의 개수
		//db자원 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection(); //DB호출
		String sql = "select count(*) cnt from nicer_but_slower_film_list";
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
