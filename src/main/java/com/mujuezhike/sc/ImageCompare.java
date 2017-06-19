package com.mujuezhike.sc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCompare {
	
	public static void main(String[] args) throws IOException{
		
		 BufferedImage bi = ImageIO.read(new File("E://5se.webp"));
		 BufferedImage si = ImageIO.read(new File("E://5s.webp"));
		 int height = bi.getHeight();
		 int width = bi.getWidth();
		 System.out.println(height);
		 System.out.println(width);
		 height = si.getHeight();
		 width = si.getWidth();
		 System.out.println(height);
		 System.out.println(width);
		
		 BufferedImage ci = new BufferedImage(312, 116, BufferedImage.TYPE_INT_RGB);
		 
			   for(int x=0;x<116;x++){
				   for(int y=0;y<312;y++){
					   
					   int m = bi.getRGB(y, x);
					   int n = si.getRGB(y, x);
					   int n1 = si.getRGB(y, x);
					   int n2 = si.getRGB(y, x);
					   int n3 = si.getRGB(y, x);
					   int n4 = si.getRGB(y, x);
					   int n5 = si.getRGB(y, x);
					   int n6 = si.getRGB(y, x);
					   int n7 = si.getRGB(y, x);
					   int n8 = si.getRGB(y, x);
					   if(y>0 && y<311 && x>0 && x<115){
						   n1 = si.getRGB(y-1, x-1);
						   n2 = si.getRGB(y-1, x);
						   n3 = si.getRGB(y-1, x+1);
						   n4 = si.getRGB(y, x-1);
						   n5 = si.getRGB(y, x+1);
						   n6 = si.getRGB(y+1, x-1);
						   n7 = si.getRGB(y+1, x);
						   n8 = si.getRGB(y+1, x+1);
					   }
					   System.out.println(m+"|"+n);
					   if(m==n
						||m==n1
						||m==n2
						||m==n3
						||m==n4
						||m==n5
						||m==n6
						||m==n7
						||m==n8){
						   ci.setRGB(y, x, 0);
					   }else{
						   ci.setRGB(y, x, n);
					   }
				   }
			   }
			   
			   File file = new File("E://5me.webp");
			   ImageIO.write(ci, "jpg", file);
	}

}
