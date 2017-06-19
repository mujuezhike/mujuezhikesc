package com.mujuezhike.sc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	public static void main(String[] args) throws IOException{
		
		   //WebPImageReader wr = new WebPImageReader(null);
		   BufferedImage bi = ImageIO.read(new File("E://5e.webp"));
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
		   
		   File file = new File("E://5se.webp");
		   ImageIO.write(ci, "jpg", file);
	}

}
