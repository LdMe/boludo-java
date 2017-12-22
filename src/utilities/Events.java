package utilities;

import game.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Events {
JPanel game;
Mouse mouse;
Board board;
Screen screen;
JLabel label1=new JLabel();
JLabel label2=new JLabel();
JLabel playerLabel=new JLabel();
JLabel turnLabel=new JLabel();
Player player=null;
public Events(JPanel panel,Mouse m,Board b,Screen s){
	game=panel;
	mouse=m;
	board=b;
	screen=s;
	//label1.setLocation(10, 10);
	//label1.setLocation(20, 10);
	//playerLabel.setLocation(20, 20);
	//turnLabel.setLocation(30, 20);
}
public Events(JPanel panel,Mouse m,Board b,Screen s,Player p){
	game=panel;
	mouse=m;
	board=b;
	screen=s;
	player = p;
	//label1.setLocation(10, 10);
	//label1.setLocation(20, 10);
	//playerLabel.setLocation(20, 20);
	//turnLabel.setLocation(30, 20);
}
public void showWords(JLabel label,String words,Color c){
	
	
		label.setForeground(c);
		label.setText(words);
		
		
		
		//label.setVisible(true);
		
	
}
public void removeLabels(){
	for (Component jc : game.getComponents()) {
	 if ( jc instanceof JLabel ) {
	        game.remove(jc);
	    }
	}
}
public void start(){
	//board.setLocation(50, 100);
	String pl ="Player: ";
	String ll1 ="2 " ;
	String ll2 = "2" ;
	String ll3 = Integer.toString(board.getTurn());
	//screen.setLabel(l);
	showWords(label1,ll1,Color.red);
	showWords(label2,ll2,Color.green);
	showWords(playerLabel,pl,Color.black);
	if(board.getTurn() ==1){
		showWords(turnLabel,ll3,Color.red);
	}
	else{
		showWords(turnLabel,ll3,Color.green);
	}
	label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 20));
	label2.setFont(new Font(label2.getFont().getName(), Font.PLAIN, 20));
	playerLabel.setFont(new Font(playerLabel.getFont().getName(), Font.PLAIN, 20));
	turnLabel.setFont(new Font(turnLabel.getFont().getName(), Font.PLAIN, 20));
	game.add(label1);
	game.add(label2);
	game.add(playerLabel);
	game.add(turnLabel);
	game.setPreferredSize(new Dimension(100,50));
	
	screen.getContentPane().add(BorderLayout.NORTH,game);
	screen.pack();
	screen.repaint();
	
}
public void act(){
	
	if(player != null){
		if(player.play_v2()){
			board.isStart= true;
			board.start=board.lista.get(player.getStart());
			board.isEnd = true;
			board.end=board.lista.get(player.getEnd());
			mouse.released=true;
			
		}
		else{
			
			board.end=board.lista.get(player.getEnd());
			mouse.released=true;
			}
		}
	if(mouse.pressed){
		int x= mouse.x;
		int y=mouse.y;
		Bola b;
		
		mouse.pressed=false;
		for(int i=0;i <board.lista.size();i++){
			b=board.lista.get(i);
			
			if(b.inside(x,y) && b.getColor() == board.getTurn()){
				b.press();
				b.act();
				try{
				Bola start = board.getStart();
				if(start!=b){
					start.reset();
					start.act();
				}}catch (NullPointerException e){
					
				}
				board.setStart(b);
				break;
			}
		}
		
	}
	else if(mouse.released && board.isStart){
		int x= mouse.x;
		int y=mouse.y;
		Bola b;
		mouse.released=false;
		for(int i=0;i <board.lista.size();i++){
			b=board.lista.get(i);
			if(b.inside(x,y)&& b != board.start && b.getColor()==0){
				
				board.setEnd(b);
				
				break;
			}
			
			else if(b.inside(x,y)&& b.getColor() == board.start.getColor()){
				screen.repaint();
				
				break;
			}
		}
		if(player != null){
		if(player.play_v2()){
			System.out.println(player.getStart());
			System.out.println(player.getEnd());
			board.isStart= true;
			board.start=board.lista.get(player.getStart());
			board.isEnd = true;
			board.end=board.lista.get(player.getEnd());
			
		}}
		if(board.isStart && board.isEnd){
			board.moveTo(board.lista.indexOf(board.start),board.lista.indexOf(board.end));
			board.isStart =false;
			board.isEnd=false;
			board.count();
			
			
			String l1 = Integer.toString(board.team[0]) +" " ;
			String l2 = Integer.toString(board.team[1]) ;
			String l3 = Integer.toString(board.getTurn());
			//screen.setLabel(l);
			//System.out.println(l1);
			showWords(label1,l1,Color.red);
			showWords(label2,l2,Color.green);
			if(board.getTurn() ==1){
				showWords(turnLabel,l3,Color.red);
			}
			else{
				showWords(turnLabel,l3,Color.green);
			}
			if(board.endGame()){
				board.setEnded(true);
				playerLabel.setText("winner: ");
				
				if(board.winner ==1){
					showWords(turnLabel,Integer.toString(board.winner),Color.red);
				}
				else{
					showWords(turnLabel,Integer.toString(board.winner),Color.green);
				}
				playerLabel.repaint();
			}
			label1.repaint();
			label2.repaint();
			turnLabel.repaint();
			board.start.reset();
			board.start.act();
			screen.repaint();
			
		}
	}
}
}
