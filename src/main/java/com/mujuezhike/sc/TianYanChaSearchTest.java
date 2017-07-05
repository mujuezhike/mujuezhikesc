package com.mujuezhike.sc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mujuezhike.sc.spider.ipcontrol.DynamicIpContainer;

public class TianYanChaSearchTest {
	
	static int eer = 0;

	public static void main(String args[]) throws IOException, InterruptedException {
		
		DynamicIpContainer.init();
		Thread.sleep(15000);
		//3264311
		for (long s = 905; s < 100000; s++) {
			 

			String code = Long.toString(s);
			try {
				
				List<String> namelist = findName(code);
				if(namelist!=null && namelist.size()>0){

					File file = new File("C://zklist//"+String.valueOf(s)+".txt");
					OutputStream o = new FileOutputStream(file);
					o.write(code.getBytes());
					o.write("\r\n".getBytes());
					for(int i=0;i<namelist.size();i++){
						o.write(namelist.get(i).getBytes());
						o.write("\r\n".getBytes());
					}
					o.close();
				}else{
					System.out.println("changeip::}}");
					eer++;
					if(eer >= 10){
						eer = 0;
					}
				}
				//o.write(name.getBytes());
				
				Thread.sleep(2000);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static List<String> findName(String searchBean) throws ClientProtocolException, IOException, InterruptedException {
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		
		webClient.setHTMLParserListener(null);

		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("140.255.236.138");
		// proxyConfig.setProxyPort(8118);
		// webClient.getOptions().setProxyConfig(proxyConfig);
		String ipport = DynamicIpContainer.dynamicips.get(eer);
		if (ipport != null) {
			ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]));
			webClient.getOptions().setProxyConfig(proxyConfig);
		}else {
			System.out.print(" ");
			return null;
		}

		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setTimeout(30000);
		webClient.setJavaScriptTimeout(30000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(30000);

		HtmlPage page = webClient.getPage("http://www.tianyancha.com/search?key="+searchBean+"&checkFrom=searchBox");
		try {
			Thread.sleep(4000);
			System.out.println(333333);
			DomNodeList<DomElement> list = page.getElementsByTagName("a");
			List<String> slist = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {

				DomElement de = list.get(i);
				String uim = de.getAttribute("class");
				if (uim.equals("query_name search-new-color sv-search-company")) {
					System.out.println("|||||||||||||||||========================42423432423" + de.getTextContent());
					String m = de.getTextContent();
					String ucm = de.getAttribute("href");
					slist.add(ucm);

				}
			}
			
			return slist;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		return null;
		
	}

}
