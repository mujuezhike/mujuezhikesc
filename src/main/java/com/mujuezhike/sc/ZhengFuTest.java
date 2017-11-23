package com.mujuezhike.sc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import com.gargoylesoftware.htmlunit.AjaxController;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.OnbeforeunloadHandler;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.attachment.AttachmentHandler;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class ZhengFuTest {

	public static void main(String[] args) throws Exception {
		
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52);

		// ProxyConfig proxyConfig = new ProxyConfig();
		// proxyConfig.setProxyHost("119.90.248.245");
		// proxyConfig.setProxyPort(9999);
		// webClient.getOptions().setProxyConfig(proxyConfig);
		/**test==**/
		
		webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {

			@Override
			public void scriptException(HtmlPage page, ScriptException scriptException) {
				// TODO Auto-generated method stub
				System.out.println(scriptException.getMessage());
			}

			@Override
			public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {
				// TODO Auto-generated method stub
				System.out.println(executionTime);
			}

			@Override
			public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {
				// TODO Auto-generated method stub
				System.out.println(malformedURLException.getMessage());
			}

			@Override
			public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {
				// TODO Auto-generated method stub
				System.out.println(exception.getMessage());
			}
			
		});
		webClient.setConfirmHandler(new ConfirmHandler() {

			@Override
			public boolean handleConfirm(Page page, String message) {
				System.out.println("ConfirmHandler::"+page.getUrl());
				System.out.println(message);
				return false;
			}
			
		});
		webClient.setOnbeforeunloadHandler(new OnbeforeunloadHandler() {

			@Override
			public boolean handleEvent(Page page, String returnValue) {
				System.out.println("AttachmentHandler::"+page.getUrl());
				System.out.println(returnValue);
				return false;
			}
			
		});
		webClient.setAttachmentHandler(new AttachmentHandler() {

			@Override
			public void handleAttachment(Page page) {
				System.out.println("AttachmentHandler::"+page.getUrl());	
			}
			
		});
		webClient.addWebWindowListener(new WebWindowListener() {

			@Override
			public void webWindowOpened(WebWindowEvent event) {
				System.out.println(event);
				if(event.getNewPage()!=null) {
					System.out.println("WebWindowEvent::"+event.getNewPage().getUrl());	
				}
				
			}

			@Override
			public void webWindowContentChanged(WebWindowEvent event) {
				System.out.println(event);
				if(event.getNewPage()!=null) {
					System.out.println("WebWindowEvent::"+event.getNewPage().getUrl());	
				}
			}

			@Override
			public void webWindowClosed(WebWindowEvent event) {
				System.out.println(event);
				if(event.getNewPage()!=null) {
					System.out.println("WebWindowEvent::"+event.getNewPage().getUrl());	
				}
			}
			
		});
		
		/**==test**/
		AjaxController ac = new NicelyResynchronizingAjaxController();
		
        webClient.setAjaxController(ac);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setScreenWidth(2048);
		webClient.getOptions().setScreenHeight(1152);
		webClient.getOptions().setDownloadImages(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setActiveXNative(true);
		webClient.getOptions().setTimeout(200000);
		webClient.setJavaScriptTimeout(200000);
		webClient.getJavaScriptEngine().setJavaScriptTimeout(200000);
		//PostponedAction as = new PostponedAction ();		WebWindowListener wwl = new 
		
		
		
		//HtmlPage page = webClient.getPage("http://www.gsxt.gov.cn/index.html");
		WebWindow ww = webClient.openWindow(new URL("http://www.gsxt.gov.cn/index.html"), "window1");
		
		
		webClient.setCurrentWindow(ww);
		int wwx = ww.getInnerHeight();
		int wwy = ww.getInnerWidth();
		System.out.println(wwx+"||"+wwy);
		
		HtmlPage page2 = (HtmlPage) ww.getEnclosedPage();
		
		try {
			Thread.sleep(3000);
			ScriptResult sr = page2.executeJavaScript("$('#keyword').val('统一');");
			System.out.println(sr.getJavaScriptResult().toString());
			ScriptResult srb = page2.executeJavaScript("$('#keyword').val();");
			System.out.println(srb.getJavaScriptResult().toString());
			ScriptResult srp = page2.executeJavaScript("$('#btn_query').click();");
			System.out.println(srp.getJavaScriptResult().toString());
			Thread.sleep(4000);
			
			ScriptResult uneim = page2.executeJavaScript("$('.gt_cut_fullbg_slice').css(\"background-image\")");
			System.out.println(uneim.getJavaScriptResult().toString());
			ScriptResult ndjme = page2.executeJavaScript("$('.gt_cut_bg_slice').css(\"background-image\")");
			System.out.println(ndjme.getJavaScriptResult().toString());
			String m1 = uneim.getJavaScriptResult().toString();
			String m2 = ndjme.getJavaScriptResult().toString();
			m1 = m1.substring(4, m1.length()-1);
			m2 = m2.substring(4, m2.length()-1);
			String n1 = Download(m1);
			String n2 = Download(m2);
			ImageSolve(n1);
			ImageSolve(n2);
			Long[] ris = ImageCompare(n1,n2);
			System.out.println(n2+"-----------------------------");
			Thread.sleep(100);
			page2.executeJavaScript("$('.gt_slider_knob').attr('id','sdsda11');");
			Thread.sleep(100);
			ScriptResult rdertop = page2.executeJavaScript("$('.gt_slider_knob').offset().top;");
			ScriptResult rderleft = page2.executeJavaScript("$('.gt_slider_knob').offset().left;");
			
			System.out.println(rdertop.toString());
			System.out.println(rderleft.toString());
			System.out.println(rdertop.getJavaScriptResult());
			System.out.println(rderleft.getJavaScriptResult());
			Thread.sleep(1000);
			ScriptResult bbbefd2 = page2.executeJavaScript("$('#sdsda11').attr('class');");
			System.out.println(bbbefd2.getJavaScriptResult()+"|"+ris[0]+"|"+ris[1]);
			//page2.executeJavaScript("$('#sdsda11').click(function(e){console.info(e);console.info(this);});");
			//page2.executeJavaScript("$('#sdsda11').click();");
			System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			List<DomElement> mlist = page2.getElementsById("sdsda11");
			
			
			
//			ScriptResult krdertop = page2.executeJavaScript("$('#keyword').offset().top;");
//			ScriptResult krderleft = page2.executeJavaScript("$('#keyword').offset().left;");
//			//System.out.println(tt.asXml());
//			System.out.println(krdertop);
//			System.out.println(krderleft);
//			ScriptResult brdertop = page2.executeJavaScript("$('body').offset().top;");
//			ScriptResult brderleft = page2.executeJavaScript("$('body').offset().left;");
//			System.out.println(brdertop);
//			System.out.println(brderleft);
			//choose_box
//			ScriptResult chooseboxtop = page2.executeJavaScript("$('#choose_box').offset().top;");
//			ScriptResult chooseboxleft = page2.executeJavaScript("$('#choose_box').offset().left;");
//			System.out.println(chooseboxtop);
//			System.out.println(chooseboxleft);
//			ScriptResult rdtop = page2.executeJavaScript("$('.gt_popup_ready').offset().top;");
//			ScriptResult rdleft = page2.executeJavaScript("$('.gt_popup_ready').offset().left;");
//			System.out.println(rdtop);
//			System.out.println(rdleft);
//			ScriptResult rdtop1 = page2.executeJavaScript("$('.gt_popup_box').offset().top;");
//			ScriptResult rdleft1 = page2.executeJavaScript("$('.gt_popup_box').offset().left;");
//			System.out.println(rdtop1);
//			System.out.println(rdleft1);
			
			page2.executeJavaScript("var mm1 = document.createEvent('MouseEvent');mm1.initMouseEvent('mousemove', true, true, mm1.view,0, -363, 512, -363, 417,false, false, false, false,0, null);document.dispatchEvent(mm1);");
			//Thread.sleep(1000);
			page2.executeJavaScript("var md1 = document.createEvent('MouseEvent');md1.initMouseEvent('mousedown', true, true, md1.view,0, -363, 515, -363, 420,false, false, false, false,0, null);document.getElementById('sdsda11').dispatchEvent(md1);");
			//Thread.sleep(1000);
			ScriptResult bbbefd = page2.executeJavaScript("$('#sdsda11').attr('class');");
			System.out.println(bbbefd.getJavaScriptResult()+"|"+ris[0]+"|"+ris[1]);
			ScriptResult b1ddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
			System.out.println(b1ddbbefd.getJavaScriptResult());
			
			ScriptResult qweqweq1w7 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			//Thread.sleep(100);
			System.out.println(qweqweq1w7.getJavaScriptResult());
			
//			//一格一格移动
			for(int i=0;i<=(ris[1]-22);){
				
				int l = i+0;
				Random rd = new Random();
				int yiue= rd.nextInt(10);
				l = i-yiue+5;
				int yiuee= rd.nextInt(10);
				
				page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +i-24 )+", "+( 512-yiuee+5)+", "+(-339 +i-24 )+","+( 417-yiuee+5)+",false, false, false, false,0, null);document.dispatchEvent(mm2);");
				//Thread.sleep(10*yiue);
				
				ScriptResult bddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
				System.out.println(bddbbefd.getJavaScriptResult()+"||"+System.currentTimeMillis());
				int heh = rd.nextInt(100);
				int[] speed = {1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,2,3,3,3,3,3,3,3,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1};
				
				i=i+speed[i%40];
				
			}
			//Thread.sleep(2000);
			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-23-24 )+", 512, "+(-339 +ris[1]-23-24 )+", 417,false, false, false, false,0, null);document.dispatchEvent(mm2);");
			ScriptResult bddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
			System.out.println(bddbbefd.getJavaScriptResult());
			//Thread.sleep(2000);
			
			page2.executeJavaScript("var mu1 = document.createEvent('MouseEvent');mu1.initMouseEvent('mouseup', true, true, mu1.view,0, "+(-339 +ris[1]-23-24 )+", 512, "+(-339 +ris[1]-23-24 )+", 417,false, false, false, false,0, null);document.getElementById('sdsda11').dispatchEvent(mu1);");

			//一格一格移动
//			for(int i=0;i<=(ris[1]-22);){
//				
//				int l = i+0;
//				Random rd = new Random();
//				int yiue= rd.nextInt(4);
//				int ii = yiue + 2;
//				
//				page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+( -339+ris[1]-23-24+ii )+", 512, "+( -339+ris[1]-23-24+ii )+",417,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//				//Thread.sleep(10*yiue);
//				
//				ScriptResult cbddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
//				System.out.println(cbddbbefd.getJavaScriptResult()+"||"+System.currentTimeMillis());
//				int heh = rd.nextInt(100);
//				
//				i++;
//				
//			}
			
//			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-22 )+", 523, "+(-339 +ris[1]-22-22 )+", 428,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//			ScriptResult cddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
//			System.out.println(cddbbefd.getJavaScriptResult());
//			Thread.sleep(19);
//			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-20 )+", 522, "+(-339 +ris[1]-22-20 )+", 427,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//			ScriptResult cddbbefd1 = page2.executeJavaScript("$('#sdsda11').css('left');");
//			System.out.println(cddbbefd1.getJavaScriptResult());
//			Thread.sleep(8);
//			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-14 )+", 523, "+(-339 +ris[1]-22-14 )+", 428,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//			ScriptResult cddbbefd2 = page2.executeJavaScript("$('#sdsda11').css('left');");
//			System.out.println(cddbbefd2.getJavaScriptResult());
//			page2.executeJavaScript("$('#sdsda11').click();");

			
			ScriptResult qweqweqw1 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw1.getJavaScriptResult());
			ScriptResult qweqweqw2 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw2.getJavaScriptResult());
			ScriptResult qweqweqw3 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw3.getJavaScriptResult());
			ScriptResult qweqweqw4 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw4.getJavaScriptResult());
			ScriptResult qweqweqw5 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw5.getJavaScriptResult());
			ScriptResult qweqweqw6 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(3000);
			System.out.println(qweqweqw6.getJavaScriptResult());
			ScriptResult qweqweqw7 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
			Thread.sleep(100);
			System.out.println(qweqweqw7.getJavaScriptResult());
			
			Thread.sleep(10500);
			
			HtmlPage page3 = (HtmlPage) ww.getEnclosedPage();
			doSomeThing(page3);
			
			Thread.sleep(10500);
			
			Set<Cookie> cookies = webClient.getCookies(new URL("http://api.geetest.com"));
		    for(Cookie ck:cookies) {
		    	System.out.println(ck.getName()+":"+ck.getValue());
		    }
			
			HtmlPage page4 = (HtmlPage) ww.getEnclosedPage();
			System.out.println(page4.asXml());
			System.out.println("=====================================================================");
			
			ScriptResult listnum = page4.executeJavaScript("$('.search_list_item').length");
			System.out.println(listnum.getJavaScriptResult()+"|"+ris[0]+"|"+ris[1]);
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		HtmlAttributeChangeListener listener = new BEAttachmentHandler();
//		page.addHtmlAttributeChangeListener(listener);
//		
		
//		ScriptResult uneim = page.executeJavaScript("$('.gt_cut_fullbg_slice').css(\"background-image\")");
//		
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		System.out.println(uneim.getJavaScriptResult().toString());
		return;
		
	}

	 /*** 
     * 下载图片 
     *  
     * @param listImgSrc 
     */  
    private static String Download(String listImgSrc) {  
        try {  
                String imageName = listImgSrc.substring(listImgSrc.lastIndexOf("/") + 1, listImgSrc.length());  
                URL uri = new URL(listImgSrc);  
                InputStream in = uri.openStream();  
                FileOutputStream fo = new FileOutputStream(new File("E://a//"+imageName));  
                byte[] buf = new byte[1024];  
                int length = 0;  
                System.out.println("开始下载:" + listImgSrc);  
                while ((length = in.read(buf, 0, buf.length)) != -1) {  
                    fo.write(buf, 0, length);  
                }  
                in.close();  
                fo.close();  
                System.out.println(imageName + "下载完成");  
                return imageName;
        } catch (Exception e) {  
            System.out.println("下载失败");  
        }
		return "";  
    }  
    
    private static void ImageSolve(String imagename) throws IOException{
    	
    	BufferedImage bi = ImageIO.read(new File("E://a//"+imagename));
 	   int height = bi.getHeight();
 	   int width = bi.getWidth();
 	   System.out.println(height);
 	   System.out.println(width);
 	   
 	   BufferedImage ci = new BufferedImage(312, 116, BufferedImage.TYPE_INT_RGB);
 	   
 	   int[] nums = {157,145,265,277,181,169,241,253,109,97,289,301,85,73,25,37,13,1,121
 			   		,133,61,49,217,229,205,193};
 	   for(int m=0;m<26;m++){
 		   for(int x=0;x<10;x++){
 			   for(int y=0;y<58;y++){
 				   ci.setRGB(x+m*10, y, bi.getRGB(x+nums[m], y+58));
 			   }
 		   }
 	   }
 	   for(int m=0;m<26;m++){
 		   for(int x=0;x<10;x++){
 			   for(int y=0;y<58;y++){
 				   int k = 0;
 				   if(m%2==0){
 					   k = m+1;
 				   }else{
 					   k = m-1;
 				   }
 				   ci.setRGB(x+m*10, y+58, bi.getRGB(x+nums[k], y));
 			   }
 		   }
 	   }
 	   
 	   File file = new File("E://b//"+imagename);
 	   ImageIO.write(ci, "jpg", file);
    	
    }
    
    
    
    private static Long[] ImageCompare(String name1,String name2) throws IOException{
    	Long[] ris = new Long[2];
    	 long sumx = 0;
		 long sumy = 0;
		 int countx = 0;
		 int county = 0;
    	 
    	 BufferedImage bi = ImageIO.read(new File("E://b//"+name1));
		 BufferedImage si = ImageIO.read(new File("E://b//"+name2));
		 BufferedImage ci = new BufferedImage(312, 116, BufferedImage.TYPE_INT_RGB);
		 
			   for(int x=0;x<116;x++){
				   for(int y=0;y<312;y++){
					   
					   int m = bi.getRGB(y, x);
					   int n = si.getRGB(y, x);
//					   int n1 = si.getRGB(y, x);
//					   int n2 = si.getRGB(y, x);
//					   int n3 = si.getRGB(y, x);
//					   int n4 = si.getRGB(y, x);
//					   int n5 = si.getRGB(y, x);
//					   int n6 = si.getRGB(y, x);
//					   int n7 = si.getRGB(y, x);
//					   int n8 = si.getRGB(y, x);
					   if(y>0 && y<311 && x>0 && x<115){
//						   n1 = si.getRGB(y-1, x-1);
//						   n2 = si.getRGB(y-1, x);
//						   n3 = si.getRGB(y-1, x+1);
//						   n4 = si.getRGB(y, x-1);
//						   n5 = si.getRGB(y, x+1);
//						   n6 = si.getRGB(y+1, x-1);
//						   n7 = si.getRGB(y+1, x);
//						   n8 = si.getRGB(y+1, x+1);
					   }
					   //System.out.println(m+"|"+n);
					   if(m==n
//						||m==n1
//						||m==n2
//						||m==n3
//						||m==n4
//						||m==n5
//						||m==n6
//						||m==n7
//						||m==n8
						){
						   ci.setRGB(y, x, 0);
					   }else{
						   ci.setRGB(y, x, m-n);
						   Integer[] md = convertToARGB(m-n);
						   
						   if((md[1] > 50 && md[1] < 205) || (md[2] > 50 && md[2] < 205) || (md[3] > 50 && md[3] < 205)){
							   
							   //int see = (int)(((int)255 << 255) | (short)(((short)255 << 8  ) | 255));
							   int mek = 0;
							   mek += (md[1]-0)>(255-md[1])?md[1]-0:255-md[1];
							   mek += (md[2]-0)>(255-md[2])?md[2]-0:255-md[2];
							   mek += (md[3]-0)>(255-md[3])?md[3]-0:255-md[3];
							   
							   sumx += x;
							   sumy += y;
							   countx ++;
							   county ++;
							   
							   //System.out.println(x+"|"+y+"||"+md[0]+"|"+md[1]+"|"+md[2]+"|"+md[3]+"||"+mek);
							   ci.setRGB(y, x, m-n);
						   }else{
							   ci.setRGB(y, x, 0);
						   }
						   
					   }
				   }
			   }
			   
			   File file = new File("E://c//"+name2);
			   ImageIO.write(ci, "jpg", file);
			   ris[0] = sumx/countx;
			   ris[1] = sumy/county;
			   return ris;
    }
	
    public static Integer[] convertToARGB(int color) {
    	Integer[] soe = new Integer[4];
    	Integer alpha = alpha(color);
    	Integer red = red(color);
    	Integer green = green(color);
    	Integer blue = blue(color);
        soe[0] = alpha;
        soe[1] = red;
        soe[2] = green;
        soe[3] = blue;
        
        return soe;
    }
    
    public static int alpha(int color) {

        return color >>> 24;
    }

    /**
     * Return the red component of a color int. This is the same as saying
     * (color >> 16) & 0xFF
     */
    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    /**
     * Return the green component of a color int. This is the same as saying
     * (color >> 8) & 0xFF
     */
    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    /**
     * Return the blue component of a color int. This is the same as saying
     * color & 0xFF
     */
    public static int blue(int color) {
        return color & 0xFF;
    }
    
    public static void doSomeThing(HtmlPage page2) throws Exception{
    	

		ScriptResult uneim = page2.executeJavaScript("$('.gt_cut_fullbg_slice').css(\"background-image\")");
		System.out.println(uneim.getJavaScriptResult().toString());
		ScriptResult ndjme = page2.executeJavaScript("$('.gt_cut_bg_slice').css(\"background-image\")");
		System.out.println(ndjme.getJavaScriptResult().toString());
		String m1 = uneim.getJavaScriptResult().toString();
		String m2 = ndjme.getJavaScriptResult().toString();
		m1 = m1.substring(4, m1.length()-1);
		m2 = m2.substring(4, m2.length()-1);
		String n1 = Download(m1);
		String n2 = Download(m2);
		ImageSolve(n1);
		ImageSolve(n2);
		Long[] ris = ImageCompare(n1,n2);
		System.out.println(n2+"-----------------------------");
		Thread.sleep(100);
		page2.executeJavaScript("$('.gt_slider_knob').attr('id','sdsda11');");
		Thread.sleep(100);
		ScriptResult rdertop = page2.executeJavaScript("$('.gt_slider_knob').offset().top;");
		ScriptResult rderleft = page2.executeJavaScript("$('.gt_slider_knob').offset().left;");
		
		System.out.println(rdertop.toString());
		System.out.println(rderleft.toString());
		System.out.println(rdertop.getJavaScriptResult());
		System.out.println(rderleft.getJavaScriptResult());
		Thread.sleep(1000);
		ScriptResult bbbefd2 = page2.executeJavaScript("$('#sdsda11').attr('class');");
		System.out.println(bbbefd2.getJavaScriptResult()+"|"+ris[0]+"|"+ris[1]);
		//page2.executeJavaScript("$('#sdsda11').click(function(e){console.info(e);console.info(this);});");
		//page2.executeJavaScript("$('#sdsda11').click();");
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		List<DomElement> mlist = page2.getElementsById("sdsda11");
		
		
		
//		ScriptResult krdertop = page2.executeJavaScript("$('#keyword').offset().top;");
//		ScriptResult krderleft = page2.executeJavaScript("$('#keyword').offset().left;");
//		//System.out.println(tt.asXml());
//		System.out.println(krdertop);
//		System.out.println(krderleft);
//		ScriptResult brdertop = page2.executeJavaScript("$('body').offset().top;");
//		ScriptResult brderleft = page2.executeJavaScript("$('body').offset().left;");
//		System.out.println(brdertop);
//		System.out.println(brderleft);
		//choose_box
//		ScriptResult chooseboxtop = page2.executeJavaScript("$('#choose_box').offset().top;");
//		ScriptResult chooseboxleft = page2.executeJavaScript("$('#choose_box').offset().left;");
//		System.out.println(chooseboxtop);
//		System.out.println(chooseboxleft);
//		ScriptResult rdtop = page2.executeJavaScript("$('.gt_popup_ready').offset().top;");
//		ScriptResult rdleft = page2.executeJavaScript("$('.gt_popup_ready').offset().left;");
//		System.out.println(rdtop);
//		System.out.println(rdleft);
//		ScriptResult rdtop1 = page2.executeJavaScript("$('.gt_popup_box').offset().top;");
//		ScriptResult rdleft1 = page2.executeJavaScript("$('.gt_popup_box').offset().left;");
//		System.out.println(rdtop1);
//		System.out.println(rdleft1);
		
		page2.executeJavaScript("var mm1 = document.createEvent('MouseEvent');mm1.initMouseEvent('mousemove', true, true, mm1.view,0, -363, 512, -363, 417,false, false, false, false,0, null);document.dispatchEvent(mm1);");
		//Thread.sleep(1000);
		page2.executeJavaScript("var md1 = document.createEvent('MouseEvent');md1.initMouseEvent('mousedown', true, true, md1.view,0, -363, 515, -363, 420,false, false, false, false,0, null);document.getElementById('sdsda11').dispatchEvent(md1);");
		//Thread.sleep(1000);
		ScriptResult bbbefd = page2.executeJavaScript("$('#sdsda11').attr('class');");
		System.out.println(bbbefd.getJavaScriptResult()+"|"+ris[0]+"|"+ris[1]);
		ScriptResult b1ddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
		System.out.println(b1ddbbefd.getJavaScriptResult());
		
		ScriptResult qweqweq1w7 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		//Thread.sleep(100);
		System.out.println(qweqweq1w7.getJavaScriptResult());
		
//		//一格一格移动
		for(int i=0;i<=(ris[1]-22);){
			
			int l = i+0;
			Random rd = new Random();
			int yiue= rd.nextInt(10);
			l = i-yiue+5;
			int yiuee= rd.nextInt(10);
			
			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +i-24 )+", "+( 512-yiuee+5)+", "+(-339 +i-24 )+","+( 417-yiuee+5)+",false, false, false, false,0, null);document.dispatchEvent(mm2);");
			//Thread.sleep(10*yiue);
			
			ScriptResult bddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
			System.out.println(bddbbefd.getJavaScriptResult()+"||"+System.currentTimeMillis());
			int heh = rd.nextInt(100);
			int[] speed = {1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,2,3,3,3,3,3,3,3,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1};
			
			i=i+speed[i%40];
			
		}
		//Thread.sleep(2000);
		page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-23-24 )+", 512, "+(-339 +ris[1]-23-24 )+", 417,false, false, false, false,0, null);document.dispatchEvent(mm2);");
		ScriptResult bddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
		System.out.println(bddbbefd.getJavaScriptResult());
		//Thread.sleep(2000);
		
		page2.executeJavaScript("var mu1 = document.createEvent('MouseEvent');mu1.initMouseEvent('mouseup', true, true, mu1.view,0, "+(-339 +ris[1]-23-24 )+", 512, "+(-339 +ris[1]-23-24 )+", 417,false, false, false, false,0, null);document.getElementById('sdsda11').dispatchEvent(mu1);");

		//一格一格移动
//		for(int i=0;i<=(ris[1]-22);){
//			
//			int l = i+0;
//			Random rd = new Random();
//			int yiue= rd.nextInt(4);
//			int ii = yiue + 2;
//			
//			page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+( -339+ris[1]-23-24+ii )+", 512, "+( -339+ris[1]-23-24+ii )+",417,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//			//Thread.sleep(10*yiue);
//			
//			ScriptResult cbddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
//			System.out.println(cbddbbefd.getJavaScriptResult()+"||"+System.currentTimeMillis());
//			int heh = rd.nextInt(100);
//			
//			i++;
//			
//		}
		
//		page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-22 )+", 523, "+(-339 +ris[1]-22-22 )+", 428,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//		ScriptResult cddbbefd = page2.executeJavaScript("$('#sdsda11').css('left');");
//		System.out.println(cddbbefd.getJavaScriptResult());
//		Thread.sleep(19);
//		page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-20 )+", 522, "+(-339 +ris[1]-22-20 )+", 427,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//		ScriptResult cddbbefd1 = page2.executeJavaScript("$('#sdsda11').css('left');");
//		System.out.println(cddbbefd1.getJavaScriptResult());
//		Thread.sleep(8);
//		page2.executeJavaScript("var mm2 = document.createEvent('MouseEvent');mm2.initMouseEvent('mousemove', true, true, mm2.view,0, "+(-339 +ris[1]-22-14 )+", 523, "+(-339 +ris[1]-22-14 )+", 428,false, false, false, false,0, null);document.dispatchEvent(mm2);");
//		ScriptResult cddbbefd2 = page2.executeJavaScript("$('#sdsda11').css('left');");
//		System.out.println(cddbbefd2.getJavaScriptResult());
//		page2.executeJavaScript("$('#sdsda11').click();");

		
		ScriptResult qweqweqw1 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw1.getJavaScriptResult());
		ScriptResult qweqweqw2 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw2.getJavaScriptResult());
		ScriptResult qweqweqw3 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw3.getJavaScriptResult());
		ScriptResult qweqweqw4 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw4.getJavaScriptResult());
		ScriptResult qweqweqw5 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw5.getJavaScriptResult());
		ScriptResult qweqweqw6 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(3000);
		System.out.println(qweqweqw6.getJavaScriptResult());
		ScriptResult qweqweqw7 = page2.executeJavaScript("$('.gt_ajax_tip').attr('class');");
		Thread.sleep(100);
		System.out.println(qweqweqw7.getJavaScriptResult());
    	
    }
}
