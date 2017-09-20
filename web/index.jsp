<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wong
  Date: 2017/9/19
  Time: 下午6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>这是一个登陆界面</title>
</head>
<body>
<form action="doLogin" method="post">
    用户名:<input type="text" name="username">${errors.username[0]}<br>
    密码:<input type="password" name="psw">${errors.psw[0]}<br>
    用户类型:<select name="type">
    <option value="普通用户">普通用户</option>
    <option value="管理员">管理员</option>
</select>
    <br>
    验证码：<input type="text" name="checkcode"  id="checkcode"/>${errors.check[0]}${errors.checkcode[0]}
    <img src="imageAction.action" onclick="this.src='imageAction.action'" title="点击图片刷新验证码"/><br>
    <input type="submit" value="确认">

</form>
</body>
</html>
