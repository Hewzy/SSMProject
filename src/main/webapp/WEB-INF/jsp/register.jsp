<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">

        var countdown=3;
        function settime(obj) {
            if (countdown == 0) {
                obj.removeAttribute("disabled");
                obj.value="免费获取验证码";
                countdown = 3;
                this.reloadImage();
                return;
            } else {
                obj.setAttribute("disabled", true);
                obj.value="重新发送(" + countdown + ")";
                countdown--;
            }
            setTimeout(function() {
                    settime(obj) }
                ,1000)
        }

        function reloadImage(){
            document.getElementById('imgCode').src="${pageContext.request.contextPath}/getVerifyCode.action?time="+new Date().getTime();
        }

        //注册用户名是否存在，失去焦点时判断
        function checkName(){
            var username=$("#username").val();
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/checkName.action',
                data:{"username":username},
                dataType:'JSON',
                success:function(data){
                    if(data!=null)
                        alert("用户名已存在");
                }
            });
        }

        //判断验证码是否正确
        function checkSafeCode(){
            var safeCode=$("#safeCode").val();
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/checkSafeCode.action',
                data:{"safeCode":safeCode},
                dataType:'JSON',
                success:function(data){
                    if(data==0){
                        alert("验证码错误！");
                    }
                }
            });
        }

        //判断用户名的手机格式是否正确
        function checkNumber(){
            var number = $("#number").val();
            if(number.length!=11) {
                alert("手机号格式错误");
            }
            else{
                return true;
			}
        }

	</script>
</head>
<body>
<center>
<form action="${pageContext.request.contextPath}/register01.action">     <!-- 提交表单到controller判断是否注册成功 -->
	用户名：<input type="text" name="username" id="username" onblur="checkName()"><br />
	手机号：<input type="text" name="number" id="number" onblur="checkNumber()"><br />
	<input type="button" id="btn" value="免费获取验证码" onclick="settime(this)" />
	<img id="imgCode" src="${pageContext.request.contextPath}/getVerifyCode.action" />	<br />
	验证码： <input type="text" id="safeCode" onblur="checkSafeCode()"><br />
	密码：<input type="password" name="password" id="password"><br />
	<input type="submit" value="注册">
</center>
</form>
</body>
</html>