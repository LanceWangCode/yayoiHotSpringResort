<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LOFT CITY | Responsive Travel & Tourism Template</title>
<%@ include file= "/front-end/framework/include.file" %>


</head>
<body>
<!-- Header Section Start -->
<%@ include file= "/front-end/framework/header.file" %>

<!-- Content Section Start -->
<div id="search" style="background: #f2f2f2;">
    <div class="container">
        <div class="form-row">
            <div class="control-group col-md-3">
                <label>Check-In</label>
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
                <label>Check-Out</label>
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

<!-- Room Section Start -->
<div id="rooms">
    <div class="container">
        <div class="section-header">
            <h2>Apartments & Suites</h2>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas in mi libero. Quisque convallis, enim at venenatis tincidunt.
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
                            <h3>Standard Single</h3>
                            <h1>$150<span>/ Night</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>Size: 260 sq ft </li>
                                <li><i class="fa fa-arrow-right"></i>Beds: 2 Single(s) </li>
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
                                <a href="#" data-toggle="modal" data-target="#modal-id">Read More</a>
                                <a href="#">Book Now</a>
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
                            <h3>Standard Double</h3>
                            <h1>$220<span>/ Night</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>Size: 260 sq ft </li>
                                <li><i class="fa fa-arrow-right"></i>Beds: 2 Single(s) </li>
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
                                <a href="#" data-toggle="modal" data-target="#modal-id">Read More</a>
                                <a href="#">Book Now</a>
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
                            <h3>Premium Single</h3>
                            <h1>$180<span>/ Night</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>Size: 260 sq ft </li>
                                <li><i class="fa fa-arrow-right"></i>Beds: 2 Single(s) </li>
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
                                <a href="#" data-toggle="modal" data-target="#modal-id">Read More</a>
                                <a href="#">Book Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6 d-block d-md-none">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-4.jpg" %>">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>Premium Double</h3>
                            <h1>$300<span>/ Night</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>Size: 260 sq ft </li>
                                <li><i class="fa fa-arrow-right"></i>Beds: 2 Single(s) </li>
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
                                <a href="#" data-toggle="modal" data-target="#modal-id">Read More</a>
                                <a href="#">Book Now</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 d-none d-md-block">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-4.jpg" %>">
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-12 room">
                <div class="row">
                    <div class="col-md-6">
                        <div class="room-img">
                            <img src="<%=request.getContextPath()+"/resources/img/room/room-5.jpg" %>">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="room-des">
                            <h3>Economy Single</h3>
                            <h1>$120<span>/ Night</span></h1>
                            <ul class="room-size">
                                <li><i class="fa fa-arrow-right"></i>Size: 260 sq ft </li>
                                <li><i class="fa fa-arrow-right"></i>Beds: 2 Single(s) </li>
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
                                <a href="#" data-toggle="modal" data-target="#modal-id">Read More</a>
                                <a href="#">Book Now</a>
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
<!-- Content Section End -->

<!-- Footer Section Start -->
<%@ include file= "/front-end/framework/footer.file" %>

</body>
</html>