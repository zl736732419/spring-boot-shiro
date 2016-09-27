<%--
  Created by IntelliJ IDEA.
  User: zhenglian
  Date: 2016/9/25
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
    <title>首页</title>
</head>
<body>

	<shiro:user>
		hello <shiro:principal/>
	</shiro:user>

    ${loginUser.username}<br/>
    this is a test page!
</body>
</html>
