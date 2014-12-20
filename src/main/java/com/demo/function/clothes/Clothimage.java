package com.demo.function.clothes;

import cn.dreampie.tablebind.TableBind;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
@TableBind(tableName="clothimage")
public class Clothimage extends Model<Clothimage> {
	public static Clothimage dao=new Clothimage();
	
	public Clothes getClothes(){
		return Clothes.dao.findById(get("clothesid"));
	}
}
