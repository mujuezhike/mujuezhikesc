package com.mujuezhike.sc;

public class urlStringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String m1 = "url(http://static.geetest.com/pictures/gt/895656306/895656306.jpg)";
//		
//		m1 = m1.substring(4, m1.length()-1);
//		System.out.println(m1);
		
		String url = "http://www.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company";
		String name = url.substring(7, url.indexOf("."));
		System.out.println(name);
		
		
		String tt = "    <span>共</span>    7    <span>页</span>";
		System.out.println(tt.indexOf("</span>")+("</span>".length()));
		System.out.println(tt.lastIndexOf("<span>"));
		String na2me = tt.substring(tt.indexOf("</span>")+("</span>".length()), tt.lastIndexOf("<span>"));
		String nnm = na2me.trim();
		System.out.println(nnm.length());
		System.out.println(Integer.parseInt(na2me.trim()));
	}

}
