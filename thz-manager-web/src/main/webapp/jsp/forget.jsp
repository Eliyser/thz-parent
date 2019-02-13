<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<html>
  <head>   
      
    <title>forget</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  

  	<script type="text/javascript" src="../js/check.js"></script>
  	<script type="text/javascript">
  	function froozen2(){
		var check=document.getElementById("check");
		var email= document.getElementById("email").value;
		var userName= document.getElementById("userName").value;
		var number= document.getElementById("number").value;
		if(userName.length==0){
			alert("请填写用户名！");
			return;
		}
		if(number.length==0){
			alert("请填写手机号！");
			return;
		}
		if(email.length==0){
			alert("请填写邮箱！");
			return;
		}
		document.getElementById("form17").submit();
		check.disabled="disable";//关闭按钮
		check.value="30s后重新发送...";
		setTimeout(function () { //等候
			check.disabled=false;
		}, 1000000);
		//check.value="获取验证码";
		return;
	}
  </script>
  
</head>  
    
<body>
  <div style="padding-left: 400px"></div>
	<div style="padding-top:10px;padding-left:400px;width:80%;;height:80%">  
    <form id="form17" action="${pageContext.request.contextPath}/forgetPwd_check" method="post" style="padding-top:80px;padding-left:300px;background-color:#F89C88;width:60%;height:80%">
    	<font size="5" style="padding-top:50px;padding-left:140px;">找回密码</font>
    	<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        请输入用户名：&nbsp;&nbsp;<input type="text" value="${username }" name="username" id="username" maxlength="8" onBlur="checkUserName()"><br/>
    	<br> 请输入绑定的手机：&nbsp;&nbsp;<input type="text" value="${phone }" name="phone" id="phone" maxlength="11" onBlur="checkNumber()"></br>
     	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入新密码：&nbsp;&nbsp;<input type="password" value="${password }" name="password" id="password" maxlength="12" onBlur="vilidate()"><br/>
    	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认密码：&nbsp;&nbsp;<input type="password" value="${rpsw }" name="rpsw"  maxlength="12"><br/>
     	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         输入验证码：&nbsp;&nbsp;<input type="text" name="verifyCode" id="verifyCode" >
		<input type="button" id="check" name="check" value="获取验证码" onclick="froozen2()"></br>
    	<input type="hidden" value="forget" name="action" id="action"/>
    	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    		&nbsp;
    		<input type="submit" id="yes" name="yes" value="确定">
    	<br><br><font color="red" size="2"> ${message }</font>
    </form>  
  </div> 
</body>  

</html>