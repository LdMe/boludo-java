package utilities;

import java.util.ArrayList;

public class Bola extends Button{
	
	Sprites image3,image4,image0;
	public ArrayList<Bola> neighbors;
	public Bola(int color,int x,int y,int sizeX,int sizeY){
		
		super(2, color, sizeX, sizeY);
		set_coord(x,y);
		act();
		
	}
	public void set_neighbors(ArrayList<Bola> n){
		neighbors= n;
	}
	public ArrayList<Bola> get_neighbors(){
		return neighbors;
	}
	@Override
	public void act(){
		if(getColor()==0){
			imagen=image0;
			return;
		}
		else if(getColor() ==1){
		if(pressed){
			Sprites old=imagen;
			
			imagen=image2;
			pressed=false;	
		}
		else{
			
			imagen=image1;
		
		}}
		else if(getColor() ==2){
			if(pressed){
				Sprites old=imagen;
				
				imagen=image4;
				pressed=false;	
			}
			else{
				
				imagen=image3;
			
			}
		}
		set_coord(xpos,ypos);
		
	}
	@Override
	public void select_button(int type,int color)
	{
		this.setColor(color);
		image0=new Sprites("buttons.png",0,0);
		image1=new Sprites("buttons.png",0,0);
		//	image.copy(image1);
		image2=new Sprites("buttons.png",0,0);
		image3=new Sprites("buttons.png",0,0);
		//	image.copy(image1);
		image4=new Sprites("buttons.png",0,0);
		
			sizeX= 100.0;
			sizeY=100.0;
			image0.image=image0.image.getSubimage(580,0, 100, 100);
			image1.image=image1.image.getSubimage(580,100, 100, 100);
			image2.image=image2.image.getSubimage(720,100, 100, 100);
			image3.image=image3.image.getSubimage(580,200, 100, 100);
			image4.image=image4.image.getSubimage(720,200, 100, 100);
		
		image0.image= Sprites.scale(image0.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		image1.image= Sprites.scale(image1.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		image2.image= Sprites.scale(image2.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		image3.image= Sprites.scale(image3.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		image4.image= Sprites.scale(image4.image,2,scaleX,scaleY,scaleX/sizeX,scaleY/sizeY);
		act();
	}
	public void select_button(){
		setColor(3-getColor());
	}
	public void change_color(int color){
		int x=xpos;
		int y=ypos;
		this.setColor(color);
		
		set_coord(x,y);
		act();
		
	}
	@Override
	public void set_coord(int x,int y){
		xpos=x;
		ypos=y;
		image1.xpos=x;
		image1.ypos=y;
		image2.xpos=x;
		image2.ypos=y;
		image3.xpos=x;
		image3.ypos=y;
		image4.xpos=x;
		image4.ypos=y;
		image0.xpos=x;
		image0.ypos=y;
	}

}
