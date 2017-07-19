package com.mujuezhike.sc.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

public class EsUtils {
	
	public static String put(String type,String miniType ,Object o) {

        //establish connection and push policy to snc controller
        try {
        	
        	 
             URL url = new URL("http","10.0.0.23",9200,"/"+type+"/"+miniType+"/");
             HttpURLConnection conn = (HttpURLConnection)url.openConnection();
             conn.setRequestMethod("POST");
             conn.setDoInput(true);
             conn.setDoOutput(true);
             OutputStream os = conn.getOutputStream();     
             
             ObjectMapper objectMapper = new ObjectMapper();
             String jsonString = objectMapper.writeValueAsString(o);
              
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
             return jsonString;
             
        } catch (Exception e) {
          
            e.printStackTrace();  
        }
		return null;
    }

}
