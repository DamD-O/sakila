package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;
import vo.FilmList;

public class CategoryDao {
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
   
   //전체행
 	public int totalRow(String category, String rating, double price, int length, String title, String actor) {
 		int totalRow =0; //전체행의 개수
 		//db자원 준비
 		Connection conn = null;
 		PreparedStatement stmt = null;
 		ResultSet rs = null;
 		
 		conn = DBUtil.getConnection(); //DB호출
 		String sql = "select count(*) cnt from film_list WHERE title LIKE ? AND actors LIKE ?"; 
 		try {
 			//아무것도 선택안됬을때
 			if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
 				stmt = conn.prepareStatement(sql);
 				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
 			}
 			//2) length만 입력되었다
 			else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) {
 				if(length == 0) {
					sql += " AND length<60";
				} else if(length == 1) {
					sql += " AND length>=60";
				}
 			}
 			//3)price만 입력
 			else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) { 
				sql += " AND price=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
			} 
 			//4)rating만 선택
			else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
				sql += " AND rating=? ";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
			}
 			//5)카테고리만 선택
			else if(!category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += " AND category=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
			}
 			
 			//6)length + price만 선택
			else if(category.equals("") && rating.equals("") && price!=-1 && length !=-1) {
				if(length == 0) {
					sql += " AND price=? AND length<60";
				} else if(length == 1) {
					sql += " AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
			}
			//7)length + rating
			else if(category.equals("") && !rating.equals("") && price==-1 && length !=-1) {
				if(length == 0) {
					sql += " AND rating=? AND length<60";
				} else if(length == 1) {
					sql += " AND rating=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
			}
 			//8)length + category
			else if(!category.equals("") && rating.equals("") && price==-1 && length !=-1) {
				if(length == 0) {
					sql += " AND category=? AND length<60";
				} else if(length == 1) {
					sql += " AND category=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
			}
 			//9)length + rating + price
			else if(category.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if(length == 0) {
					sql += " AND rating=? AND price=? AND length<60";
				} else if(length == 1) {
					sql += " AND rating=? AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
				
			}
			//10)length + rating + category
			else if(!category.equals("") && !rating.equals("") && price ==-1 && length !=-1) {
				if(length == 0) {
					sql += " AND rating=? AND category=? AND length<60";
				} else if(length == 1) {
					sql += " AND rating=? AND category=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, rating);
				stmt.setString(4, category);
			}
			//11)length + price + category
			else if(!category.equals("") && rating.equals("") && price !=-1 && length !=-1) {
				if(length == 0) {
					sql += " AND price=? AND category=? AND length<60";
				} else if(length == 1) {
					sql += " AND price=? AND category=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setString(4, category);
			
			}
 			//12)price +rating
			else if(category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND price=?  AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setString(4, rating);
			}
			//13)price + category
			else if(!category.equals("") && rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND price=?  AND category=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setString(4, category);
			}
 			//14)price+rating + category
			else if(!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND price=?  AND category=? AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setString(4, category);
				stmt.setString(5, rating);
			}
			//15)rating + category
			else if(!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND category=?  AND rating=? ";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
			}
			//16)모두 다 선택
			else if(!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if(length == 0) {
					sql += " AND price=? AND category=? AND rating=?  AND length<60";
				} else if(length == 1) {
					sql += " AND price=? AND category=? AND rating=? AND length>=60 ";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setString(4, category);
				stmt.setString(5, rating);
			}
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