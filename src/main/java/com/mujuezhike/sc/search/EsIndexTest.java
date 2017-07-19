package com.mujuezhike.sc.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EsIndexTest {

	public static void main(String[] args) throws Exception {
	
//		Map<String,String> putMap = new HashMap<String,String>();
//		putMap.put("name","dnfds");
//		putMap.put("score",90+"");
//		EsUtils.put("mtest", "test", putMap);
		
		search();
		
	}

	 public static void put() {

	        //establish connection and push policy to snc controller
	        try {
	        	
	             URL url = new URL("http","10.0.0.23",9200,"/mtest/test/1");
	             HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	             conn.setRequestMethod("PUT");
	             conn.setDoInput(true);
	             conn.setDoOutput(true);
	             OutputStream os = conn.getOutputStream();     
	             
	             String jsonString = "{\"settings\" : \"asmdk\",\"name\" : \"namekrerrt\"}";
	             //String jsonString = "{\"settings\" : {\"index\" : {\"number_of_shards\" : 5, \"number_of_replicas\" : 1 }}}";
	             
	             os.write(jsonString.getBytes("utf-8")); 
	             os.flush();
	             os.close();         
	            
	             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             String line = "";
	             String result = "";
	             while( (line =br.readLine()) != null ){
	                 result += line;
	             }
	             System.out.println(result);
	             br.close();
	        } catch (Exception e) {
	          
	            e.printStackTrace();  
	        }
	    }
	 
	 public static void search() {

	        //establish connection and push policy to snc controller
	        try {
	             URL url = new URL("http","10.0.0.23",9200,"/mtest/test/_search");
	             HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	             conn.setRequestMethod("GET");
	             conn.setDoInput(true);
	             conn.setDoOutput(true);
	             OutputStream os = conn.getOutputStream();     
	             
	             String jsonString = "{\"query\": {\"wildcard\": {\"_all\": \"*f*\" }}}";
	             //String jsonString = "{\"settings\" : {\"index\" : {\"number_of_shards\" : 5, \"number_of_replicas\" : 1 }}}";
	             
	             os.write(jsonString.getBytes("utf-8")); 
	             os.flush();
	             os.close();         
	             BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             String line = "";
	             String result = "";
	             while( (line =br.readLine()) != null ){
	                 result += line;
	             }
	             System.out.println(result);
	             br.close();
	        } catch (Exception e) {
	          
	            e.printStackTrace();  
	        }
	    }
}
