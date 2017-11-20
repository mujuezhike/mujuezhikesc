package com.mujuezhike.sc.spider.ipcontrol;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QichachaListSpider {

	public static void main(String[] args) throws Exception{
		
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("文化");
		list.add("南京");
		list.add("北京");
		list.add("化学");
		list.add("医疗");
		list.add("衣服");
		
		for(int i=0;i<list.size();i++) {
			Thread.sleep(3000);
			CommonStaticPageSpider.savePage("http://www.qichacha.com/search?key="+list.get(i), new File("C:\\zkbean\\qcclist"));
		}

	}

}
