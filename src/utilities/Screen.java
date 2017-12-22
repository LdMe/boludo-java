package utilities;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;


public class Screen extends JFrame{
	
	ArrayList<String> words= new ArrayList() ;
	ArrayList<JLabel> labels ;
	ArrayList<Sprites> lista= new ArrayList() ;
	public Screen(String name){
		super(name);
		setVisible(false);
	}
	public void init(int x, int y ){
		
		setSize(x, y);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void get(Sprites image){
		lista.add(image);
	}
	
	public void removeLabels(){
		words=new ArrayList();
	}
	public void setLabel(String l){
		words.add(l);
	}
	public void remove(Sprites image){
		lista.remove(image);
	}
	/*@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i=0;i< lista.size();i++){
			lista.get(i).paint(g2d);
		}
		
		
	}*/

}
