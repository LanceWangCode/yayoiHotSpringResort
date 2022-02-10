<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>最新消息</title>
<%@ include file= "/front-end/framework/include.file" %>

</head>
<body>
<!-- Header Section Start -->
<%@ include file= "/front-end/framework/header.file" %>

<!-- Content Section Start -->
<div id="news">
    <div class="container">
        <div class="section-header">
            <h2>最新消息</h2>
        </div>
        <div class="row">
            <div class="col-md-6 img-cols">
                <div class="img-col">
                    <img class="img-fluid" src="<%=request.getContextPath()+"/resources/img/about/about-1.jpg" %>">
                </div>
            </div>
            <div class="col-md-6 content-cols">
                <div class="content-col">
                    <h3>彌生溫泉度假酒店 防疫服務政策</h3>
                    <p>
                        守護健康，雲品溫泉酒店全面啟動防疫措施<br>
                        為了賓客的健康與安全並配合政府防疫措施，雲品溫泉酒店遵循政府規定，已採取必要的應對措施...
                    </p>
                    <a href="#">Learn More</a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 img-cols d-block d-md-none">
                <div class="img-col">
                    <img class="img-fluid" src="<%=request.getContextPath()+"/resources/img/about/about-2.jpg" %>">
                </div>
            </div>
            <div class="col-md-6 content-cols">
                <div class="content-col">
                    <h3>彌生永續奢華之旅</h3>
                    <p>
                         近年來國人環保意識抬頭，永續旅遊逐漸受到重視，當代旅人開始正視自己對旅遊地的影響，並付諸行動帶來正向改變。永續旅行不僅對生態環境，也對當地文化以及經濟帶來正面影響。這幾年，除了疫情嚴重影響了我們的生活習慣，森林大火頻傳、全球海洋面積縮減，落實永續行動已經是身在地球的我們不得不重視的事情。
                    </p>
                    <a href="#">Learn More</a>
                </div>
            </div>
            <div class="col-md-6 img-cols d-none d-md-block">
                <div class="img-col">
                    <img class="img-fluid" src="<%=request.getContextPath()+"/resources/img/about/about-2.jpg" %>">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 img-cols">
                <div class="img-col">
                    <img class="img-fluid" src="<%=request.getContextPath()+"/resources/img/about/about-3.jpg" %>">
                </div>
            </div>
            <div class="col-md-6 content-cols">
                <div class="content-col">
                    <h3>VIKids 每日活動與包場服務</h3>
                    <p>
                        全新的VIKids打造出適合3至7歲小朋友的專屬空間，提供包場及每日體驗服務，25坪的空間裡以綠地與木製樹林加上雲朵造型燈的天然系設計為主，再搭配上大型冰棒及甜甜圈造型裝飾，猶如闖入夢遊仙境般的夢幻場域，成功吸引小朋友的注意力，空間內設有一座半透明式的旋轉溜滑梯，還有能讓小朋友發揮創造力的積木牆，以及小朋友最愛的冰淇淋櫃，家長可以放心的將小朋友交給服務人員照料。
                    </p>
                    <a href="#">Learn More</a>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Content Section End -->

<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>

</body>
</html>