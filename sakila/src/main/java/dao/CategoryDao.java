package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;
import vo.FilmList;

public class SearchDao {
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
	//영화 카테고리
   public List<Category> selectCategoryList() {
      List<Category> list = new ArrayList<Category>();
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      conn = DBUtil.getConnection();
      String sql = "SELECT category_id categoryId, name, last_update lastUpdate FROM category";
      try {
         stmt = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         while(rs.next()) {
            Category c = new Category();
            c.setCategoryId(rs.getInt("categoryId"));
            c.setName(rs.getString("name"));
            c.setLastUpdate(rs.getString("lastUpdate"));
            list.add(c);
         }
      } catch (SQLException e) {
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
   
   //영화검색
   public List<FilmList>  selectFilmSerach(String category, String rating, Double price, int length, String title, String actor ){
	   List<FilmList> list = new ArrayList<FilmList>();
	   String sql ="";
	   		if(category.equals("")&& rating.equals("") && price == -1 && length == -1) {
	   			sql = "SELECT * from film_list WHERE title LIKE ? AND actor LIKE ?";
	   		}else if(category.equals("") && rating.equals("")&& price == -1 && length!= -1) { //length만 입력됨.
	   			if(length ==0) {
	   				sql = "SELECT * from film_list WHERE title LIKE ? AND actors LIKE ? AND length < 60";
	   			}else if(length == 1){
	   				sql = "SELECT * from film_list WHERE title LIKE ? AND actors LIKE ? AND length >=60";
	   			}
	   		}else if(category.equals("")&& rating.equals("") && price != -1 && length == -1) {
	   			sql = "SELECT * from film_list WHERE title LIKE ? AND actors LIKE ? AND price = ? ";
	   		}
	   
	   return list;
	   
   }
}