<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	StoreDao storeDao = new StoreDao(); //dao생성
	List<Map<String, Object>> list =  storeDao.selctStoreList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>storeList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">Store List</h1></p>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>storeId</th>
				<th>staffId</th>
				<th>staffName</th>
				<th>addressId</th>
				<th>storeAddress</th>
				<th>lastUpdate</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(Map m : list){
			%>
			<tr>
				<td><%=m.get("storeId")%></td>
				<td><%=m.get("staffId")%></td>
				<td><%=m.get("staffName")%></td>
				<td><%=m.get("addressId")%></td>
				<td><%=m.get("storeAddress")%></td>
				<td><%=m.get("lastUpdate")%></td>
			</tr>
			<% 
				}
			%>
		</tbody>
	</table>
</div>
	
</body>
</html>