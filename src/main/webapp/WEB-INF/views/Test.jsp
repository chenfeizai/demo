<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
       <div>demo jsp ${name}</div>
       </hr>
 
         <form action="${ctx}/requ" method="post">
          <input type=text name="username"/>
          </br>
          <button type="submit">确定</button>
       </form>
        <form action="${ctx}/selbyid" method="post">
          <input type=text name="id"/>
          </br>
          <button type="submit">确定</button>
       </form>
         
     
     
     
</body>
</html>