package com.demo.function.workmate;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;

import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
@TableBind(tableName="workmate")
public class Workmate extends Model<Workmate> {
	
	public static final Workmate dao=new Workmate();
	
	public Page<Workmate> paginate(int pageNumber,int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from workmate order by id asc");
	}
}
