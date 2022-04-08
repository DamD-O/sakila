<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();

	//1. customer별 총 amount
	List<Map<String,Object>> amountByCoustomer = statsDataDao.amountByCoustomer();
	//2.rental_rate별 영화개수
	List<Map<String,Object>> filmCountByRentalRate =statsDataDao.filmCountByRentalRate();
	//3.rating별 영화 개수
	List<Map<String,Object>> filmCountByRating =statsDataDao.filmCountByRating();
	//4.language별 영화 개수
	List<Map<String,Object>> filmCountByLanguage =statsDataDao.filmCountByLanguage();
	//5.
	List<Map<String, Object>> filmCountByLength =statsDataDao.filmCountByLength();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통계</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h3 class="text-dark">amountByCoustomer(지불액이 $180 이상)</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>고객아이디</th>
			<th>고객이름</th>
			<th>총 지불액</th>
		</tr>
	<%
		for(Map<String,Object> m : amountByCoustomer){
	%>
		<tr>
			<td><%=m.get("customerId") %></td>
			<td><%=m.get("name") %></td>
			<td><%=m.get("total") %></td>
		</tr>
	<% 		
		}
	%>
	</table>
	<p><h3 class="text-dark">rental_rate별 영화개수</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>대여료</th>
			<th>총 개수</th>
		</tr>
		<%
		for(Map<String,Object> m : filmCountByRentalRate){
		%>
		<tr>
			<td><%=m.get("rentalRate") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<% 		
			}
		%>
	</table>
	
	<p><h3 class="text-dark">rating별 영화 개수</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>등급</th>
			<th>총 개수</th>
		</tr>
		<%
		for(Map<String,Object> m : filmCountByRating){
		%>
		<tr>
			<td><%=m.get("rating") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<% 		
			}
		%>
	</table>
	
	<p><h3 class="text-dark">lanauge별 영화개수</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>언어</th>
			<th>총 개수</th>
		</tr>
		<%
		for(Map<String,Object> m : filmCountByLanguage){
		%>
		<tr>
			<td><%=m.get("language") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<% 		
			}
		%>
	</table>
	
	<p><h3 class="text-dark">Length별 영화개수(구간)</h3></p>
	<table  class="table table-bordered table-sm">
		<tr>
			<th>영화 시간(구간)</th>
			<th>총 개수</th>
		</tr>
		<%
		for(Map<String,Object> m : filmCountByLength){
		%>
		<tr>
			<td><%=m.get("length") %></td>
			<td><%=m.get("cnt") %></td>
		</tr>
		<% 		
			}
		%>
	</table>
</div>
</body>
</html>