package game;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import utilities.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
public class PruebaGame {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)   {
		// TODO Auto-generated method stub
		Screen s= new Screen("Prueba");
		Board b;
		JPanel game= new JPanel();
		
		
		Mouse m= new Mouse();
		
		game.addMouseListener(m);
		int x= 400;
		int y = 400;
		JLabel label0= new JLabel("\n\n\n\n .");
		JLabel label=new JLabel("loading...");
		label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 40));
		label.setBounds(101, 62, 54, 21);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		//label.setVerticalAlignment(SwingConstants.CENTER);
		//game.add(label);
		
		label.setVisible(true);
		
		
		
		
		
		s.getContentPane().add(label);
		
		
		s.getContentPane().add(BorderLayout.WEST,game);
		game.setBounds(100,50,100,100);
		s.init( x, y);
		b=new Board(400,400,5,40);
		
		//b1=new Bola(1,100,100,50,50);
		//imagen.image=Sprites.scale(imagen.image,2,100,100,1,1);
		
		game.remove(label);
		
		Events e= new Events(game,m,b,s);
		//s.get(b);
		e.start();
		
		int counter =1000;
		while(counter >0){
			e.act();
			if(b.isEnded()){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				counter= 0;
			}
		/*	if(counter %20== 0){
				b.press();
			}
			if(counter %30== 0){
				b1.press();
			}*/
		//	counter+=1;
			//s.remove(b.getImage());
			//b.act();
			//b1.act();
			//s.repaint();
			
			//s.get(b.getImage());
			
			
		}
		s.setVisible(false); //you can't see me!
		s.dispose();
	}


}
