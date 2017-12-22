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

public class Boludo {

	/**
	 * @param args
	 */
	static JPanel north,south,east,west,center;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Screen s= new Screen("");
		s.setPreferredSize(new Dimension(300, 300));
		north= new JPanel();
		south= new JPanel();
		east= new JPanel();
		west= new JPanel();
		center= new JPanel();
		north.setPreferredSize( new Dimension(100,100));
		
		JLabel loading = new JLabel("loading...");
		loading.setFont(new Font(loading.getFont().getName(), Font.PLAIN, 40));
		center.add(loading);
		
		
		Board b;
		Mouse m= new Mouse();
		
		Player p1;
		int x= 400;
		int y = 400;
		s.init( x, y);
		menu men = new menu();
		men.show(s);
		int q= men.getQ();
		s.init(300, 300);
		
		s.getContentPane().add(BorderLayout.CENTER,center);
		s.getContentPane().add(BorderLayout.NORTH,north);
		//s.setBounds(s.getX(), s.getY(), 300,300);
		s.pack();
		s.repaint();
		int size= 30;
		b=new Board(300,300,q,size);
		JPanel emptyPanel=new JPanel();
		emptyPanel.setPreferredSize(new Dimension(size* 2- (q- 5) * 10,300));
		//Events e= new Events(game,m,b,s);
		s.getContentPane().add(BorderLayout.WEST,emptyPanel);
		s.getContentPane().add(BorderLayout.CENTER,b);
		s.getContentPane().remove(center);
		s.pack();
		p1= new Player(b,1,6,s);
		Events e=new Events(north, m, b, s,null);
		b.addMouseListener(m);
		e.start();
		
		s.repaint();
		
		int counter =1000;
		while(counter >0){
			e.act();
			//p1.play();
			if(b.isEnded()){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				counter= 0;
			}
		
		//e.start();
	}
		s.setVisible(false); //you can't see me!
		s.dispose();
	}
}
