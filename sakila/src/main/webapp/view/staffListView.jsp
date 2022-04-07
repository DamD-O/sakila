<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	//dao객체 생성, 메소드 호출
	StaffListViewDao staffListViewDao = new StaffListViewDao();
	//dao에 생성된 메소드 호출
	List<StaffListView> list = staffListViewDao.selectStaffListView();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StaffListView</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark table-responsive">
<ul class="nav justify-content-end bg-dark">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">SalesByStore</h1></p>
	<table class="table table-striped ">
	<thead>
			<th>staffId</th>
			<th>name</th>
			<th>address</th>
			<th>postalCode</th>
			<th>phone</th>
			<th>city</th>
			<th>country</th>
			<th>storeID</th>
		</thead>
		<tbody>
			<%
				for(StaffListView s :list){
			%>
				<tr>
				<td><%=s.getStaffId()%></td>
				<td><%=s.getName()%></td>
				<td><%=s.getAddress()%></td>
				<td><%=s.getPostalCode()%></td>
				<td><%=s.getPhone()%></td>
				<td><%=s.getCity()%></td>
				<td><%=s.getCountry()%></td>
				<td><%=s.getStoreId()%></td>
				</tr>
			<%		
				}
			%>
		</tbody>
	</table>
</div>
</body>
</html>