<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Yayoi Hot spring resort | 彌生溫泉度假酒店</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

<%@ include file= "/front-end/framework/include.file" %>



</head>

<body>
  <!-- Header Section Start -->
  <%@ include file= "/front-end/framework/header.file" %>
  
<!-- Header Section End -->

<!-- Header Bottom Start -->
<div id="header-bottom">
  <!-- Search Section Start -->
  <div id="search" class="search-slider">
      <div class="container">
          <h1 style="font-family: 華康瘦金體">悠活山林之旅 ·帶你重拾幸福</h1>
          <div class="form-row">
              <div class="control-group col-md-3">
                  <label>入住日期</label>
                  <div class="form-group">
                      <div class="input-group date" id="date-3" data-target-input="nearest">
                          <input type="text" class="form-control datetimepicker-input" data-target="#date-3"/>
                          <div class="input-group-append" data-target="#date-3" data-toggle="datetimepicker">
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="control-group col-md-3">
                  <label>退房日期</label>
                  <div class="form-group">
                      <div class="input-group date" id="date-4" data-target-input="nearest">
                          <input type="text" class="form-control datetimepicker-input" data-target="#date-4"/>
                          <div class="input-group-append" data-target="#date-4" data-toggle="datetimepicker">
                              <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="control-group col-md-3">
                  <div class="form-row">
                      <div class="control-group col-md-6">
                          <label>成人</label>
                          <select class="custom-select">
                              <option selected>0</option>
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                              <option value="9">9</option>
                              <option value="10">10</option>
                          </select>
                      </div>
                      <div class="control-group col-md-6 control-group-kid">
                          <label>孩童</label>
                          <select class="custom-select">
                              <option selected>0</option>
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                              <option value="9">9</option>
                              <option value="10">10</option>
                          </select>
                      </div>
                  </div>
              </div>
              <div class="control-group col-md-3">
                  <button class="btn btn-block">Search</button>
              </div>
          </div>
      </div>
  </div>
  <!-- Search Section End -->

  <!-- Slider Section Start -->
  <div id="headerSlider" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
          <li data-target="#headerSlider" data-slide-to="0" class="active"></li>
          <li data-target="#headerSlider" data-slide-to="1"></li>
          <li data-target="#headerSlider" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
          <div class="carousel-item active">
              <img src="<%=request.getContextPath()+"/resources/img/slider/header-slider-1.jpg" %>" alt="Royal Hotel">
          </div>

          <div class="carousel-item">
              <img src="<%=request.getContextPath()+"/resources/img/slider/header-slider-2.jpg" %>" alt="Royal Hotel">
          </div>

          <div class="carousel-item">
              <img src="<%=request.getContextPath()+"/resources/img/slider/header-slider-3.jpg" %>" alt="Royal Hotel">
          </div>
      </div>

      <a class="carousel-control-prev" href="#headerSlider" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#headerSlider" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
      </a>
  </div>
  <!-- Slider Section End -->
</div>
<!-- Header Bottom End -->

<!-- Search Section Start -->
<div id="search" class="search-home">
  <div class="container">
      <div class="form-row">
          <div class="control-group col-md-3">
              <label>Check-In</label>
              <div class="form-group">
                  <div class="input-group date" id="date-5" data-target-input="nearest">
                      <input type="text" class="form-control datetimepicker-input" data-target="#date-5"/>
                      <div class="input-group-append" data-target="#date-5" data-toggle="datetimepicker">
                          <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="control-group col-md-3">
              <label>Check-Out</label>
              <div class="form-group">
                  <div class="input-group date" id="date-6" data-target-input="nearest">
                      <input type="text" class="form-control datetimepicker-input" data-target="#date-6"/>
                      <div class="input-group-append" data-target="#date-6" data-toggle="datetimepicker">
                          <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                      </div>
                  </div>
              </div>
          </div>
          <div class="control-group col-md-3">
              <div class="form-row">
                  <div class="control-group col-md-6">
                      <label>Adult</label>
                      <select class="custom-select">
                          <option selected>0</option>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                          <option value="7">7</option>
                          <option value="8">8</option>
                          <option value="9">9</option>
                          <option value="10">10</option>
                      </select>
                  </div>
                  <div class="control-group col-md-6 control-group-kid">
                      <label>Kid</label>
                      <select class="custom-select">
                          <option selected>0</option>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                          <option value="7">7</option>
                          <option value="8">8</option>
                          <option value="9">9</option>
                          <option value="10">10</option>
                      </select>
                  </div>
              </div>
          </div>
          <div class="control-group col-md-3">
              <button class="btn btn-block">Search</button>
          </div>
      </div>
  </div>
</div>
<!-- Search Section End -->

<!-- Welcome Section Start -->
<div id="welcome">
  <div class="container">
      <h3>歡迎來到日月潭第一泉</h3>
      <h4>坐看雲起，食住幸也，駐留享受靜謐且舒適的度假時光！</h4> 
      <p>位於日月潭北邊的彌生溫泉度假酒店，明亮恬靜的空間，融入日月潭豐富多變的景致，每個角落都能一覽這幅生動的自然畫作，美不勝收。
      酒店精心規畫211間客房，每房皆設有天然溫泉池，讓您自在地享受專屬的放鬆時刻。館內六間餐廳提供多樣化的中、西式美食，邀您前來盡情品味完美的度假體驗。</p>
      <a href="#">關於彌生</a>
  </div>
</div>
<!-- Welcome Section End -->



<!-- Amenities Section Start -->
<div id="amenities">
  <div class="container">
      <div class="section-header">
          <h2>設施與服務</h2>
          <p>
              我們相信謹慎而堅定的取捨，才能提供顧客最好的消費體驗與滿意度，誠摯地邀請您造訪彌生。
              希望我們全心全意的款待，能為您留下一個美好的回憶。
          </p>
      </div>
      <div class="row">
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-2"></i>
                  <h3>智慧型房控</h3>
                  <p>除提供您貼心的人員服務外，每間客房還設有智慧型房控系統，提供e化飯店的高科技服務品質。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-3"></i>
                  <h3>三養湯屋</h3>
                  <p>獨享半露天的35坪湯泉，日式禪風造景，隱密的私人空間，讓緊繃的身、心、靈全然自在放鬆。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-4"></i>
                  <h3>親水主題館</h3>
                  <p>讓您盡情享受多元化的玩水樂趣：溫泉SPA區、動靜水療區、兒童親水區、室內泳池、日光浴平台。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-6"></i>
                  <h3>液晶電視</h3>
                  <p>提供HBO、Discovery、衛視體育、NHK與鳳凰衛視等眾多頻道，也可觀看Netfix以及連接遊戲主機。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-7"></i>
                  <h3>免費WiFi</h3>
                  <p>免費高速光纖無線上網，可借用變壓器、電源插座轉換頭、手機充電器以及HDMI線，讓旅程更加便利。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-8"></i>
                  <h3>值班管家</h3>
                  <p>提供貴賓無拘無束的渡假空間，貼心的One Step Service，直撥分機2，值班管家提供您各種服務。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-9"></i>
                  <h3>醫療級環境清潔</h3>
                  <p>醫療級紫外線殺菌燈進行客房環境清潔，空調設備加裝UVC紫外線燈組，消毒空氣中的細菌。</p>
              </div>
          </div>
          <div class="col-md-3 col-sm-6">
              <div class="item">
                  <i class="icon icon-10"></i>
                  <h3>咖啡廳＆酒吧</h3>
                  <p>坐擁日月潭的綠水煙波，盡情享用各式咖啡、水果、甜點及熱食等，提供在日月潭畔享受美好時光。</p>
              </div>
          </div>
      </div>
  </div>
</div>
<!-- Amenities Section Start -->

<!-- Room Section Start -->
<div id="rooms">
    <div class="container">
        <div class="section-header">
            <h2>精緻客房</h2>
            <p>
                滿足人性暢覽遠山近水的視覺極致，連接水色波光的規劃理念，格外展現出崇尚天然的設計感。面湖視野將美景融入房內，並為客房注入滿帶溫情的元素，更讓悠閒的假期顯得難能可貴。
            </p>
        </div>
        <div class="row">
            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-1.jpg" %>">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>湖景房</h3>
                            <h1>$2,500<span>/ 晚</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>平均14坪 / 46平方公尺  </li>
                                <li><i class="fa fa-arrow-right"></i>一張雙人床 </li>
                            </ul>
                            <ul class="room-icon">
                                <li class="icon-1"></li>
                                <li class="icon-2"></li>
                                <li class="icon-3"></li>
                                <li class="icon-4"></li>
                                <li class="icon-5"></li>
                                <li class="icon-6"></li>
                                <li class="icon-7"></li>
                                <li class="icon-8"></li>
                                <li class="icon-9"></li>
                                <li class="icon-10"></li>
                            </ul>
                            <div class="room-link">
                                <a href="#" data-toggle="modal" data-target="#modal-id">了解更多</a>
                                <a href="#">立即預訂</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6 d-block d-md-none">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-2.jpg" %>">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>山景房</h3>
                            <h1>$2,200<span>/ 晚</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>平均13坪 / 43平方公尺 </li>
                                <li><i class="fa fa-arrow-right"></i>二張雙人床 </li>
                            </ul>
                            <ul class="room-icon">
                                <li class="icon-1"></li>
                                <li class="icon-2"></li>
                                <li class="icon-3"></li>
                                <li class="icon-4"></li>
                                <li class="icon-5"></li>
                                <li class="icon-6"></li>
                                <li class="icon-7"></li>
                                <li class="icon-8"></li>
                                <li class="icon-9"></li>
                                <li class="icon-10"></li>
                            </ul>
                            <div class="room-link">
                                <a href="#" data-toggle="modal" data-target="#modal-id">了解更多</a>
                                <a href="#">立即預訂</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 d-none d-md-block">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-2.jpg" %>">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-3.jpg" %>">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>湖景套房</h3>
                            <h1>$3,500<span>/ 晚</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>平均26坪 / 86平方公尺 </li>
                                <li><i class="fa fa-arrow-right"></i>一大床+榻榻米</li>
                            </ul>
                            <ul class="room-icon">
                                <li class="icon-1"></li>
                                <li class="icon-2"></li>
                                <li class="icon-3"></li>
                                <li class="icon-4"></li>
                                <li class="icon-5"></li>
                                <li class="icon-6"></li>
                                <li class="icon-7"></li>
                                <li class="icon-8"></li>
                                <li class="icon-9"></li>
                                <li class="icon-10"></li>
                            </ul>
                            <div class="room-link">
                                <a href="#" data-toggle="modal" data-target="#modal-id">了解更多</a>
                                <a href="#">立即預訂</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<!-- Room Section End -->

<!-- Modal for Room Section Start -->
<div id="modal-id" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg">
      <div class="modal-content">
          <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
              <div class="row">
                  <div class="col-12">
                      <div class="port-slider">
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-1.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-2.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-3.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-4.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-5.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-6.jpg" %>"></div>
                      </div>
                      <div class="port-slider-nav">
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-1.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-2.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-3.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-4.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-5.jpg" %>"></div>
                          <div><img src="<%=request.getContextPath()+"/resources/img/room-slider/room-6.jpg" %>"></div>
                      </div>
                  </div>
                  <div class="col-12">
                      <h2>Lorem ipsum dolor</h2>
                      <p>
                          Lorem ipsum dolor viverra purus imperdiet rhoncus imperdiet. Suspendisse vulputate condimentum ligula sollicitudin hendrerit. Phasellus luctus, elit et ultrices interdum, neque mi pellentesque massorci. Nam in cursus ex, nec mattis lectus. Curabitur quis elementum nunc. Mauris iaculis, justo eu aliquam sagittis, arcu eros cursus libero, sit amet eleifend dolor odio at lacus. 
                      </p>
                      <div class="modal-link">
                          <a href="#">Book Now</a>
                      </div>
                  </div>
              </div>
          </div>
      </div>
  </div>
</div>
<!-- Modal for Room Section End -->



<!-- Call Section Start -->
<div id="call-us">
    
  <div class="container">
      <div class="section-header">
          <h2>“日月潭最佳湖景酒店”</h2>
          <div class="trivago-img">
            <img src="<%=request.getContextPath()+"/resources/img/tripadivisor.png" %>">
        </div>
        <p>
          TripAdvisor 評選<br>
          "服務人員發自內心的熱情服務讓住宿經驗更物超所值！" Ting-Chieh L.
      </p>
  </div>
  <div class="row">
      <div class="col-12">
          <a href="reviews.html">查看更多評價</a>
      </div>

  </div>

</div>
</div>
<!-- Call Section End -->

<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>
</body>
</html>