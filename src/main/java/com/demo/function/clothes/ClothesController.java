package com.demo.function.clothes;

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
		Clothes clothes=Clothes.dao.findById(getParaToInt());
		
		List<Clothimage> clothimagelist=clothes.getClothimages();
		
		setAttr("clotheslist",clothes);
		setAttr("clothimagelist", clothimagelist);
		render("clothimagelist.html");
	}

	public void clothbootstrap() {
		
		
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
