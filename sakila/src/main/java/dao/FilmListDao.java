package dao;
import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.*;
public class FilmListDao {
//페이징
	//영화 가격
		public List<Double> selectfilmPriceList(){
			List<Double> list = new ArrayList<Double>();
			Connection conn = null; //연결변수
			PreparedStatement stmt = null; //call변수
			ResultSet rs = null; //결과저장 변수
			conn = DBUtil.getConnection(); //db연결호출
			String sql="SELECT DISTINCT price FROM film_list ORDER BY price";
			try {
				stmt =conn.prepareStatement(sql);
				rs =stmt.executeQuery();
				while(rs.next()) {
					  list.add(rs.getDouble("price"));
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
				f.setFid(rs.getInt("filmId"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("categoryName"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actorName"));
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
	
	 //영화검색
	public List<FilmList> selectFilmListSearch(int beginRow, int rowPerPage, String category, String rating, double price, int length, String title, String actor) {		
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			// 동적쿼리
			String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
			if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += " ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { // length만 입력되었다
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql += " AND price=? ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} // 13(+알파)개의 쿼리 분기
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmList f = new FilmList();
				f.setFid(rs.getInt("fid"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setCategory(rs.getString("category"));
				f.setPrice(rs.getDouble("price"));
				f.setLength(rs.getInt("length"));
				f.setRating(rs.getString("rating"));
				f.setActors(rs.getString("actors"));
				list.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
