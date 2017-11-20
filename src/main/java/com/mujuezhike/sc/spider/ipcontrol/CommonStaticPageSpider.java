package com.mujuezhike.sc.spider.ipcontrol;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class CommonStaticPageSpider {
	
	public static Boolean savePage(String url,File dir) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		
		webClient.setHTMLParserListener(null);
		webClient.getOptions().setCssEnabled(false);//origin true
		webClient.getOptions().setJavaScriptEnabled(false);//origin true
		webClient.getOptions().setTimeout(2500);
		webClient.setJavaScriptTimeout(2500);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(2500);
		
		CookieManager ck = new CookieManager();
		Cookie cookie = new Cookie("www.qichacha.com", "hasShow", "1");
		
		Cookie cookie2 = new Cookie("g.alicdn.com", "_uab_collina", "151081792735702599772403");
		Cookie cookie3 = new Cookie("www.qichacha.com", "_uab_collina", "151081889818044790002144");
		Cookie cookie4 = new Cookie("g.alicdn.com", "_umdata", "A502B1276E6D5FEF3001584AF99821BE2459495D775ECD5EE6C9D54B9D75731688B240F034C62EA1CD43AD3E795C914C2053C5B223499DBB86DB1D634ACDF7BC");
		Cookie cookie5 = new Cookie("www.qichacha.com", "_umdata", "ED82BDCEC1AA6EB9579BA636C6A89D2ECE6D565E7920CF2FD3724230DF3DB73C452052A5B795CBB1CD43AD3E795C914CEC86D9ACAB3B7341FDBC81787707D5A5");
		ck.addCookie(cookie);
		ck.addCookie(cookie2);
		ck.addCookie(cookie3);
		ck.addCookie(cookie4);
		ck.addCookie(cookie5);
		webClient.setCookieManager(ck);

		String codenum = url.substring(url.lastIndexOf("/")+1);
		codenum = codenum.substring(codenum.lastIndexOf("=")+1);
		HtmlPage page = webClient.getPage(url);
		
		try {
			Thread.sleep(100);
			System.out.println("qcc");
			String s = page.asXml();
			
				getFileFromBytes(s,dir.getAbsolutePath()+ "\\"+codenum+".html");
				webClient.close();
				
				return true;
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
		webClient.close();
		return false;
		
	}
	
	/**  
	  * 将String数据存为文件  
	  */  
	 public static File getFileFromBytes(String str,String path) {  
	     
		 byte[] b=str.getBytes();  
	     
	     BufferedOutputStream stream = null;  
	     File file = null;  
	     try {  
	         file = new File(path);  
	         //20170705
	         if(file.exists()){
	        	 return file;
	         }
	         FileOutputStream fstream = new FileOutputStream(file);  
	         stream = new BufferedOutputStream(fstream);  
	         stream.write(b);  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     } finally {  
	         if (stream != null) {  
	             try {  
	                 stream.close();  
	             } catch (IOException e1) {  
	                 e1.printStackTrace();  
	             }  
	         }  
	     }  
	     return file;  
	     
	 } 

}
