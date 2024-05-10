<%--
  Created by IntelliJ IDEA.
  User: 605
  Date: 2024-05-10
  Time: 오전 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .input-group {
            margin-bottom: 10px;
        }
        select {
            width: 300px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        #AttractionContent {
            margin-top: 20px;
            background: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
    </style>

    <script>
        $(document).ready(function() {
            $('#courseDropdown').change(function() {
                var selectedDestination = $(this).val();
                $.ajax({
                    url: '/attraction/destination/'+selectedDestination, // 서블릿 URL
                    type: 'GET',
                    data: {destinationId: selectedDestination}, // 드롭 다운 메뉴로 선택한 courseId를 기반으로 select
                    dataType: 'html', //lectureList.jsp의 html을 통째로 떄려박을거라 dataType은 html
                    success: function(response) {
                        $('#AttractionContent').html(response);
                    },error: function(xhr, status, error) {
                        console.log("Error: " + error);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    }
                });
            });
        });
    </script>
</head>
<body>


<h1>관광지 수정</h1>
<label for="courseDropdown">코스 선택:</label>
<select id="courseDropdown">
    <option value="">여행지를 선택하세요</option>
    <c:forEach items="${destination}" var="destination">
        <option value="${destination.destinationId}">${destination.destinationName}</option>
    </c:forEach>
</select>
<div id="AttractionContent">
    <!-- AJAX로 로드된 강의 내용이 표시될 부분 -->
</div>
</body>
</html>
