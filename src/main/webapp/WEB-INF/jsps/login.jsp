<%--
  Created by IntelliJ IDEA.
  User: zhenglian
  Date: 2016/9/25
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h2>用户登录</h2>
<span style="color:#ee0000;">${error}</span>
<form action="login" method="post">
    用户名：<input type="text" name="username"/><br/>
    密码: <input type="password" name="password"/><br>
    记住我<input type="checkbox" name="rememberMe"/><br/>
    <input type="submit" value="登录"/>
</form>

</body>
</html>
