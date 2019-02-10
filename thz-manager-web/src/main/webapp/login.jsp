<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2">
    <meta http-equiv="description" content="This is student page">

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>

</head>

<body>
	<div style="padding-left: 400px"></div>
	<div style="padding-top:10px;padding-left:400px;width:50%;;height:80%">
	<form name="form1" action="${pageContext.request.contextPath}/spring_security_check"  method="post" style="padding-top:-50px;padding-left:100px;background-color:#F89C88;width:125%;height:80%">
		<font size="5" style="padding-top:150px;padding-left:380px;">登录</font>
		<table>
			<tr height="100">
				<td colspan="4"></td>
			</tr>
			<tr height="30">  <!-- 四列 -->
				<td width="35%"></td>
				<td width="15%">用户名：</td>
				<td><input type="text" value="${username }" name="username" id="username" maxlength="15"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="80">
				<td width="35%"></td>
				<td width="15%">密&nbsp;&nbsp;码：</td>
				<td><input type="password" value="${password }" name="password" id="password"/></td>
				<td width="30%"></td>
			</tr>
			<tr height="50">
				<td width="35%"></td>
				<td width="10%"><input type="submit" value="登录"/></td>
				<td width="10%"><input type="button" value="重置" onclick="resetValue()"/></td>
				<td width="10%"></td>
			</tr>
			<tr height="30">
				<td width="35%"></td>
				<td ><a href="${pageContext.request.contextPath}/register"><font color="purple" size="3"><i>点击注册</i></font></a></td>
				<td><a href="#"><font color="purple" size="3"><i>忘记密码</i></font></a></td>
			</tr>
			<tr height="80">
				<td width="35%"></td>
				<td colspan="3">
					<font color="red">${error }</font><!-- el表达式 -->
					<font color="#FA5A50" size="3"> ${message }</font>
				</td>
			</tr>
			<tr height="50">
				<td></td>
			</tr>
		</table>
		</form>

	</div>
</body>

</html>