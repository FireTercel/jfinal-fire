package com.demo.workmate;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Workmate extends Model<Workmate> {
	
	public static final Workmate dao=new Workmate();
	
	public Page<Workmate> paginate(int pageNumber,int pageSize){
		return paginate(pageNumber, pageSize, "select *", "from workmate order by id asc");
	}
}
