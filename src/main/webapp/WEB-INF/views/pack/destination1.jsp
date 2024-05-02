<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>여행 목적지 정보 입력</title>
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;600;700&display=swap" rel="stylesheet">
<style>
    .abc {
        font-family: 'Nunito', sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 20px;
        color: #333;
    }
    h1 {
        color: #2c3e50;
        text-align: center;
    }
    form {
        max-width: 960px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    .table-section {
        margin-bottom: 30px;
        padding: 20px;
        background-color: #ecf0f1;
        border-left: 5px solid #3498db;
        border-radius: 5px;
    }
    .table-section h2 {
        font-size: 22px;
        color: #3498db;
    }
    .input-group {
        margin-bottom: 15px;
    }
    label {
        display: block;
        margin-bottom: 8px;
        font-size: 16px;
        font-weight: 600;
    }
    input[type="text"], input[type="number"], textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #bdc3c7;
        border-radius: 5px;
        font-size: 14px;
    }
    textarea {
        height: 120px;
    }
    button {
        width: 100%;
        padding: 15px;
        background-color: #3498db;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 18px;
        font-weight: bold;
        margin-top: 20px;
    }
    button:hover {
        background-color: #2980b9;
    }
</style>
</head>
<body>
<div class="abc">
<h1>여행 목적지 정보 입력</h1>
<form>
    <div class="table-section">
        <h2>Destinations</h2>
        <div class="input-group">
            <label for="destination_id">목적지 ID:</label>
            <input type="number" id="destination_id" name="destination_id" required>
        </div>
        <div class="input-group">
            <label for="destination_name">목적지 이름:</label>
            <input type="text" id="destination_name" name="destination_name" required>
        </div>
        <div class="input-group">
            <label for="country">국가:</label>
            <input type="text" id="country" name="country" required>
        </div>
        <div class="input-group">
            <label for="description">설명:</label>
            <textarea id="description" name="description"></textarea>
        </div>
    </div>

    <div class="table-section">
        <h2>Destinations Images</h2>
        <div class="input-group">
            <label for="img_id">이미지 ID:</label>
            <input type="number" id="img_id" name="img_id" required>
        </div>
        <div class="input-group">
            <label for="img_name">이미지 이름:</label>
            <input type="text" id="img_name" name="img_name" required>
        </div>
        <div class="input-group">
            <label for="category">카테고리:</label>
            <input type="text" id="category" name="category" required>
        </div>
        <div class="input-group">
            <label for="destination_id_img">목적지 ID:</label>
            <input type="number" id="destination_id_img" name="destination_id_img" required>
        </div>
    </div>

    <div class="table-section">
        <h2>Attractions</h2>
        <div class="input-group">
            <label for="attraction_id">관광지 ID:</label>
            <input type="number" id="attraction_id" name="attraction_id" required>
        </div>
        <div class="input-group">
            <label for="attraction_name">관광지 이름:</label>
            <input type="text" id="attraction_name" name="attraction_name" required>
        </div>
        <div class="input-group">
            <label for="type">유형:</label>
            <input type="text" id="type" name="type" required>
        </div>
        <div class="input-group">
            <label for="attraction_description">설명:</label>
            <textarea id="attraction_description" name="attraction_description"></textarea>
        </div>
    </div>

    <div class="table-section">
        <h2>Restaurants</h2>
        <div class="input-group">
            <label for="restaurant_id">레스토랑 ID:</label>
            <input type="number" id="restaurant_id" name="restaurant_id" required>
        </div>
        <div class="input-group">
            <label for="restaurant_name">레스토랑 이름:</label>
            <input type="text" id="restaurant_name" name="restaurant_name" required>
        </div>
        <div class="input-group">
            <label for="cuisine">요리 종류:</label>
            <input type="text" id="cuisine" name="cuisine" required>
        </div>
        <div class="input-group">
            <label for="restaurant_description">설명:</label>
            <textarea id="restaurant_description" name="restaurant_description"></textarea>
        </div>
    </div>
    <button type="submit">제출하기</button>
</form>
</div>
</body>
</html>
    