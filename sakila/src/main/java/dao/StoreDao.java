package dao;

import java.util.*;
import java.sql.*;
public class StoreDao {
	public List<Map<String, Object>> selctStoreList(){ //다형성 위한
		//ArrayList는 인터페이스의 구현체 중 하나이다.(List가 부모이다)
		//HasMap은 Map 인터페이스의 구현체 중 하나이다
		List<Map<String, Object>> list = new ArrayList<>(); // <>생략하면 앞에꺼랑 같음
		
		//예외처리
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) { //드라이버가 없으면
			e.printStackTrace(); //그대로 출력
			System.out.println("Class.forName() 실행중 ClassNotFoundException발생");
		}
		//SQL 예외발생  
		Connection conn = null; //conn.close할때 오류생김?(conn을 밖으로 꺼내서 밑에서도 쓸 수 있게)
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila\",\"root\",\"java1234");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DriverManager.getConnection() 실행중 SQL Eception발생");
		}
		
		PreparedStatement stmt = null;
		//예외발생 
	      try {
	         stmt = conn.prepareStatement("");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("Connection.prepareStatement() 실행 중 SQLException 발생");
	      }
	      //예외발생, stmt try블럭 밖에 꺼내서 오류안나게? 
	      try { //list 완성
	         ResultSet rs = stmt.executeQuery();
	         while(rs.next()) { //다음행에 있으면
	            list.add(null); //null 추가?
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("Statement.executeQuery() 실행 중 SQLException 발생");
	      }   
	      //예외발생
	      try {
	         conn.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("Connection.close() 실행 중 SQLException 발생");
	      }
	      return list;
	   }
	}