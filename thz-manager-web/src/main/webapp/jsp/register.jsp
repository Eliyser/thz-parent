<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head lang="en">
	<title>注册</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"><!-- 兼容   -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />

	<script type="text/javascript" src="../js/check.js"></script>
</head>

<body>

<div style="padding-left: 400px"></div>
	<div style="padding-top:10px;padding-left:400px;width:80%;;height:77%">
    	<form id="form1" action="${pageContext.request.contextPath}/register_check" method="post" style="padding-top:80px;padding-left:300px;background-color:#F89C88;width:60%;height:80%">
    		<font size="5" style="padding-top:150px;padding-left:140px;">注册</font>
    		<br> 请输入用户名：
    		<input type="text" value="${username }" name="username" id="username" maxlength="15" onBlur="checkUserName()" placeholder="请输入用户名"><br/>
    		<br> 请输入手机号：
    		<input type="text" value="${phone }" name="phone" id="phone" maxlength="11" onBlur="checkNumber()" placeholder="请输入手机号"></br>
    		<br> &nbsp;请输入邮箱：&nbsp;&nbsp;
    		<input type="text" value="${email }" name="email" id="email" maxlength="30" onBlur="checkEmail()" placeholder="请输入邮箱账号"></br>
    		<br> &nbsp;请选择性别：&nbsp;&nbsp;
    		<select id="sex" name="sex" style="height:20px;width:172px">
    		    <option value="男" selected="selected">男</option>
    		    <option value="女">女</option>
    		</select>
    		<br> &nbsp;请输入密码：&nbsp;&nbsp;
    		<input type="password" value="${password }" name="password" id="password" maxlength="12" onBlur="checkPsw()" placeholder="请输入密码"><br/>
    		<br>&nbsp;请确认密码：&nbsp;&nbsp;
    		<input type="password" value="${rpsw }" name="rpsw"  maxlength="12" placeholder="请确认密码"><br/>
    		<br>&nbsp;输入验证码：&nbsp;&nbsp;
    		<input type="text" name="code" id="code" style="width:87px" placeholder="请输入验证码">
			<input type="button" id="check" name="check" value="获取验证码" onclick="froozen()"></br>
    		<br><input type="submit" id="regist" name="regist" value="注册" style="padding-left: 130px;padding-right:130px"/>
    		<br><br><font color="red" size="2"> ${message }</font>
    	</form>

    </div>
</body>

</html>