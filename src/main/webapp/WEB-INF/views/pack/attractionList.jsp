<%--
  Created by IntelliJ IDEA.
  User: 605
  Date: 2024-05-10
  Time: 오전 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .lecture-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .lecture-table, .lecture-table th, .lecture-table td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
            font-size: 16px;
        }
        th {
            background-color: #f2f2f2;
        }
        button, input[type="submit"] {
            padding: 5px 10px;
            margin-top: 5px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover, input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<table class="lecture-table">
    <thead>
    <tr>
        <th>관광지</th>
        <th>종류</th>
        <th>설명</th>
        <th>수정</th>
        <th>삭제</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${attractionList}">
        <tr>
            <td>${list.attractionName}</td>
            <td>${list.type}</td>
            <td>${list.attractionDescription}</td>
            <td>
                <a href="/attraction/${list.attractionId}">
                <button >수정</button>
                </a>
            </td>
            <td>
                <form action="/attraction/${list.attractionId}" method="post">
                    <input type="hidden"  name="_method" value="DELETE"/>
                    <input type="hidden" name="attractionId" value="${list.attractionId}" />
                    <input type="submit" value="삭제" onclick="return confirm('정말 삭제하시겠습니까?');" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
