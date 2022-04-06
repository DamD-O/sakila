package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;
public class FilmListDao {
//페이징
	
	//목록 가져오기
	public List<FilmList> selectFilmList(int beginRow, int rowPerPage ){
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		conn = DBUtil.getConnection(); //직접적으로 가져오지 않고 호출해서 가져온다.
		String sql = "SELECT FID filmId, title, description, category categoryName, price, length, rating, actors actorName from film_list ORDER BY FID LIMIT ?,?";
		PreparedStatement stmt =null;
		ResultSet rs =null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			//list.add
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFilmId(rs.getInt("filmId"));
				f.setTitle(rs.getString("tilte"));
				f.setDescription(rs.getString("description"));
				f.setCategoryName(rs.getString("categoryName"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActorName(rs.getString("actorName"));
				list.add(f);
				//디버깅
				System.out.println(rs.getInt("filmId"));
				System.out.println(rs.getInt("filmId"));
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
		String sql = "select count(*) cnt from film_list";
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
