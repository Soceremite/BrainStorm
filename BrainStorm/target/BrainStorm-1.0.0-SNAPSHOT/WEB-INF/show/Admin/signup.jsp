<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif] ;-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${options.optionSiteTitle} &lsaquo; 注册</title>
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="/img/logo.png">
    <link rel='stylesheet' id='dashicons-css'  href='/plugin/login/dashicons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='buttons-css'  href='/plugin/login/buttons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='forms-css'  href='/plugin/login/forms.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='l10n-css'  href='/plugin/login/l10n.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='login-css'  href='/plugin/login/login.min.css' type='text/css' media='all' />
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background-color: #81d8cf;
            width:100%;
            height:100%;
        }
        .login h1 a {
            background: url(/img/BrainStorm.jpg) no-repeat;
            background-size: 100px 100px;
            border-radius: 50%;
            width: 100px;
            height: 100px;
            padding: 0;
            margin: 0 auto 1em;
        }
        .login form {
            background: #fff;
        //background: rgba(255, 255, 255, 0.6);
            border-radius: 20px;
            border: 1px solid #fff;
        }
        .login label {
            color: #000;
            font-weight: bold;
        }

        #backtobrainstorm a, #nav a {
            color: #000 !important;
        }

    </style><meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
<div id="login">
    <h1><a href="/" title="sign up for BrainStorm！" tabindex="-1">${options.optionSiteTitle}</a></h1>
    <form name="signupForm" id="signupForm"  method="post">
        <p>
            <label for="user_name">用户名<br />
                <input type="text" name="username" id="user_name"  required
                       placeholder="" autocomplete="off"
                       class="input" onblur="checkUserName()"/>
            </label>

        </p>
        <p>
            <label for="user_email">邮箱<br />
                <input type="text" name="userEmail" id="user_email" required
                       placeholder="" autocomplete="off"
                       class="input" onblur="checkUserEmail()"/>
            </label>
        </p>
        <p>
            <label for="user_pass">密码<br />
                <input type="password" name="password" id="user_pass" required
                       placeholder="" autocomplete="off" min="3" max="20"
                       class="input"  />
            </label>
        </p>
        <p>
            <label for="user_confirm_pass">确认密码<br />
                <input type="password" name="confirm_password" id="user_confirm_pass" required
                       placeholder="" autocomplete="off" min="3" max="20"
                       class="input"  />
            </label>
        </p>
        <p class="forgetmenot"><label for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="1" checked /> 记住我</label></p>
        <p class="submit">
            <input type="button" name="wp-submit" id="submit-btn" class="button button-primary button-large" value="注册" />
        </p>
    </form>

    <p id="backtologin"><a href="/login"> &larr; 登录</a></p>

</div>


<div class="clear"></div>

<script type="text/javascript" src="/plugin/layui/layui.js"></script>
<script type="text/javascript" src="/js/back.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

    $("#submit-btn").click(function () {
        var username = $("#user_name").val();
        var userEmail = $("#user_email").val();
        var password = $("#user_pass").val();
        var confirm_password=$("#user_confirm_pass").val();
        if(username=="") {
            alert("用户名不可为空!");
        }else if(userEmail==""){
            alert("用户邮箱不可为空");
        }else if(password=="") {
            alert("密码不可为空!");
        } else if(confirm_password=="") {
            alert("确认密码不能为空");
        } else if(confirm_password!=password){
            alert("两次密码不一致");
        } else {
            $.ajax({
                url: '/signupSubmit',
                type: 'post',
                dataType: 'text',
                data: {
                    userName: $('#user_name').val(),
                    userEmail:$('#user_email').val(),
                    userPass: $('#user_pass').val(),
                },
                success: function (data) {
                    alert("注册成功");
                },
                error: function () {
                    alert("注册失败");
                }
            })
        }
    })

</script>
</body>

</html>

