<%--
  Created by IntelliJ IDEA.
  User: androidjp
  Date: 2017/8/20
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>

    <script type="text/javascript">
        function reloadImg(){
            var dt = new Date().getTime();
            $("#img").attr("src","/SpringTraining/login/image/get?d="+ dt);
        }
    </script>
</head>
<body>
    用户名：<input type="text" id="input-username"><br>
    密码：<input type="password" id="input-psd"><br>
    验证码：<input type="text" id="input-verifycode"><br>
    <img src="/SpringTraining/login/image/get" id="img"><a href="javascript:reloadImg()">看不清，换一张</a>
    <br>
    <button id="send">登录</button>

    <p id="reply"></p>

    <script type="text/javascript">

        $("#send").on("click",function(){
            //暂时只有验证码验证功能
            var code = $("#input-verifycode").val();

            $.ajax({
                type:"GET",
                url:"/SpringTraining/login/image/verify?verifyCode="+code,

            }).done(function(e){
                if(e.code == 200){
                    $("#reply").html(e.msg);
                }else{
                    alert("异常");
                }
            });
        });

    </script>
</body>
</html>
