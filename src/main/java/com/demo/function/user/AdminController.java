package com.demo.function.user;

import cn.dreampie.routebind.ControllerKey;
import cn.dreampie.shiro.core.SubjectKit;
import cn.dreampie.shiro.hasher.Hasher;
import cn.dreampie.shiro.hasher.HasherInfo;
import cn.dreampie.shiro.hasher.HasherKit;

import com.demo.function.user.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;

@ControllerKey(value="/admin",path="/page/app/user")
public class AdminController extends Controller {
	
	public void index(){
		setAttr("userPage", User.dao.getUserPage(getParaToInt(0, 1), 10));
		render("user.html");
	}
	
	public void add(){}
	
	@Before(Tx.class)
	public void control(){
		
		keepModel(User.class);
		User user=getModel(User.class);
		User subUser=SubjectKit.getUser();
		boolean result=false;
		
		HasherInfo hasher = HasherKit.hash(user.getStr("password"));
		if(user.getStr("first_name")==null)
			user.set("first_name", "");
		
		user.set("password", hasher.getHashResult())
		.set("salt", hasher.getSalt())
		.set("hasher", hasher.getHasher().value())
		.set("providername", subUser.getStr("username"))
		.set("full_name", user.getStr("last_name") + "." + user.getStr("first_name"));
		result=user.save();
		
        
		
		if (result) {
			setAttr("state", "success");
			redirect("/admin");
		} else{
			setAttr("state", "failure");
			redirect("/admin");
		}
	}
	
	public void query(){
		
	}

}
