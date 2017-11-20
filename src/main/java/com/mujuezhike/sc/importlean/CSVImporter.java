package com.mujuezhike.sc.importlean;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSVImporter {
	
	private static Map<String,Integer> cmap = null;

	public static void main(String[] args) throws IOException {
		
		List<FCategroy> list = getCategroyListFromFile(new File("C://data/1115TOP20PC.csv"));
//		System.out.println(list.size());
//		System.out.println(cmap.size());
//		for(Map.Entry<String, Integer> entry :cmap.entrySet()) {
//			if(!entry.getKey().startsWith("0"))
//			System.out.println(entry.getKey()+" : "+entry.getValue());
//		}
		saveFCategroy(new File("C://data/word1120.txt"),list);
		

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
	 
	private static List<FCategroy> getCategroyListFromFile(File file){
		
		List<FCategroy> list = new ArrayList<FCategroy>();
		Map<String,FCategroy> map = new HashMap<String,FCategroy>();
		cmap = new HashMap<String,Integer>();
		BufferedReader reader = null;
        try {
        	String charset = getCharset(file);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));  
            String tempString = "";
            String totalString = ""; 
            int id = 1;
            while ((tempString = reader.readLine()) != null) {
            	
            	if(tempString.indexOf(",")>-1) {
            	
            		String[] strs = tempString.split(",");
            		
            		//词汇
            		String l0mm = strs[1];
            		saveCategroy(l0mm,0,list,map,cmap,id);
            		
            		//小类   // @20171120 会有没有小类的情况
            		if(strs.length>3) {
            			String l2mm = strs[3];
                        if(l2mm.indexOf("/")>-1) {
                        	
                        	String[] l2mms = l2mm.split("/");
                        	for(String s:l2mms) {
                        		saveCategroy(s,2,list,map,cmap,id);	
                        	}
                        	
                        }else {
                        
                        	saveCategroy(l2mm,2,list,map,cmap,id);
                        }
            		}
            		
                    //大类
                    String l1mm = strs[2];
                    if(l1mm.indexOf("/")>-1) {
                    	
                    	String[] l1mms = l1mm.split("/");
                    	for(String s:l1mms) {
                    		saveCategroy(s,1,list,map,cmap,id);	
                    	}
                    	
                    }else {
                    
                    	saveCategroy(l1mm,2,list,map,cmap,id);
                    }
                    
            	}
            	
            }
            reader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
		
		return null;
	}
	
	private static FCategroy saveCategroy(String s,int level,List<FCategroy> list,Map<String,FCategroy> map,Map<String,Integer> cmap,int id){
		
		FCategroy mkl = new FCategroy();
		mkl.setId(id);
		id++;
		mkl.setLevel(level);
		mkl.setName(s);
		mkl.setStatus(1);
		
		FCategroy bean = map.get(level+"_"+s);
		if(bean == null) {
			map.put(level+"_"+s, mkl);
			cmap.put(level+"_"+s, 1);
			list.add(mkl);
		}else {
			Integer ss = cmap.get(level+"_"+s);
			if(ss==null) {
				cmap.put(level+"_"+s, 1);
			}else {
				cmap.put(level+"_"+s, ss+1);
			}
		}
		
		return mkl;
	}

	private static File saveFCategroy(File file,List<FCategroy> list) throws IOException {
		
		Set<String> set = new HashSet<String>();
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter writer = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(writer);
        for(FCategroy f:list) {
        
        	if(!set.contains(f.getName())) {
        		bw.write(f.getName());
            	bw.newLine();
            	set.add(f.getName());
        	}
        	
        }
		
       
        bw.close();
        writer.close();
		
		return file;
	}
}
