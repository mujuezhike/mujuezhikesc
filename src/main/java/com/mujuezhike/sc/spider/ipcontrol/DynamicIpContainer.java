package com.mujuezhike.sc.spider.ipcontrol;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局IP存储器
 * @author mujuezhike
 */
public class DynamicIpContainer {
	
	public static List<String> dynamicips = new ArrayList<String>();
	
	public static Integer maxLiveIp = 10;

	public static Integer nextchangeIpCount = 0;
	
	
	public static void main(String args[]){
		
		DynamicIpContainer.init();
		
	}
	
	public static void init(){
		
		new Thread(new IpChanger()).start();
		
//		for(;;){
//			
//			try {
//				Thread.sleep(7000);
//				for(int i=0;i<dynamicips.size();i++){
//					
//					System.out.print(dynamicips.get(i) + "  ");
//					
//				}
//				System.out.print(nextchangeIpCount);
//				System.out.println();
//				
//			} catch (Exception e) {
//				
//				
//				
//				e.printStackTrace();
//			}
//		}
		
	}
	
	public static void setDIp(String sip){
		
		sip = sip.trim();
		if(nextchangeIpCount < dynamicips.size()){
			
			dynamicips.set(nextchangeIpCount, sip);
			nextchangeIpCount++;
			
		}else{
			
			dynamicips.add(sip);
			nextchangeIpCount++;
		}
		
		if(nextchangeIpCount >= maxLiveIp){
			nextchangeIpCount = 0;
		}
		
	}
}
