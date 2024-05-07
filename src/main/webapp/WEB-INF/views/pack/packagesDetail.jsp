<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <title>Au Register Forms by Colorlib</title>
    <link href="/pack/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="/pack/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
    <link href="/pack/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/pack/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">
    <link href="/pack/css/main1.css" rel="stylesheet" media="all">
    <style type="text/css">
      .styled-table {
        width: 100%;
        border-collapse: collapse;
      }
      .styled-table th, .styled-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
      }
      .styled-table th {
        background-color: #f2f2f2;
      }
    </style>
    <script type="text/javascript">
        function setHotelField(hotelId, index) {
            document.getElementById('hotel_' + index).value = hotelId;
        }
        
        function setRestaurantField(restaurantId, index) {
            document.getElementById('restaurant_' + index).value = restaurantId;
        }
    </script>
</head>

<body>

    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-heading"></div>
                <div class="card-body">
                    <h2 class="title">일차 별 상세 일정 입력</h2>
                    <form method="POST" action="/restaurants/${destination_Id}" enctype="multipart/form-data">
                        <c:forEach var="i" begin="1" end="2">
                        	<h4>${i} 일차 일정</h4>
                        	호텔:
                        	<input type="text" id="hotel_${i}" name="hotel_${i}" value="" required>
                            <div class="input-group">
                                <table class="styled-table">
						            <thead>
						                <tr>
						                    <th>Select</th>
						                    <th>Hotel Name</th>
						                    <th>Destination Name</th>
						                    <th>Star Rating</th>
						                    <th>Description</dt>
						                    <th>Hotel Amenities</dt>
						                    <th>Hotel Img</dt>
						                </tr>
						            </thead>
						            <tbody>
						                <c:forEach var="item" items="${hotelView}">
						                    <tr>
						                        <td> 
						                        	<input type="radio" name="hotelId_${i}" value="${item.hotelId}" onclick="setHotelField('${item.hotelId}', ${i})">
						                        </td>
						                        <td>${item.hotelName}</td>
						                        <td>${item.destinationName}</td>
						                        <td>${item.starRating}</dt>
						                        <td>${item.description}</dt>
						                        <td>${item.hotelAmenities}</dt>
						                        <td>${item.hotelImages}</dt>
						                    </tr>
						                </c:forEach>
						            </tbody>
						        </table>
                            </div>

							식당:
<%-- 							<input type="text" id="restaurant_${i}" name="restaurant_${i}" value="" required> --%>
<!--                             <div class="input-group"> -->
<!--                             	<table class="styled-table"> -->
<!-- 						            <thead> -->
<!-- 						                <tr> -->
<!-- 						                    <th>Select</th> -->
<!-- 						                    <th>Hotel Name</th> -->
<!-- 						                    <th>Destination Name</th> -->
<!-- 						                    <th>Star Rating</th> -->
<!-- 						                    <th>Description</dt> -->
<!-- 						                    <th>Hotel Amenities</dt> -->
<!-- 						                    <th>Hotel Img</dt> -->
<!-- 						                </tr> -->
<!-- 						            </thead> -->
<!-- 						            <tbody> -->
<%-- 						                <c:forEach var="item" items="${restaurant}"> --%>
<!-- 						                    <tr> -->
<!-- 						                        <td>  -->
<%-- 						                        	<input type="radio" name="restaurantId_${i}" value="${item.hotelId}" onclick="setRestaurantField('${item.restaurantId}', ${i})"> --%>
<!-- 						                        </td> -->
<%-- 						                        <td>${item.hotelName}</td> --%>
<%-- 						                        <td>${item.destinationName}</td> --%>
<%-- 						                        <td>${item.starRating}</dt> --%>
<%-- 						                        <td>${item.description}</dt> --%>
<%-- 						                        <td>${item.hotelAmenities}</dt> --%>
<%-- 						                        <td>${item.hotelImages}</dt> --%>
<!-- 						                    </tr> -->
<%-- 						                </c:forEach> --%>
<!-- 						            </tbody> -->
<!-- 						        </table> -->
<!--                             </div> -->
							
							관광지:
							<div class="input--style-2" type="text" placeholder="관광지 이름" name="touristSpot_${i}" required>
                            </div>
                            
                            액티비티:
                            <div class="input--style-2" type="text" placeholder="액티비티 이름" name="activity_${i}" required>
                            </div>				
                            <br><br>
                        </c:forEach>
                        <div class="p-t-30">
                            <button class="btn btn--radius btn--green" type="submit">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="/pack/vendor/jquery/jquery.min.js"></script>
    <script src="/pack/vendor/select2/select2.min.js"></script>
    <script src="/pack/vendor/datepicker/moment.min.js"></script>
    <script src="/pack/vendor/datepicker/daterangepicker.js"></script>
    <script src="/pack/js/global.js"></script>

</body>
</html>
