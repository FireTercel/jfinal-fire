package com.demo.function.workmate;

import cn.dreampie.routebind.ControllerKey;

import com.jfinal.core.Controller;

@ControllerKey(value="/workmate",path="/page/workmate")
public class WorkmateController extends Controller {
	
	public void index(){
		setAttr("workmatePage", Workmate.dao.paginate(getParaToInt(0, 1), 10));
		render("workmate.html");
	}

}
