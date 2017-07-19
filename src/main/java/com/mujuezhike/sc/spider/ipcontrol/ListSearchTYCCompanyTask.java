package com.mujuezhike.sc.spider.ipcontrol;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ListSearchTYCCompanyTask implements Runnable{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ListSearchTYCCompanyTask.class);

	private String[] strs = null;
	
	private int eer = 0;
	
	private int page = 1;
	private int totalpage = 1;
	
	public String[] getStrs() {
		return strs;
	}

	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	public int getEer() {
		return eer;
	}

	public void setEer(int eer) {
		this.eer = eer;
	}
	
	@Override
	public void run() {
		
		logger.info(Thread.currentThread().getName()+":start");
		//3264311
				for (int s = 0; s < strs.length; ) {
					 
					String code = Long.toString(s);
					String url = strs[s];
					try {
						
						List<String> namelist = null;
						if(page == 1){
							namelist = findName(url);	
						}else{
							url = url.replace("ola1?", "ola1/p"+page+"?");
							namelist = findName(url);
						}
						
						if(namelist!=null && namelist.size()>0){

							String name = url.substring(7, url.indexOf("."));
							File file = new File("C://zklist//ryn//"+"ry_"+name+"_"+page+".txt");
							OutputStream o = new FileOutputStream(file);
							o.write(code.getBytes());
							o.write("\r\n".getBytes());
							for(int i=0;i<namelist.size();i++){
								o.write(namelist.get(i).getBytes());
								o.write("\r\n".getBytes());
							}
							o.close();
							
							
							if(page >= 5
									|| page >= totalpage ){
								s++;
								page = 1;
							}else{
								page++;
							}
							
							continue;
							
						}else{
							System.out.println("changeip::}}");
							eer++;
							if(eer >= 10){
								eer = 0;
							}
						}
						//o.write(name.getBytes());
						
						//Thread.sleep(2000);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("changeip::}}");
						eer++;
						if(eer >= 10){
							eer = 0;
						}
					}
				}
		
		/** 切换新文件     **/
		//ScheduleController.turnNextFileName();
		
	}
	
public List<String> findName(String url) throws ClientProtocolException, IOException, InterruptedException {
		
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
			webClient.close();
			return null;
		}

		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setTimeout(10000);
		webClient.setJavaScriptTimeout(10000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(10000);

		HtmlPage page = webClient.getPage(url);
		try {
			Thread.sleep(2000);
			System.out.println(333333);
			
			DomNodeList<DomElement> muilist = page.getElementsByTagName("div");
			for (int i = 0; i < muilist.size(); i++) {

				DomElement de = muilist.get(i);
				String uim = de.getAttribute("class");
				if (uim.equals("f26 mb15")) {
					String qwe = de.getTextContent().trim();
					if(qwe!=null && qwe.equals("没有找到相关结果")){
						List<String> list = new ArrayList<String>();
						list.add("blank");
						totalpage = 1;
						webClient.close();
						return list;
					}
				}
				
				if (uim.equals("total ng-binding")) {
					String qwe = de.asXml();
					if(qwe==null || qwe.equals("undefined")){
						totalpage = 1;
					}else{
						String na2me = qwe.substring(qwe.indexOf("</span>")+("</span>".length()), qwe.lastIndexOf("<span>"));
						int totalnum = Integer.parseInt(na2me.trim());
						totalpage = totalnum;
					}
				}
			}
			
//			String qwe = page.executeJavaScript("$('.f26.mb15').html()").getJavaScriptResult().toString();
//			if(qwe!=null && qwe.equals("没有找到相关结果")){
//				List<String> list = new ArrayList<String>();
//				list.add("blank");
//				return list;
//			}
			
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
			
			
			
//			String qweqweqw1 = page.executeJavaScript("$('.total').html();").getJavaScriptResult().toString();
//			if(qweqweqw1==null || qweqweqw1.equals("undefined")){
//				TianYanChaSearchTest.totalpage = 1;
//			}else{
//				String na2me = qweqweqw1.substring(qweqweqw1.indexOf("</span>")+("</span>".length()), qweqweqw1.lastIndexOf("<span>"));
//				int totalnum = Integer.parseInt(na2me.trim());
//				TianYanChaSearchTest.totalpage = totalnum;
//			}
			webClient.close();
			return slist;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Thread.sleep(2000);
		webClient.close();
		return null;
		
	}

}
