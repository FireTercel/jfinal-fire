package com.demo.common.resource;

import freemarker.template.SimpleHash;

public class ResourceTags extends SimpleHash {
	
	public ResourceTags(){
		put("static", new StaticTag());
	}

}
