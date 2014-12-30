package com.demo.function.clothes;

import java.util.List;

import cn.dreampie.tablebind.TableBind;
import cn.dreampie.web.model.Model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Clothes model
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@TableBind(tableName="clothes")
public class Clothes extends Model<Clothes> {
	
	public static Clothes dao=new Clothes();
	
	/**
	 * 使用Model获取分页
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<Clothes>
	 */
	public Page<Clothes> paginate(int pageNumber,int pageSize){
		return paginate(pageNumber, pageSize, "SELECT *", "FROM clothes ORDER BY id ASC");
	}
	
	/**
	 * 使用Db+Record获取分页
	 * @param pageNumber
	 * @param pageSize
	 * @return Page<Record>
	 */
	public Page<Record> paginateR(int pageNumber,int pageSize){
		return Db.paginate(pageNumber, pageSize, "SELECT *", "FROM clothes ORDER BY id ASC");
	}
	
	
	
	public List<Clothimage> getClothimages(){
		return Clothimage.dao.find("SELECT * FROM clothimage WHERE clothesid=?",get("id"));
	}

}
