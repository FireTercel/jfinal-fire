﻿<#include "/page/common/_layout.html"/>
<@layout>
<div class="jumbotron">
<h1>JFinal Demo 项目首页</h1>
<div>
	<p>欢迎来到 JFinal极速开发世界！11111111</p>
	
	<br>
	本Demo采用FreeMarker 作为视图文件，您还可以使用Jsp、Velocity或自定义类型视图。
	点击<a href="/blog"><b>此处</b></a>开始试用Demo。
	<br><br><br>
	<p><a href="/blog" class="btn btn-primary btn-lg" role="button">Learn Demo &raquo;</a></p>
	
	
	<br/>
</div>
<form class="form-signin" role="form" method="post" action="/signin">
	<h2 class="form-signin-heading">请登录</h2>
	<!-- <input class="form-control" type="email" placeholder="用户名" required autofocus> -->
	<input class="form-control" type="text" name="username" value="${(username)!}" placeholder="用户名" required autofocus>
	<input class="form-control" type="password" name="password" value="${(password)!}" placeholder="密码" required>
	<div class="checkbox">
		<label>
			<input type="checkbox" value="remember-me">记住我
		</label>
	</div>
	<@shiro.isLoginFailure name="shiroLoginFailure">
        <div style="background-image: none;">
          <@shiro.loginException name="shiroLoginFailure"/>
        </div>
    </@shiro.isLoginFailure>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
</div>



</@layout>