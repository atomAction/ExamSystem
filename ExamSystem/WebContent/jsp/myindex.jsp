<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="style/style1.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY Index</title>
</head>
<body>
	<div id="clock">
  <div class="hour"> 
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
  </div>
  <div class="hour">
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
    <div class="min"></div>
  </div>
  <div id="alarm"> </div>
  <div id="min"></div>
  <div id="hour"></div>
  <div id="sec"></div>
  <ol>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ol>
</div>
 
<script>
window.requestAnimFrame = (function(){
  return  window.requestAnimationFrame       ||
          window.webkitRequestAnimationFrame ||
          window.mozRequestAnimationFrame    ||
          function( callback ){
              window.setTimeout(callback, 1000 / 60);
          };
})();
 
//initialize the clock in a self-invoking function
(function clock(){ 
    var hour = document.getElementById("hour"),
        min = document.getElementById("min"),
        sec = document.getElementById("sec");
    //set up a loop
    (function loop(){
        requestAnimFrame(loop);
        draw();
    })();
    //position the hands
    function draw(){
        var now = new Date(),//now
            then = new Date(now.getFullYear(),now.getMonth(),now.getDate(),0,0,0),//midnight
            diffInMil = (now.getTime() - then.getTime()),// difference in milliseconds
            h = (diffInMil/(1000*60*60)),//hours
            m = (h*60),//minutes
            s = (m*60);//seconds
        //rotate the hands accordingly
        sec.style.webkitTransform = "rotate(" + (s * 6) + "deg)";
        hour.style.webkitTransform = "rotate(" + (h * 30 + (h / 2)) + "deg)";
        min.style.webkitTransform = "rotate(" + (m * 6) + "deg)";
    } 
})();</script>
</body>
</html>