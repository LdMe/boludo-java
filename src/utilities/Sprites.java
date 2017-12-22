package utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import javax.imageio.ImageIO;




public class Sprites  implements Cloneable{
	public
	BufferedImage image ;
	int xpos;
	int ypos;
	int dx;
	int dy;
	String path;
	public void move(){
		xpos+=dx;
		ypos+=dy;
	}
	public void moveTo(int x,int y){
		xpos=x;
		ypos=y;
	}
	public Sprites(){}
	public Sprites(String imagepath,int x,int y) {
		try{
		
		//InputStream is = getClass().getClassLoader().getResourceAsStream(imagepath);
			URL url = ClassLoader.getSystemResource(imagepath);
			File f= new File(imagepath);
			System.out.println(imagepath);
			image=ImageIO.read(f);
		xpos=x;
		ypos=y;
		path=imagepath;
		}catch(IOException e){}
	}
	public void paint(Graphics g){
		g.drawImage(image,xpos,ypos,null);
		
	}
	@Override
	public Sprites clone(){
		Sprites result;
		try {
			result = (Sprites) super.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return this;
		}
		
		
	}
	public Sprites copy(Sprites result) {
		
		
		try{
			
			//result=new Sprites();
			result.image=image;
			return result;
		}catch(NullPointerException e){
			result=new Sprites(path,xpos,ypos);
			result.image=image;
			return result;
		}
		
		
		
		
	}
	static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}
}
