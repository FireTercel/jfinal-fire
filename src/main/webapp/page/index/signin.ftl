<#include "/page/common/_layout.html"/>
<@layout>
<div class="jumbotron">

	<p><a href="/" class="btn btn-primary btn-lg" role="button">Index &raquo;</a></p>

<form class="form-signin" role="form" method="post" action="/signin">
	<h2 class="form-signin-heading">请登录</h2>
	<!-- <input class="form-control" type="email" placeholder="用户名" required autofocus> -->
	<input class="form-control" type="text" name="username" value="${(username)!}" placeholder="用户名" required autofocus>
	<input class="form-control" type="password" name="password" value="${(password)!}" placeholder="密码" required>
	<div>
    <label >验证码:</label>

    <div >
      <input type="text" name="captcha" value="" class="form-control captcha" ng-minlength="4" ng-maxlength="4"
             placeholder="验证码" required>
      <img class="captcha" src="/captcha?width=128&height=45&fontsize=30&time=${.now?time}">
    </div>
  	</div>
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