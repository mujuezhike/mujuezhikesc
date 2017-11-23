package com.mujuezhike.sc.spider.ipcontrol;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class QichachaListSpider {

	public static void main(String[] args) throws Exception{
		
		List<String> list = getListFromFile(new File("C:\\zkbean\\word1120.txt"));
		
		for(int i=30;i<list.size();i++) {
			Thread.sleep(2000);
			System.out.println(list.get(i));
			CommonStaticPageSpider.savePage("http://www.qichacha.com/search?key="+URLEncoder.encode(list.get(i)), new File("C:\\zkbean\\qcclist"));
		}

	}
	
	public static List<String> getListFromFile(File file){
		
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
        try {
        	String charset = getCharset(file);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));  
            String tempString = "";
            while ((tempString = reader.readLine()) != null) {
            	
            		list.add(tempString);
            		
            }
        }catch(Exception e) {
        	
        		e.printStackTrace();
        		
        }
		
		return list;
	}
	
	private static String getCharset(File file) throws IOException{  
        
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));    
        int p = (bin.read() << 8) + bin.read();    
          
        String code = null;    
          
        switch (p) {    
            case 0xefbb:    
                code = "UTF-8";    
                break;    
            case 0xfffe:    
                code = "Unicode";    
                break;    
            case 0xfeff:    
                code = "UTF-16BE";    
                break;    
            default:    
                code = "GBK";    
        }    
        System.out.println(code);
        return code;  
	}

}
