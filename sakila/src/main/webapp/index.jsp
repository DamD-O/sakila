<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container p-3 my-3 border border-dark" style= "background-color:#E0F2F7">
	<h1 class="font-weight-bold text-center border border-dark" style="padding: 10px; background-color:#A9E2F3 ; color: #58ACFA">HOME</h1>
		<div class="clearfix">
			<table>
				<tr>
					<td>
						<h6 class="text-right">
						<img src="<%=request.getContextPath()%>/img/search.png" width="35px" >
						<a href="<%=request.getContextPath()%>/search/filmSearchForm.jsp">영화 검색하기</a>
						</h6>
					</td>
					<td>
						<h6 class="text-right">
						<img src="<%=request.getContextPath()%>/img/search.png" width="35px" >
						<a href="<%=request.getContextPath()%>/search/rentalSearchForm.jsp">대여 상세검색</a>
						</h6>
					</td>
					<td>
						<h6 class="text-right">
						<img src="<%=request.getContextPath()%>/img/stats.png" width="35px" >
						<a href="<%=request.getContextPath()%>/statistics/statsData.jsp">통계</a>
						</h6>
					</td>
				</tr>				
			</table>
		</div>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0" >
		<img class="card-img-top " src="<%=request.getContextPath()%>/img/store.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">매점 목록</h5>
			<p class="card-text">매점 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/storeList.jsp">매점 리스트 보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staff.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">스태프 목록</h5>
			<p class="card-text">스태프들의 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/staffList.jsp">스태프 목록보기</a>
			</div>
		</div>
		<!-- view -->
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/actor.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">배우 정보</h5>
			<p class="card-text">배우들의 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/actorInfoList.jsp">배우 정보보기</a>
			</div>
		</div>
		
	</div>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/customer.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">손님 정보</h5>
			<p class="card-text">손님들의 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/customerList.jsp">손님 정보보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/film.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">영화 정보</h5>
			<p class="card-text">영화 정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/filmList.jsp">영화 정보보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/filmstore.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">영화정보</h5>
			<p class="card-text">영화 정보를 볼 수 있습니다.<br>(배우 이름이 Pascal표기법으로 되어있음.)</p>
			<a href="<%=request.getContextPath()%>/view/nicerButSlowerFilmList.jsp">매점 영화목록 보기</a>
			</div>
		</div>
	</div>
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/sales.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">영화 판매매출 정보</h5>
			<p class="card-text">각 영화장르의 판매 매출을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/salesByFilmCategory.jsp">영화 판매매출액보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staffsales.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">스태프의 상점 매출액 정보</h5>
			<p class="card-text">스태프들의 매점 매출을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/salesByStore.jsp">스태프 매출액보기</a>
			</div>
		</div>
		<div class="card border border-dark"style= "background-color:#E0F8E0" >
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/staffList.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">스태프 정보(View)</h5>
			<p class="card-text">스태프들의 개인정보를 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/view/staffListView.jsp">스태프 개인정보보기</a>
			</div>
		</div>
	</div>
	<!-- 프로시저 -->
	<div class="card-deck" style="padding: 10px">
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/stock.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">재고 확인<br>(프로시저)</h5>
			<p class="card-text">영화가 지정된 매점에 재고가 있는지 확인 할 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/procedure/filmInStock.jsp">영화 재고보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/rent.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">없는 재고 확인</h5>
			<p class="card-text">매점에 재고가 없는 영화를 확인 할 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/procedure/filmNotInStock.jsp">영화 재고보기</a>
			</div>
		</div>
		<div class="card border border-dark" style= "background-color:#E0F8E0">
			<img class="card-img-top" src="<%=request.getContextPath()%>/img/reward.png" alt="Card image" style="width:100px">
			<div class="card-body">
			<h5 class="card-title">이달의 손님</h5>
			<p class="card-text">이전의 달에 상위고객에 대한 목록을 볼 수 있습니다.</p>
			<a href="<%=request.getContextPath()%>/procedure/rewardsReport.jsp">상위고객 보기</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>