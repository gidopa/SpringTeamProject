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
                        <!-- Existing select for destination -->
                        
                        <!-- New section for detailed destination choice with radio buttons -->
                        <div class="input-group">
                            <table class="styled-table">
                                <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Destination Name</th>
                                        <th>Country</th>
                                        <th>Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Sample data added directly for demonstration -->
                                    <tr>
                                        <td><input type="radio" name="selectedDestination" value="ICN"></td>
                                        <td>인천(ICN)</td>
                                        <td>대한민국</td>
                                        <td>대한민국 인천 국제공항</td>
                                    </tr>
                                    <tr>
                                        <td><input type="radio" name="selectedDestination" value="NRT"></td>
                                        <td>도쿄(NRT)</td>
                                        <td>일본</td>
                                        <td>일본 도쿄 국제공항</td>
                                    </tr>
                                    <!-- Additional rows can be added similarly -->
                                </tbody>
                            </table>
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
