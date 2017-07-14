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
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SearchTYCCompanyTask implements Runnable{
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SearchTYCCompanyTask.class);
	
	private final static String saveBasePath = "C:\\zkbean\\20170714new\\";

	private String[] strs = null;
	
	private int eer = 0;
	
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
		for (int s = 0; s < strs.length;) {
			
			try {
				
				Long emunu = findName(strs[s]);
				if(emunu!=null && emunu>0){
					
					s++;
					
				}else{
					System.out.println("changeip::}}");
					eer++;
					if(eer >= DynamicIpContainer.dynamicips.size()){
						eer = 0;
					}
				}
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("changeip::}}");
				eer++;
				if(eer >= DynamicIpContainer.dynamicips.size()){
					eer = 0;
				}
			}
		}
		
		/** 切换新文件     **/
		//ScheduleController.turnNextFileName();
		
	}
	
	public Long findName(String url) throws ClientProtocolException, IOException, InterruptedException {
		
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

		webClient.getOptions().setCssEnabled(false);//origin true
		webClient.getOptions().setJavaScriptEnabled(false);//origin true
		webClient.getOptions().setTimeout(8000);
		webClient.setJavaScriptTimeout(8000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(8000);

		String codenum = url.substring(url.lastIndexOf("/")+1);
		HtmlPage page = webClient.getPage(url);
		try {
			Thread.sleep(4000);
			System.out.println(333333);
			String s = page.asXml();
			if(s.contains("f18 in-block vertival-middle")){
				getFileFromBytes(s,saveBasePath+codenum+".html");
				webClient.close();
				return 1l;
				
			}else{
				Random rd = new Random();
				int em = rd.nextInt(10);
				getFileFromBytes(s,saveBasePath+"eerr"+em+".html");
			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webClient.close();
		//Thread.sleep(2000);
		return 0l;
		
	}
	
	@Deprecated
	public Long findNameStatic(String url) throws ClientProtocolException, IOException, InterruptedException {
		
		HttpClient httpClient = new SystemDefaultHttpClient();
		String response = "";
				 
	    HttpGet httpGet = new HttpGet(url);
	    String ipport = DynamicIpContainer.dynamicips.get(eer);
		if (ipport != null) {
			HttpHost proxy = new HttpHost(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]),"http");
		    RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(1000 * 60).build();
		    httpGet.setConfig(config);
		}else{
			System.out.print(" ");
			return null;
		}
	
		String codenum = url.substring(url.lastIndexOf("/")+1);       
	    HttpResponse httpResponse = httpClient.execute(httpGet);	
		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("140.255.236.138");
		// proxyConfig.setProxyPort(8118);
		// webClient.getOptions().setProxyConfig(proxyConfig);
		try {
			Thread.sleep(4000);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
                //System.out.println("访问成功");
                HttpEntity httpEntity = httpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);
                //getFileFromBytes(response,"E:\\webspider\\u.html");
                //System.out.println(response);
                
                    if(response.contains("f18 in-block vertival-middle")){
	    				getFileFromBytes(response,"C:\\zkbean\\20170714\\"+codenum+".html");
	    				return 1l;
	    				
	    			}else{
	    				Random rd = new Random();
	    				int em = rd.nextInt(10);
	    				getFileFromBytes(response,"C:\\zkbean\\20170714\\eerr"+em+".html");
	    				return 0l;
	    			}
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
