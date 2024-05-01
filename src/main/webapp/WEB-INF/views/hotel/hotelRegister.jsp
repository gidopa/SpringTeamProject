<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Registration Form</title>
    <link rel="stylesheet" href="resources/css/hotelRegister.css">
</head>
<body>
    <form id="hotelRegistrationForm">
        <h1>호텔 등록 페이지</h1>
        <label for="hotelName">호텔 이름:</label>
        <input type="text" id="hotelName" name="hotelName" required>

        <label for="hotelLocation">호텔 위치:</label>
        <input type="text" id="hotelLocation" name="hotelLocation" required>

        <label for="hotelStars">Hotel Stars:</label>
        <select id="hotelStars" name="hotelStars">
            <option value="1">1 Star</option>
            <option value="2">2 Stars</option>
            <option value="3">3 Stars</option>
            <option value="4">4 Stars</option>
            <option value="5">5 Stars</option>
        </select>

        <label for="hotelDescription">호텔 설명:</label>
        <textarea id="hotelDescription" name="hotelDescription" required></textarea>

        <label for="hotelImage">호텔 이미지:</label>
        <input type="file" id="hotelImage" name="hotelImage" accept="image/*">

        <fieldset>
            <legend>호텔 편의시설</legend>
            <label><input type="checkbox" name="amenities" value="Free WiFi"> Free WiFi</label>
            <label><input type="checkbox" name="amenities" value="Parking"> Parking</label>
            <label><input type="checkbox" name="amenities" value="Pool"> Pool</label>
            <label><input type="checkbox" name="amenities" value="Gym"> Gym</label>
            <label><input type="checkbox" name="amenities" value="Spa"> Spa</label>
        </fieldset>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
