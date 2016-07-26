<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../resources/js/order.js" type="text/javascript"></script>
<!-- <script src="../resources/js/jquery-1.9.0.js" type="text/javascript"></script> -->
<script src="../resources/js/jquery-3.1.0.js" type="text/javascript"></script>
<title>Order - test!</title>
</head>
<body>
<div>
   <h2>Hello_Factory</h2>
   <div>
      <label for="inputBox">Title</label>
      <input type="text" id="inputBox"/>
      <label for="inputBox2">Content</label>
      <input type="text" id="inputBox2"/>
      <label for="inputBox3">Author</label>
      <input type="text" id="inputBox3"/>
      <label for="inputBox4">idx</label>
      <input type="text" id="inputBox4"/>
      <button type="button" id="bt1" onClick="buttonName_click()">Insert</button>
      <button type="button" id="bt2" onClick="buttonName_click2()">Select</button>
      <button type="button" id="bt3" onClick="buttonName_click3()">update</button>
   </div>
   <div id="div1">   
   </div>
</div>
</body>
</html>