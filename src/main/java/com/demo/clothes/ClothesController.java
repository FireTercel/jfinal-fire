package com.demo.clothes;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;

public class ClothesController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("/clothes")
	public void index() {
		setAttr("clothPage", Clothes.dao.paginate(getParaToInt(0, 1), 10));

		render("clothes.html");
	}

	@Before(CacheInterceptor.class)
	@CacheName("/clothes/list")
	public void list() {
		List<Clothes> clotheslist = Clothes.dao.find("select * from clothes");
		setAttr("clotheslist", clotheslist);
		render("clothbootstrap.html");
	}

	public void clothbootstrap() {
		String sql = "select ci.* ,c.clothname from clothimage ci inner join"
				+ " clothes c on ci.clothes_id=c.id where ci.clothes_id=?";
		Clothimage clothimage=Clothimage.dao.findFirst(sql, 5);
		if(clothimage!=null){
			String clothname=clothimage.getStr("clothname");
			System.out.println(clothname);
		}
		render("clothbootstrap.html");
	}

	public void add() {
	}

	@Before({ ClothesValidator.class, EvictInterceptor.class })
	@CacheName("/clothes")
	public void save() {
		getModel(Clothes.class).set("flag", "12").save();

		redirect("/clothes");
	}

	public void edit() {
		setAttr("clothes", Clothes.dao.findById(getParaToInt()));
	}

	@Before({ ClothesValidator.class, EvictInterceptor.class })
	@CacheName("/clothes")
	public void update() {
		getModel(Clothes.class).update();
		redirect("/clothes");
	}

	@Before(EvictInterceptor.class)
	@CacheName("/clothes")
	public void delete() {
		Clothes.dao.deleteById(getParaToInt());
		redirect("/clothes");
	}

}
