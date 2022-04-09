<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
<h3>rewardReport</h3>
	<form action="<%=request.getContextPath()%>/procedure/rewardReportAction.jsp">
		<table class="form-group table table-striped">
			<tr class="table-info text-dark">
				<td>구매횟수</td>
				<td><input type="number" name="minMonthlyPurchase" class="form-control"></td>
				<td>구매가격</td>
				<td><input type="number" name="minDollarAmountPurchase" class="form-control"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-primary">조회하기</button>	
	</form>
</div>
</body>
</html>