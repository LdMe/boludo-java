package utilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Board extends JPanel{
private int q;
int num;
public int turn;
private boolean ended;
boolean isEnd=false;
boolean isStart=false;

Bola start,end;
int winner;
int[] team;
public ArrayList<Bola> lista;
ArrayList<Bola>  free;
private ArrayList<Bola> reserved;
//ArrayList <Bolas>
public Board(int x,int y,int q1,int size){
	
	
	setPreferredSize( new Dimension((int)(q1 * size ),(int)(q1 * size )));
	
	//setPreferredSize( new Dimension((int)(q1 * size ),(int)(q1 * size )));
	this.setLocation(size, size);
	
	lista= new ArrayList() ;
	winner =0;
	setQ(q1);
	team = new int[2];
	num=getQ()^2;
	setEnded(false);
	
	setTurn(1);
	free=new ArrayList();
	for(int i=0;i < getQ();i++){
		for (int j =0;j< getQ();j++){
			Bola b = new Bola(0,(int)(size* (j )),(int)(size* (i)),size,size);
			lista.add(b);
			free.add(b);
		}
		
	}
	/*lista.get(0).change_color(1);
	lista.get(q*q-q ).change_color(2);*/
	
	
	change_color(0,1);
	change_color(getQ()-1,2);
	change_color(getQ()* getQ()-1,1);
	change_color(getQ()*getQ() -getQ(),2);
	setReserved(new ArrayList());
	getReserved().add(lista.get(0));
	getReserved().add(lista.get(getQ()-1));
	getReserved().add(lista.get(getQ()* getQ()-1));
	getReserved().add(lista.get(getQ()*getQ() -getQ()));
	
	free.remove(lista.get(0));
	free.remove(lista.get(getQ()-1));
	free.remove(lista.get(getQ()* getQ()-1));
	free.remove(lista.get(getQ()*getQ() -getQ()));
	set_neighbors();
	for(int i=0;i<getReserved().size();i++)
	{
		conquer(getReserved().get(i));
	}
	
}

public void setStart(Bola b){
	start=b;
	isStart=true;
}
public void setEnd(Bola b){
	end= b;
	isEnd=true;
}
public Bola getStart(){
	return start;
	
}
public Bola getEnd(){
	return end;
}
public boolean endGame(){
	for(int i=0;i < free.size();i++){
		Bola b=free.get(i);
		for(int j =0;j < b.neighbors.size();j++){
			for(int k =0; k  < b.neighbors.get(j).neighbors.size();k++){
				
			if(b.neighbors.get(j).neighbors.get(k).getColor()== getTurn()){
				return false;
			}
		}
	}
	}
	return true;
}
public void count(){
	team[0]=0;
	team[1]=0;
	for(int i=0;i < getReserved().size();i++){
		team[getReserved().get(i).getColor()-1]+=1;
	}
	if(team[0] > team[1]){
		winner =1;
	}
	else if(team[0] < team[1]){
		winner = 2;
	}
	else{
		winner=0;
	}
	
}
public void set_neighbors(){
	for(int i=0;i< getQ();i++)
	{
		for(int j=0;j< getQ();j++){
			ArrayList <Bola> neighbors = new ArrayList();
			
			if(i > 0){
				neighbors.add(lista.get((i-1)* getQ() +j));
				if(j > 0){
					neighbors.add(lista.get((i-1)* getQ() +(j-1)));
				}
				if(j < getQ()-1)
				{
					neighbors.add(lista.get((i-1)* getQ() +(j+1)));
				}}
			if(i < getQ()-1){
				neighbors.add(lista.get((i+1)* getQ() +j));
				if(j < getQ()-1)
				{
					neighbors.add(lista.get((i+1)* getQ() +(j+1)));
				}
				if(j > 0){
					neighbors.add(lista.get((i+1)* getQ() +(j-1)));
				}
			}
			if(j > 0){
				neighbors.add(lista.get((i)* getQ() +(j-1)));
			}
			if(j < getQ()-1){
				neighbors.add(lista.get((i)* getQ() +(j+1)));
			
			}
			lista.get(i* getQ() + j ).set_neighbors(neighbors);
			}
		}
	}
public void moveTo(int start,int end){
	System.out.println("conquering");
	Bola endBall=lista.get(end);
	Bola startBall=lista.get(start);
	if(startBall.xpos==endBall.xpos && startBall.ypos==endBall.ypos){
		startBall.reset();
		return;
	}
	if(startBall.getColor()!=0){
		int distance= 3;
		for(int i=0;i < startBall.neighbors.size();i++){
			Bola ball=startBall.neighbors.get(i);
			if(ball.xpos==endBall.xpos && ball.ypos== endBall.ypos){
				distance= 1;
				break;
			
			}
			if(distance >2){
			for(int j=0;j < ball.neighbors.size();j++){
				Bola second = ball.neighbors.get(j);
				if(second.xpos==endBall.xpos && second.ypos == endBall.ypos){
					distance = 2;
					break;
				}
			}}
		}
			if(distance > 2){
				System.out.println("too far");
				startBall.reset();
				endBall.reset();
				return;
			}
			
			int color = startBall.getColor();
			startBall.reset();
			change_color(end, color);
			if(distance ==2){
				System.out.println("2 layers");
				change_color(start,0);
				getReserved().remove(startBall);
				free.add(startBall);
			}
			conquer(end);
			free.remove(endBall);
			getReserved().add(endBall);
			setTurn(3-getTurn());
		}
		
	}
	

public void conquer(int index){
	Bola b= lista.get(index);
	try{
		for(int i=0;i < b.neighbors.size();i++){
			Bola neighbor =b.neighbors.get(i);
			if(neighbor.getColor() !=0 && neighbor.getColor() != b.getColor()){
				neighbor.change_color(b.getColor());
			}
		}}catch (NullPointerException e){
		
	}
}

public void conquer(Bola b){
	try{
		for(int i=0;i < b.neighbors.size();i++){
			Bola neighbor =b.neighbors.get(i);
			
			if(neighbor.getColor() !=0 && neighbor.getColor() != b.getColor()){
				neighbor.change_color(b.getColor());
			}
		}}catch (NullPointerException e){
		
	}
}
public void change_color(int index,int color){
	
	Bola b=lista.get(index);
	b.change_color(color);
	
}
@Override
public void paint(Graphics g){
	super.paint(g);
	for(int i=0;i< lista.size();i++){
		lista.get(i).paint(g);
	}
}
public boolean isEnded() {
	return ended;
}
public void setEnded(boolean ended) {
	this.ended = ended;
}

public int getQ() {
	return q;
}

public void setQ(int q) {
	this.q = q;
}

public int getTurn() {
	return turn;
}

public void setTurn(int turn) {
	this.turn = turn;
}

public ArrayList<Bola> getReserved() {
	return reserved;
}

public void setReserved(ArrayList<Bola> reserved) {
	this.reserved = reserved;
}
}
