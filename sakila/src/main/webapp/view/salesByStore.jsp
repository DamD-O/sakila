<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%

	//dao객체 생성, 메소드 호출
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	//dao에 생성된 메소드 호출
	List<SalesByStore> list = salesByStoreDao.selectSalseStoreList(); 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SalesByStore</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark table-responsive">
<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">SalesByStore</h1></p>
	<table class="table table-striped table-hover">
	<thead>
			<th>store</th>
			<th>manager</th>
			<th>totalSales</th>
		
		</thead>
		<tbody>
			<%
				for(SalesByStore s :list){
			%>
				<tr>
				<td><%=s.getStore()%></td>
				<td><%=s.getManager()%></td>
				<td><%=s.getTotalSales()%></td>
		
				</tr>
			<%		
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>