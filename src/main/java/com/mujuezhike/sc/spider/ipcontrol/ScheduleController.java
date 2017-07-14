package com.mujuezhike.sc.spider.ipcontrol;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.LoggerFactory;

public class ScheduleController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	public static String lastname = "";//最后处理的文件
	
	public static String listnumpath = "C:\\zklist\\ryn";
	
	final private static int TASK_MAX_THREAD = 12;
	
	public static void init(){
		
		
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		DynamicIpContainer.init();
		Thread.sleep(35000);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(TASK_MAX_THREAD);  
		  
			for (;;) {  
				//logger.info("boforestart:"+index);
				int threadCount = ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();
				
				//System.out.println(threadCount);
				
				if(threadCount < TASK_MAX_THREAD){
					logger.info("lastname:"+lastname);
					SearchTYCCompanyTask task = new SearchTYCCompanyTask();
					String[] srr = getNextURLsFromFileName();
					Random rd = new Random();
					int ioe = rd.nextInt(5);
					if(srr!=null && srr.length>0){
						task.setStrs(srr);
						task.setEer(ioe);
						fixedThreadPool.execute(task);
					}
				}
				
		    }  

	}
	
	public static String findNextFileName(){
		
		File folder = new File(listnumpath);
		
		File[] f = folder.listFiles();
		
		if("".equals(lastname)){
			return f[0].getAbsolutePath();
		}
		
		for(int i=0;i<f.length;i++){
			
			if(i==f.length-1){
				return "end";
			}
			
			if(f[i].getAbsolutePath().equals(lastname)){
				return f[i+1].getAbsolutePath();
			}
		}
		
		return "end";
	}
	
	public static String turnNextFileName(){
		
		String ss = findNextFileName();
		lastname = ss;
		return lastname;
		
	}
	
	public static String[] getNextURLsFromFileName() throws IOException{
		
		turnNextFileName();
		
		String[] slist = null;
		List<String> list = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(lastname)),
				getCharset(new File(lastname))));
		
		  String lineTxt = null;
          while ((lineTxt = br.readLine()) != null) {
              
        	  if(lineTxt!=null && lineTxt.startsWith("http")){
            	  
        		  list.add(lineTxt.trim());
        		  
              }
        	  
          }
          br.close();
		
       if(list!=null && list.size()>0){
    	   slist = new String[list.size()];
    	   for(int i=0;i<list.size();i++){
    		   
    		   slist[i] = list.get(i);
    		   
    	   }
       }
          
		return slist;
		
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
        return code;  
	 }

}
