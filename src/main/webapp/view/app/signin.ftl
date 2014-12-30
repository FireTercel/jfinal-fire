<#include "/view/main/layout.tpl.ftl"/>
<!-- build:js javascript/main.js -->
<script data-main="/javascript/main" src="/webjars/requirejs/2.1.14/require.min.js" charset="utf-8"></script>
<!-- endbuild -->
<@layout activebar="index" html_title="首页">
<form class="form-horizontal signin" role="form" method="post" action="/signin">
  <div class="form-group">
    <label class="col-sm-2 control-label">用户名:</label>

    <div class="col-sm-8">
      <input class="form-control" type="text" name="username" value="${(username)!}" placeholder="用户名" required>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">密码:</label>

    <div class="col-sm-8">
      <input class="form-control" type="password" name="password" value="${(password)!}" placeholder="密码" required>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">验证码:</label>

    <div class="col-sm-8">
      
    </div>
  </div>

  <div class="form-group">
    <label class="col-sm-2 control-label"></label>

    <div class="col-sm-8">
      <div class="checkbox">
        <label>
          <input type="checkbox" name="rememberMe" value="remember-me">记住我
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label"></label>

    <div class="col-sm-8">
      <@shiro.isLoginFailure name="shiroLoginFailure">
        <div class="alert alert-danger" style="background-image: none;">
          <@shiro.loginException name="shiroLoginFailure"/>
        </div>
      </@shiro.isLoginFailure>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label"></label>

    <div class="col-sm-8">
      <button type="reset" class="btn btn-default">重&nbsp;置</button>
      &nbsp;&nbsp;
      <button type="submit" class="btn btn-primary">登&nbsp;录</button>
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label"></label>

    <div class="col-sm-8">
      Github大量jfinal插件库，<a href="https://github.com/Dreampie">Dreampie</a><br>
      本项目源码，<a href="https://github.com/Dreampie/jfinal-demo">jfinal-demo</a><br>
      本项目帐号，admin/shengmu
    </div>
  </div>
</form>

</@layout>

