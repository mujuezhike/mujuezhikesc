package com.mujuezhike.sc.spider.ipcontrol;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


@SuppressWarnings("deprecation")
public class SearchChinaAppJieShao {
	
	public static int mmek = 1;
//	private static String[] strs = {
//				"http://www.tianyancha.com/company/2353003534",
//				"http://www.tianyancha.com/company/1573941867",
//				"http://www.tianyancha.com/company/2342853020",
//				"http://www.tianyancha.com/company/2320991369",
//				"http://www.tianyancha.com/company/10011163",
//				"http://www.tianyancha.com/company/412134123",
//				"http://www.tianyancha.com/company/2965843677",
//				"http://www.tianyancha.com/company/507597961",
//				"http://www.tianyancha.com/company/107742559",
//				"http://www.tianyancha.com/company/1569353637",
//				"http://www.tianyancha.com/company/2386152253",
//				"http://www.tianyancha.com/company/2347380355",
//				"http://www.tianyancha.com/company/24737218",
//				"http://www.tianyancha.com/company/6190577",
//				"http://www.tianyancha.com/company/1489624914",
//				"http://www.tianyancha.com/company/1020652957",
//				"http://www.tianyancha.com/company/1235352165",
//				"http://www.tianyancha.com/company/834036114",
//				"http://www.tianyancha.com/company/346377121",
//				"http://www.tianyancha.com/company/2446658989",
//				"http://www.tianyancha.com/company/2316391462",
//				"http://www.tianyancha.com/company/1247140961",
//				"http://www.tianyancha.com/company/627364375",
//				"http://www.tianyancha.com/company/323387870",
//				"http://www.tianyancha.com/company/146023447",
//				"http://www.tianyancha.com/company/220616887",
//				"http://www.tianyancha.com/company/400623165",
//				"http://www.tianyancha.com/company/24508034",
//				"http://www.tianyancha.com/company/1273093215",
//				"http://www.tianyancha.com/company/2960565969",
//				"http://www.tianyancha.com/company/3504174",
//				"http://www.tianyancha.com/company/277799587",
//				"http://www.tianyancha.com/company/80191107",
//				"http://www.tianyancha.com/company/256299571",
//				"http://www.tianyancha.com/company/14128433",
//				"http://www.tianyancha.com/company/104886642",
//				"http://www.tianyancha.com/company/3039979713",
//				"http://www.tianyancha.com/company/491696388",
//				"http://www.tianyancha.com/company/6009707",
//				"http://www.tianyancha.com/company/2394895860"
//	};
//
//	
//	static int eer = 0;

	public static void main(String args[]) throws IOException, InterruptedException {
		
		//DynamicIpContainer.init();
		//Thread.sleep(15000);
		//3264311
		for (int s = 160383; s < 999999;) {
			Thread.sleep(100);
			try {
				
				Long emunu = findName("https://www.chinapp.com/pinpai/jieshao/"+s);
				if(emunu!=null && emunu>0){
					
					s++;
					
				}else{
//					System.out.println("changeip::}}");
//					eer++;
//					if(eer >= DynamicIpContainer.dynamicips.size()){
//						eer = 0;
//					}
					s++;
				}
				
				//Thread.sleep(2000);
				
			} catch (Exception e) {
				
//				e.printStackTrace();
//				System.out.println("changeip::}}");
//				eer++;
//				if(eer >= DynamicIpContainer.dynamicips.size()){
//					eer = 0;
//				}
				s++;
			}
		}

	}

	public static Long findName(String url) throws ClientProtocolException, IOException, InterruptedException {
		
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		
		webClient.setHTMLParserListener(null);

		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("140.255.236.138");
		// proxyConfig.setProxyPort(8118);
		// webClient.getOptions().setProxyConfig(proxyConfig);
//		String ipport = DynamicIpContainer.dynamicips.get(eer);
//		if (ipport != null) {
//			ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]));
//			webClient.getOptions().setProxyConfig(proxyConfig);
//		}else {
//			System.out.print(" ");
//			webClient.close();
//			return null;
//		}

		webClient.getOptions().setCssEnabled(false);//origin true
		webClient.getOptions().setJavaScriptEnabled(false);//origin true
		webClient.getOptions().setTimeout(3000);
		webClient.setJavaScriptTimeout(3000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(3000);

		String codenum = url.substring(url.lastIndexOf("/")+1);
		HtmlPage page = webClient.getPage(url);
		try {
			Thread.sleep(100);
			System.out.println("scajs");
			String s = page.asXml();
			
				getFileFromBytes(s,"C:\\zkbean\\ppjs20171120\\"+codenum+".html");
				webClient.close();
				//mmek++;
				return 1l;
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		webClient.close();
		//Thread.sleep(2000);
		return 1l;
		
	}
	
	public static Long findNameStatic(String url) throws ClientProtocolException, IOException, InterruptedException {
		
		HttpClient httpClient = new SystemDefaultHttpClient();
		String response = "";
				 
	    HttpGet httpGet = new HttpGet(url);
//	    String ipport = DynamicIpContainer.dynamicips.get(eer);
//		if (ipport != null) {
//			HttpHost proxy = new HttpHost(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]),"http");
//		    RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1000 * 60).build();
//		    httpGet.setConfig(config);
//		}else{
//			System.out.print(" ");
//			return null;
//		}
	
		String codenum = url.substring(url.lastIndexOf("/")+1); 
		System.out.println("loading"+codenum);
	    HttpResponse httpResponse = httpClient.execute(httpGet);	
		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("140.255.236.138");
		// proxyConfig.setProxyPort(8118);
		// webClient.getOptions().setProxyConfig(proxyConfig);
		try {
			Thread.sleep(100);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
                //System.out.println("访问成功");
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                //getFileFromBytes(response,"E:\\webspider\\u.html");
                //System.out.println(response);
                
                    //if(response.contains("f18 in-block vertival-middle")){
                
	    				getFileFromBytes(response,"C:\\zkbean\\"+codenum+".html");
	    				//mmek++;
	    				return 1l;
	    				
	    			//}else{
	    			//	Random rd = new Random();
	    			//	int em = rd.nextInt(10);
	    			//	getFileFromBytes(response,"C:\\zkbean\\eerr"+em+".html");
	    			//	return 0l;
	    			//}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Thread.sleep(2000);
		return 0l;
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
