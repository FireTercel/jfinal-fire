package com.demo.function.user;

import cn.dreampie.ValidateKit;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		String oldpassword=c.getPara("oldpassword");
		ValidateKit.isNumber(oldpassword);
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated method stub
		
		
	}

}
