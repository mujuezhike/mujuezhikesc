package com.mujuezhike.sc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCompare {
	
	public static void main(String[] args) throws IOException{
		 
		 long sumx = 0;
		 long sumy = 0;
		 int countx = 0;
		 int county = 0;
		 
		 BufferedImage bi = ImageIO.read(new File("E://a11.jpg"));
		 BufferedImage si = ImageIO.read(new File("E://b11.jpg"));
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
							   
							   System.out.println(x+"|"+y+"||"+md[0]+"|"+md[1]+"|"+md[2]+"|"+md[3]+"||"+mek);
							   ci.setRGB(y, x, m-n);
						   }else{
							   ci.setRGB(y, x, 0);
						   }
						   
					   }
				   }
			   }
			   
			   File file = new File("E://512meaa.jpg");
			   ImageIO.write(ci, "jpg", file);
			   System.out.println(sumx/countx);
			   System.out.println(sumy/county);
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

}
