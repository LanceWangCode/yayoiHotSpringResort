<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <mata name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
        }

        img {
            max-width: 100%;
        }

        :root {
            /* font-size: 62.5%; */
            /* 16px * 62.5% = 10px */
            --header-height: 60px;
            /* variable */
            --aside-width: 240px;
        }

        /*
            div{
                font-size: 1.8rem;  >>> 1.8rem => 1.8 * 10px = 18px
            }
            */
        /* ====== header ���� =======*/
        header.header {
            border: 1px solid black;
            height: var(--header-height);
            background-color: lightgray;
            position: sticky;
            top: 0;
        }
        header.header > h3{
        	margin-left: 10px;
        }

        /* ====== aside ���� =======*/
        aside.aside {
            /* border: 1px solid red; */
            position: fixed;
            left: 0;
            top: var(--header-height);
            width: var(--aside-width);
            background-color: lightblue;
            height: calc(100% - var(--header-height));
            padding: 20px 0;
            overflow-y: auto;

            transition: 1s; 
        }
        @media screen and (max-width: 767.98px) {
            aside.aside {
                top: 0;
                height: 100%;
                transform: translateX(-100%);
            }
            aside.aside.-on{
                transform: translateX(0%);
            }
        }
        aside.aside nav.nav > ul.nav_list{
            /* border: 1px solid purple; */
            list-style: none;
            margin: 0;
            padding: 0;
        }
        aside.aside nav.nav{
            /* border: 1px solid green; */
        }

        aside.aside nav.nav > ul.nav_list > li{
            /* border: 1px solid orange; */
        }
        aside.aside nav.nav > ul.nav_list > li > h4{
            /* border: 1px solid white; */
            display: block;
            text-decoration: none;
            text-align: center;
            padding: 0;
        }
        aside.aside nav.nav > ul.nav_list > li > a{
            border: 1px solid white;
            display: block;
            text-decoration: none;
            text-align: center;
            padding: 5px 0;
        }
        /* ====== main ���� =======*/
        main.main {
            /* border: 1px solid blue; */
            margin-left: var(--aside-width);
            width: calc(100% - var(--aside-width));
            background-color: orange;
            min-height: calc(100vh - var(--header-height));

            padding: 20px;
        }
        @media screen and (max-width: 767.98px) {
            main.main {
                margin-left: 0;
                width: 100%;
            }
        }
        main.main > ul.item_list{
            /* border: 1px solid blue; */
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            flex-wrap: wrap;
        }
        main.main > ul.item_list > li{
            border: 1px solid green;
            width: calc((100% - 60px) / 4 );
            margin-right: 20px;
            margin-bottom: 20px;
        }
        @media screen and (max-width: 767.98px) {
            main.main > ul.item_list > li{
                width: calc((100% - 20px) / 2 );
            }
        }
        main.main > ul.item_list > li:nth-child(4n){
            margin-right: 0;
        }
        @media screen and (max-width: 767.98px) {
            main.main > ul.item_list > li:nth-child(2n){
                margin-right: 0;
            }
        }
        main.main > ul.item_list > li > a{
            display: block;
            border: 1px solid white;
            text-decoration: none;
        }
        main.main > ul.item_list > li > a div.img_block{
            border: 1px solid blue;
            font-size: 0;
        }
        main.main > ul.item_list > li > a span.item_text{
            display: block;
            /* border: 1px solid red; */
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        button.btn_hamburger{
            display: none; 
        }
        @media screen and (max-width: 767.98px) {
            button.btn_hamburger{
                display: block;
            }
        }
        @media screen and (max-width: 767.98px) {
	        header.header > h3 {
			  display: inline-block;
	        }
        	header.header > button.btn_hamburger{
        		display: inline-block;
        	}
        }
    </style>
</head>

<body>
    <header class="header">
        <button type="button" class="btn_hamburger">選單</button>
        <h3>彌生酒店管理後台 - 功能一</h3>
    </header>
    <aside class="aside">
        <nav class="nav">
            <button type="button" class="btn_hamburger">選單</button>
            <ul class="nav_list">
            	<li><h4>User Name：<%= session.getAttribute("username") %></h4></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin.jsp" %>">儀錶版</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin1.jsp" %>">功能一</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin2.jsp" %>">功能二</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin3.jsp" %>">功能三</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin-class.jsp" %>">自訂功能</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin4.jsp" %>">功能四</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin5.jsp" %>">功能五</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/demo/admin6.jsp" %>">功能六</a></li>
                <li><a href="#">功能未定一</a></li>
                <li><a href="#">功能未定二</a></li>
                <li><a href="<%=request.getContextPath()+"/back-end/admin/login.jsp" %>">登出</a></li>
            </ul>
        </nav>
    </aside>
    <main class="main">
        <ul class="item_list">
            <li>
              <a href="#">
                <div class="img_block">
                  <img src="https://via.placeholder.com/400x300">
                </div>
                <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
              </a>
            </li>
            <li>
              <a href="#">
                <div class="img_block">
                  <img src="https://via.placeholder.com/400x300">
                </div>
                <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
              </a>
            </li>
            <li>
              <a href="#">
                <div class="img_block">
                  <img src="https://via.placeholder.com/400x300">
                </div>
                <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
              </a>
            </li>
            <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
              <li>
                <a href="#">
                  <div class="img_block">
                    <img src="https://via.placeholder.com/400x300">
                  </div>
                  <span class="item_text">標題說明標題說明標題說明標題說明標題說明標題說明標題說明</span>
                </a>
              </li>
                </ul>
    </main>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    $("button.btn_hamburger").on("click", function(){
        $("aside.aside").toggleClass("-on");
    });
    </script>
</body>

</html>