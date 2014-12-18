package com.demo.function.workmate;

import com.jfinal.core.Controller;

public class WorkmateController extends Controller {
	
	public void index(){
		setAttr("workmatePage", Workmate.dao.paginate(getParaToInt(0, 1), 10));
		render("workmate.html");
	}

}
