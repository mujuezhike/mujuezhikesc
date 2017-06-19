package com.mujuezhike.sc;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebConsole;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ZhengFuTest {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		WebClient webClient = new WebClient(BrowserVersion.CHROME);

		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("119.90.248.245");
		// proxyConfig.setProxyPort(9999);
		// webClient.getOptions().setProxyConfig(proxyConfig);

		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setTimeout(10000);
		webClient.setJavaScriptTimeout(20000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(20000);

		HtmlPage page = webClient.getPage("http://www.gsxt.gov.cn/index.html");
		ScriptResult sr = page.executeJavaScript("$('#keyword').val('拟定');");
		System.out.println(sr.getJavaScriptResult().toString());
		ScriptResult srb = page.executeJavaScript("$('#keyword').val();");
		System.out.println(srb.getJavaScriptResult().toString());
		ScriptResult srp = page.executeJavaScript("$('#btn_query').click();");
		System.out.println(srp.getJavaScriptResult().toString());
		WebConsole er = webClient.getWebConsole();
		
	}

}
