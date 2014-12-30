package com.demo.function.user.model;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;

@TableBind(tableName="sec_user")
public class User extends Model<User> {
	
	public static User dao=new User();

}
