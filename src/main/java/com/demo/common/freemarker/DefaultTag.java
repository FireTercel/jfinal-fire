package com.demo.common.freemarker;

import freemarker.template.SimpleHash;

public class DefaultTag extends SimpleHash {
	
	public DefaultTag(){
		put("upper", new UpperDirective());
		put("repeat", new RepeatDirective());
	}

}
