package utilities;

import java.awt.Graphics;
import java.io.IOException;

public class Button extends Sprites{
	double sizeX,sizeY;
	int scaleX,scaleY;
	private int color;
	int xpos,ypos;
	boolean pressed=false;
	int counter = 10;
	int max_counter=10;
	Sprites imagen,image1,image2,image_old;
	
	public Button(int type,int color,int x,int y){
		super("buttons.png",0,0);
		image_old= new Sprites("buttons.png",0,0);
		
		//		image.copy(image2);
		scaleX=x;
		scaleY=y;
		select_button(type,color);
		
		//image1.image= Sprites.scale(image1.image,2,x,y,x/sizeX,y/sizeY);
		//image2.image= Sprites.scale(image2.image,2,x,y,x/sizeX,y/sizeY);
		
		
	}
	
	
	
	public void set_coord(int x,int y){
		xpos=x;
		ypos=y;
		image1.xpos=x;
		image1.ypos=y;
		image2.xpos=x;
		image2.ypos=y;
	}
	public void press() {
		counter= max_counter;
		pressed=true;
	}
	public void reset(){
		counter=max_counter;
		pressed=false;
	}
	public boolean inside(int x,int y){
		if(x > xpos && x < xpos +scaleX){
			if(y > ypos && y < ypos + scaleY){
				return true;
			}
		}
		return false;
	}
	public void act(){
		if(pressed){
			Sprites old=imagen;
			
			imagen=image2.copy(imagen);
			pressed=false;	
		}
		else{
			
			imagen=image1.copy(imagen);
		
		}
		
	}
	public void select_button(int type,int color)
	{
		this.setColor(color);
		image1=new Sprites("buttons.png",0,0);
		//	image.copy(image1);
		image2=new Sprites("buttons.png",0,0);
		//image2 = new Sprites("buttons.png",0,0);
		if(type == 0){
			sizeX= 160.0;
			sizeY=80.0;
			image1.image=image1.image.getSubimage(0,color *100, 160, 80);
			image2.image=image2.image.getSubimage(160,color *100, 160, 80);
		}
		else if(type==1){
			sizeX= 100.0;
			sizeY=100.0;
			image1.image=image1.image.getSubimage(340,color *100, 100, 100);
			image2.image=image2.image.getSubimage(460,color *100, 100, 100);
		
		}
		else if(type==2){
			sizeX= 100.0;
			sizeY=100.0;
			image1.image=image1.image.getSubimage(580,color *100, 100, 100);
			image2.image=image2.image.getSubimage(720,color *100, 100, 100);
		
		}
		image1.image= Sprites.scale(image1.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		image2.image= Sprites.scale(image2.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		act();
	}
	public Sprites getImage(){
		return imagen;
	}
	public void paint(Graphics g){
		imagen.paint(g);
		
	}



	public int getColor() {
		return color;
	}



	public void setColor(int color) {
		this.color = color;
	}
	
}
