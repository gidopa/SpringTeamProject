<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>여행 패키지 상세 정보</title>
    <!-- Bootstrap CSS -->
    <style>
        .info-header {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .btn-toggle {
            width: 100%;
            text-align: left;
            padding: 10px;
            font-size: 1.2rem;
            color: #007bff;
            background-color: #e9ecef;
            border-color: #007bff;
        }
        .detail-item {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 5px;
        }
        .detail-image {
            width: 100%;
            height: auto;
            border-radius: 5px;
            margin-top: 10px;
        }
        .content-area {
            display: flex;
        }
        .main-content {
            flex: 1;
        }
        .side-bar {
            width: 200px; /* Adjust the width as needed */
            padding-left: 20px;
        }
        .booking-button, .person-select {
            display: block;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container content-area">
    <div class="main-content">
        <div class="info-header">
            <h1 class="h3">여행지: ${pack.destinationName}</h1>
            <p>출발일: ${pack.startDate} | 도착일: ${pack.endDate} | 가격: ${pack.price}원</p>
            <c:forEach var="image" items="${imageList}">
                <img src="/images/${image.imgName}" alt="${image.imgName}">
            </c:forEach>
        </div>
        <div id="accordion">
            <c:forEach var="entry" items="${map}">
                <div class="card">
                    <div class="card-header" id="heading${entry.key}">
                        <h2 class="mb-0">
                            <button class="btn btn-toggle" type="button" data-toggle="collapse" data-target="#collapse${entry.key}" aria-expanded="true" aria-controls="collapse${entry.key}">
                                Day ${entry.key}
                            </button>
                        </h2>
                    </div>
                    <div id="collapse${entry.key}" class="collapse" aria-labelledby="heading${entry.key}" data-parent="#accordion">
                        <div class="card-body">
                            <c:forEach var="itemWrapper" items="${entry.value}">
                                <div class="detail-item">
                                    <c:choose>
                                        <c:when test="${itemWrapper.type == 'hotel'}">
                                            <strong>Hotel:</strong> ${itemWrapper.item.hotelName} - ${itemWrapper.item.destinationName}
                                        </c:when>
                                        <c:when test="${itemWrapper.type == 'hotelAmenities'}">
                                            <strong>Hotel Amenities:</strong> ${itemWrapper.item.amenity}
                                        </c:when>
                                        <c:when test="${itemWrapper.type == 'hotelImages'}">
                                            <img src="${itemWrapper.item.imgName}" alt="${itemWrapper.item.imgName}" class="detail-image">
                                        </c:when>
                                        <c:when test="${itemWrapper.type == 'attraction'}">
                                            <strong>Attraction:</strong> ${itemWrapper.item.attractionName} - ${itemWrapper.item.attractionDescription}
                                        </c:when>
                                        <c:when test="${itemWrapper.type == 'restaurant'}">
                                            <strong>Restaurant:</strong> ${itemWrapper.item.restaurantName} - ${itemWrapper.item.restaurantDescription}
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="side-bar">
        <button class="btn btn-primary booking-button">
            예약하기
        </button>
        <select class="person-select form-control">
            <option value="1">1인</option>
            <option value="2">2인</option>
            <option value="3">3인</option>
            <option value="4">4인</option>
            <option value="5">5인</option>
        </select>
    </div>
</div>
</body>
</html>
