package com.demo.function.user;

import cn.dreampie.routebind.ControllerKey;

import com.demo.function.user.model.Role;
import com.jfinal.core.Controller;

@ControllerKey(value="/autho",path="/page/app/autho")
public class AuthoController extends Controller{
	
	public void index(){
		setAttr("roletree", Role.dao.findAll());
		render("roles.html");
	}

}
