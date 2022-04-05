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
<div class="container p-3 my-3 border border-dark" style= "background-color:#E0F2F7">
	<h1 class="font-weight-bold text-center border border-dark" style="padding: 10px; background-color:#A9E2F3 ; color: #58ACFA">HOME</h1>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0" >
		<img class="card-img-top " src="<%=request.getContextPath()%>/img/store.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">매점 목록</h4>
			<p class="card-text">매점 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/storeList.jsp">매점 리스트 보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staff.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">스태프 목록</h4>
			<p class="card-text">스태프들의 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/staffList.jsp">스태프 목록보기</a>
			</div>
		</div>
		<!-- view -->
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/actor.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">배우 정보</h4>
			<p class="card-text">배우들의 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp">배우 정보보기</a>
			</div>
		</div>
		
	</div>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/customer.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">손님 정보</h4>
			<p class="card-text">손님들의 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/customerList.jsp">손님 정보보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/film.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">영화 정보</h4>
			<p class="card-text">영화 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/filmList.jsp">영화 정보보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/filmstore.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">매점영화정보</h4>
			<p class="card-text">매점에 있는 영화 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">매점 영화목록 보기</a>
			</div>
		</div>
	</div>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/sales.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">영화 판매매출 정보</h4>
			<p class="card-text">각 영화장르의 판매 매출을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/salesByFilmCategory.jsp">영화 판매매출액보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staffsales.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">스태프의 상점 매출액 정보</h4>
			<p class="card-text">스태프들의 매점 매출을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/salesByStore.jsp">스태프 매출액보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staffList.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">스태프 정보(View)</h4>
			<p class="card-text">스태프들의 개인정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/staffListView.jsp">스태프 개인정보보기</a>
			</div>
		</div>
	</div>
	<!-- 프로시저 -->
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/stock.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">filmInStock</h4>
			<p class="card-text">영화의 사본 지정된 저장소에 재고가 있는지 확인 할 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/filmInStock.jsp">영화 재고보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/rent.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">filmNotInStock</h4>
			<p class="card-text">상점에 재고가 없는 영화의 대여(사본)가 있는지 확인 할 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/filmNotInStock.jsp">영화 재고보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/reward.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h4 class="card-title">rewardsReport</h4>
			<p class="card-text">이전의 달에 상위고객에 대한 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/rewardsReport.jsp">상위고객 보기</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>