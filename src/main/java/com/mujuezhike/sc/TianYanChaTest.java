package com.mujuezhike.sc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TianYanChaTest {

	public static void main(String args[]) throws IOException, InterruptedException {
		
		File file = new File("D://a.txt");
		OutputStream o = new FileOutputStream(file); 

		List<String> tlist = new ArrayList<String>();
		tlist.add("504768665");
		tlist.add("848980405");
		tlist.add("2326147439");
		tlist.add("1240063524");
		tlist.add("5776123");
		tlist.add("3015602788");
		tlist.add("28747086");
		tlist.add("2312515244");
		tlist.add("1066152312");
		tlist.add("321977964");
		tlist.add("1613956326");
		tlist.add("33538340");
		
		
		//3264311
		for (int i = 0; i < tlist.size(); i++) {

			String code = tlist.get(i);
			try {
				o.write(code.getBytes());
				o.write("\r\n".getBytes());
				String name = findName(code);
				o.write(name.getBytes());
				o.write("\r\n".getBytes());
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static String findName(String num) throws ClientProtocolException, IOException {
		
		
		 
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		//ProxyConfig proxyConfig = new ProxyConfig();
		//proxyConfig.setProxyHost("121.31.146.28");
		//proxyConfig.setProxyPort(8123);
		//webClient.getOptions().setProxyConfig(proxyConfig);

		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setTimeout(20000);
		//webClient.getOptions()
		webClient.setJavaScriptTimeout(20000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(20000);

		HtmlPage page = webClient.getPage("http://www.tianyancha.com/company/" + num);
		try {
			Thread.sleep(4000);
			System.out.println(333333);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DomNodeList<DomElement> list = page.getElementsByTagName("span");
		for (int i = 0; i < list.size(); i++) {

			DomElement de = list.get(i);
			String uim = de.getAttribute("class");
			if (uim.equals("f18 in-block vertival-middle")) {
				System.out.println("|||||||||||||||||========================42423432423" + de.getTextContent());
				String m = de.getTextContent();
				return m;

			}
		}
		
		return "";

	}

}
