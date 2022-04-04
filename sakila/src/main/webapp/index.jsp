<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark">
	<h1 class="font-weight-bold text-center" style="padding: 10px">HOME</h1>
	<div class="card-deck"  >
		<div class="card" >
		<img class="card-img-top" src="<%=request.getContextPath()%>/img/store.png" alt="Card image">
			<div class="card-body">
			<h4 class="card-title">매점 목록</h4>
			<p class="card-text">store list</p>
			<a href="<%=request.getContextPath()%>/storeList.jsp">매점 리스트 보기</a>
			</div>
		</div>
		<div class="card" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staff.png" alt="Card image">
			<div class="card-body">
			<h4 class="card-title">스태프 목록</h4>
			<p class="card-text">staff list</p>
			<a href="<%=request.getContextPath()%>/staffList.jsp">스태프 목록보기</a>
			</div>
		</div>
		<div class="card" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/actor.png" alt="Card image">
			<div class="card-body">
			<h4 class="card-title">배우 정보</h4>
			<p class="card-text">actor Info</p>
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp">배우 정보보기</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>