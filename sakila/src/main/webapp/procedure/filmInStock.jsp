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
	<form action="<%=request.getContextPath()%>/procedure/insertFilmInStockAction.jsp">
		<table class="form-group table table-striped">
			<tr class="table-info text-dark">
				<td>영화번호</td>
				<td><input type="number" name="filmId" class="form-control" max="1000"></td>
				<td>가게번호</td>
				<td><input type="number" name="storeId" class="form-control" max="2"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-primary">재고 확인</button>	
	</form>
</div>
</body>
</html>