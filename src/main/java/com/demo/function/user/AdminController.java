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
	
	
	public void save(){
		
		User user=getModel(User.class);
		
		user.save();
		redirect("/admin");
		/*keepModel(User.class);
		User user=getModel(User.class);
		User subUser=SubjectKit.getUser();
		
		HasherInfo passwordInfo = HasherKit.hash(user.getStr("password"), Hasher.DEFAULT);
		user.set("password", passwordInfo.getHashResult());
		user.set("hasher", passwordInfo.getHasher());
		user.set("salt", passwordInfo.getSalt());
		user.set("providername", subUser.get("username"));
		
		if (user.save()) {
			setAttr("state", "success");
			return;
		} else{
			setAttr("state", "failure");
			redirect("/admin");
		}*/
	}
	
	public void query(){
		
	}

}
