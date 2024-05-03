<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Au Register Forms by Colorlib</title>

    <!-- Icons font CSS-->
    <link href="/pack/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="/pack/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="/pack/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/pack/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="/pack/css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo">
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-body">
                    <h2 class="title">식당 지역 선택</h2>
                    <form method="POST" action="/restaurants">
                    	
                    	<!-- Destination 정보 입력 -->
                    	<!-- Destination_name 목적지 이름 -->
                    	<div class="input-group">
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="destinationName" required>
                                    <option disabled="disabled" selected="selected">Destination_name</option>
                                    <option value="ICN">인천(ICN)</option>
								    <option value="NRT">도쿄(NRT)</option>
								    <option value="HND">도쿄(하네다)(HND)</option>
								    <option value="KIX">오사카(KIX)</option>
								    <option value="CTS">삿포로(CTS)</option>
								    <option value="BKK">방콕(BKK)</option>
								    <option value="SIN">싱가포르(SIN)</option>
								    <option value="KUL">쿠알라룸푸르(KUL)</option>
								    <option value="CGK">자카르타(CGK)</option>
								    <option value="HAN">하노이(HAN)</option>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div>
                        
                         <div class="p-t-30">
                            <button class="btn btn--radius btn--green" type="submit">저장</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="/pack/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="/pack/vendor/select2/select2.min.js"></script>
    <script src="/pack/vendor/datepicker/moment.min.js"></script>
    <script src="/pack/vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="/pack/js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>