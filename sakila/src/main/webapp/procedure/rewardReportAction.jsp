<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%
	//인코딩
	request.setCharacterEncoding("utf-8");

	int minMonthlyPurchase = 0;
	double minDollarAmountPurchase = 0;
	
	if(request.getParameter("minMonthlyPurchase") != null && request.getParameter("minDollarAmountPurchase") != null) { 
		minMonthlyPurchase = Integer.parseInt(request.getParameter("minMonthlyPurchase")); 
		minDollarAmountPurchase = Double.parseDouble(request.getParameter("minDollarAmountPurchase"));
	}
	System.out.println("minMonthlyPurchase : " + minMonthlyPurchase);
	System.out.println("minDollarAmountPurchase : " + minDollarAmountPurchase);
	
	//dao객체생성
	RewardsReportDao rewardsReportDao = new RewardsReportDao();
	Map<String, Object> map = rewardsReportDao.rewardReportCall(minMonthlyPurchase, minDollarAmountPurchase);
	List<Map<String,Object>> list = (List<Map<String,Object>>)map.get("list");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<ul class="nav justify-content-end bg-dark ">
		<li class="nav-item ">
			<a href="<%=request.getContextPath()%>/index.jsp" class="nav-link text-white">HOME</a>
		</li>
	</ul>
	<p><h1 class="text-dark">이달의 손님</h1></p>
	<table class="table table-bordered">
		<thead>
			<th>고객번호</th>
			<th>매점번호</th>
			<th>이름</th>
			<th>성</th>
			<th>email</th>
			<th>주소 아이디</th>
			<th>생성날짜</th>
			<th>수정날짜</th>
		</thead>
		<tbody>
				<%
				for(Map<String, Object> m : list) {
				%>
					<tr>
						<td><%=m.get("customerId") %></td>
						<td><%=m.get("storeId") %></td>
						<td><%=m.get("firstName") %></td>
						<td><%=m.get("lastName") %></td>
						<td><%=m.get("email") %></td>
						<td><%=m.get("addressId") %></td>
						<td><%=m.get("createDate") %></td>
						<td><%=m.get("lastUpdate") %></td>
					</tr>
				<%
				}
				%>
		</tbody>
	</table>
</div>	
</body>
</html>