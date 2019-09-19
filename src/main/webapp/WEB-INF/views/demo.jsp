<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台管理系统</title>
<meta name="keyword" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="Author" content="zifan">
<meta name="copyright" content="胡桃夹子。All Rights Reserved">

</head>

<body>
	<div>demo jsp ${name}</div>

	<br />
	<form action="${ctx}/userForm" method="post">
		<input name="username" />
		<button type="submit">submit</button>
	</form>

	<br />
	<hr>

	<form action="${ctx}/query" method="post">
		<input name="orderId" />
		<button type="submit">query</button>
	</form>
	   </br>
	      <form action="${ctx}/find" method="post">
	       <input name="mername" />
	       <input name="orderId" />
	      <button type="submit">find</button>
	      
	      </form>
	       
	<hr>
	</form>


</body>
</html>
