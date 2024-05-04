<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"> -->
</head>

<body>
  <!-- [S]bloomcity-N1 -->

  <!-- [E]bloomcity-N1 -->
  <main class="th-layout-main ">
    <!-- [S]bloomcity-N9 -->
    <div class="bloomcity-N9" data-bid="LDLvM1Xg5K">
      <div class="content-container">
        <div class="container-md">
          <div class="con-tit textset">
            <h2 class="textset-tit ff-po">Package Register</h2>
            <p class="textset-desc">편안함과 여유를 선사하는 것으로 특별한 여유를 느낄 수 있는 객실에서 설레이는 여행의 시작과 끝을 블롬시티 호텔과 함께해보세요</p>
          </div>
          <div class="content-group">
            <div class="form-wrap">
              <form>
                <div class="form-header">
                  <h3 class="form-tit">날짜를 선택하세요.</h3>
                  <fieldset class="mobile-check">
                    <div class="input-group">
                      <label class="label" for="CheckIn-m">CHECK IN</label>
                      <div class="inputset inputset-lg calendar">
                        <input id="CheckIn-m" type="text" class="date-range200 inputset-input form-control" value="">
                      </div>
                    </div>
                    <label class="inputset inputset-lg days-wrap">
                      <input type="text" class="selected-days inputset-input" value="11"> 박 </label>
                    <div class="input-group">
                      <label class="label" for="CheckOut-m">CHECK OUT</label>
                      <div class="inputset inputset-lg calendar">
                        <input id="CheckOut-m" type="text" class="date-range201 inputset-input form-control" value="">
                      </div>
                    </div>
                  </fieldset>
                  <div class="contents-date"></div>
                </div>
                <div class="form-body">
                  <div class="calendar-wrap"></div>
                  <fieldset class="fieldset pc-form">
                  
                    <div class="input-group">
                      <label class="label" for="CheckIN-pc">체크인</label>
                      <div class="inputset inputset-lg">
                        <input id="CheckIN-pc" type="text" class="date-range200 inputset-input form-control" value="" readonly>
                        <figure class="icon">
                          <img src="/icons/icon_calendar.svg" alt="아이콘">
                        </figure>
                      </div>
                    </div>
                    
                    <div class="input-group">
                      <label class="label" for="CheckOut-pc"></label>체크아웃 <div class="inputset inputset-lg">
                        <input id="CheckOut-pc" type="text" class="date-range201 inputset-input form-control" value="" readonly>
                        <figure class="icon">
                          <img src="/icons/icon_calendar.svg" alt="아이콘">
                        </figure>
                      </div>
                    </div>
                    
                    
                     <div class="input-group">
                      <label class="label">패키지 지역</label>
                      <div class="inputset inputset-lg">
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_minus_black.svg" alt="-"> -->
                        </button>
	                       <select class="form-select form-select-lg mb-3" aria-label="Large select example">
							 	<option value="KOREA ICN">인천(ICN)</option>
							    <option value="JAPAN NRT">도쿄(NRT)</option>
							    <option value="JAPAN HND">하네다(HND)</option>
							    <option value="JAPAN KIX">오사카(KIX)</option>
							    <option value="JAPAN CTS">삿포로(CTS)</option>
							    <option value="EASTSOUTHASIA BKK">방콕(BKK)</option>
							    <option value="EASTSOUTHASIA SIN">싱가포르(SIN)</option>
							    <option value="EASTSOUTHASIA KUL">쿠알라룸푸르(KUL)</option>
							    <option value="EASTSOUTHASIA CGK">자카르타(CGK)</option>
							    <option value="EASTSOUTHASIA HAN">하노이(HAN)</option>
							</select>
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_plus_black.svg" alt="+"> -->
                        </button>
                      </div>
                    </div>
                    
                    
                     <div class="input-group">
                      <label class="label">패키지 타입</label>
                      <div class="inputset inputset-lg">
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_minus_black.svg" alt="-"> -->
                        </button>
                       <select class="form-select form-select-lg mb-3" aria-label="Large select example">
						 	<option value="KOREA ICN">일반 패키지</option>
						    <option value="JAPAN NRT">허니문 패키지</option>
						</select>
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_plus_black.svg" alt="+"> -->
                        </button>
                      </div>
                    </div>
                    
                    <div class="input-group">
                      <label class="label">Price</label>
                      <div class="inputset inputset-lg">
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_minus_black.svg" alt="-"> -->
                        </button>
                        <input type="number" class="inputset-input form-control" placeholder="가격을 입력하세요." >
                        <button class="btnset" type="button">
<!--                           <img src="/icons/icon_plus_black.svg" alt="+"> -->
                        </button>
                      </div>
                    </div>
                  </fieldset>
                  </fieldset>
                </div>
                <div class="btn-box">
                  <a class="btnset btnset-lg btnset-rect" href="javascript:void(0)">패키지 등록하기</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</body>