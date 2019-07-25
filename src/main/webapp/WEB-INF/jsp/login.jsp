<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
        // 校验用户名和密码是否存在
        var count =0;
        function checkUser(){
            var username=$("#username").val();  // 根据id获取用户名
            var password=$("#password").val();  // 根据id获取密码
            $.ajax({
                type:'post',
                url:'${pageContext.request.contextPath}/checkUser.action',
                data:{"username":username,
                    "password":password},
                dataType:'JSON',
                success:function(data){
                    if(data==0) {
                        alert("用户不存在");
                    }
                    else if(data==1){
                        count++;
                        // 检验密码输入次数，大于3，跳转到注册页面，小于3计数器加1
                        if(count<=3){
                            var n = 3-count;
                            alert("密码错误,你还有"+n+"次机会");}
                        else{
                            alert("密码次数超限，您可以重新注册");
                            location.href="register.action";
                        }
                    }
                    // 跳转到controller 查询所哟的消息内容
                    else location.href="messageAll.action";
                }
            });
        }
	</script>

</head>
<body>
<!-- 登陆界面 -->
<center>
	<form action="${pageContext.request.contextPath}/login" method="post">    <!-- 提交表单到Login登陆判断 -->
		用户名：<input type="text" name="username" id="username" /><br/>
		密码：<input type="password" name="password" id="password" /><br/>
		<input type="button" value="提交" onclick="checkUser()"/>
		<a href="${pageContext.request.contextPath}/register.action">点击注册</a> <!-- 跳转页面 -->
	</form>
</center>
</body>
</html>