package com.demo.function.user;

import java.sql.Timestamp;
import java.util.Date;

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
		String todo=getPara("do");
		HasherInfo hasher = HasherKit.hash(user.getStr("password"));
		if(user.getStr("first_name")==null)
			user.set("first_name", "三角");
		
		user.set("password", hasher.getHashResult())
		.set("salt", hasher.getSalt())
		.set("hasher", hasher.getHasher().value())
		.set("providername", subUser.getStr("username"))
		.set("full_name", user.getStr("last_name") + "." + user.getStr("first_name"));
		result=user.save();
		
		/*if(todo!=null){
			if(todo.equals("delete")&&(user.getLong("id")!=null)){
				user.set("deleted_at", new Timestamp(new Date().getTime())).update();
			}else if(todo.equals("save")){
				HasherInfo hasher = HasherKit.hash(user.getStr("password"));
				if(user.getStr("first_name")==null)
					user.set("first_name", "三角");
				
				user.set("password", hasher.getHashResult())
				.set("salt", hasher.getSalt())
				.set("hasher", hasher.getHasher().value())
				.set("providername", subUser.getStr("username"))
				.set("full_name", user.getStr("last_name") + "." + user.getStr("first_name"));
				result=user.save();
			}else if(todo.equals("update")&&user.getLong("id")!=null){
				user.removeNullValueAttrs();
				Boolean reuse = getParaToBoolean("reuse");
				if(reuse!=null&&reuse)
					user.set("deleted_at", null);
				if(user.getBoolean("last_name")!=null)
					user.set("full_name", user.get("last_name")+"."+user.get("first_name"));
				result=user.update();
			}
		}*/
		
		
		
        
		
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
