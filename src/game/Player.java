package game;
import java.util.ArrayList;

import utilities.*;

public class Player {

	int turn;
	Board board;
	ArrayList<Integer> positions;
	ArrayList<Integer> seen;
	ArrayList<Integer> temp_free;
	ArrayList<Integer> team_balls;
	int dificulty;
	Screen screen;
	private int start;
	private int end;
	public Player(Board b,int _turn,int d,Screen s){
		turn=_turn;
		board=b;
		 screen=s;
		dificulty= d;
		positions=new ArrayList();
	//	temp_positions=new ArrayList();
		for(int i=0;i<(b.getQ());i++){
			for(int j=0;j<(b.getQ());j++){
			positions.add(0);
			//temp_positions.add(0);
		}
		}
		
		
	}
	public void act_positions(){
		int n =board.lista.size();
		team_balls=new ArrayList();
		for(int i=0;i < n;i++){
			//temp_positions.set(i,board.lista.get(i).getColor());
			positions.set(i,board.lista.get(i).getColor());
			if(board.lista.get(i).getColor()== turn){
				team_balls.add(i);
			}
		}
	}
	public void conquer(int actual,int color,ArrayList <Integer> lista){
		Bola but=board.lista.get(actual);
		for(int i= 0;i  < but.neighbors.size();i++)
		{
			if(but.neighbors.get(i).getColor() !=0){
				lista.set(board.lista.indexOf(but.neighbors.get(i)),color);
			}
		}
	}
	public int set_heuristics(ArrayList <Integer> temp_positions)
	{
		int color1=0;
		int color2=0;
		for(int i =0; i < temp_positions.size();i++)
		{
			if(temp_positions.get(i)== 1){
				color1++;
			}
			else if(temp_positions.get(i)== 2){
				color2++;
			}
		}
		return color1 - color2;
	}
	public void move(int position){
		board.change_color(position,turn);
		board.conquer(position);
		board.setTurn(3-board.getTurn());
	}
	/*public int simulate(boolean self_turn,int actual,int depth){
		
		int color;
		if(depth <=0){
			return set_heuristics();
		}
		if(self_turn){
			color= turn;
		}
		else{
			color= 3- turn;
		
		}
		ArrayList <Integer> possibles=new ArrayList();
		try{
		temp_positions.set(actual,color);
		
		}catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			
			System.out.println(actual);
			System.out.println("");
		}
		conquer(actual,color);
		ArrayList <Integer> team_list= new ArrayList();
		System.out.println(temp_positions);
		for(int i=0; i < temp_positions.size();i++){
		if(temp_positions.get(i)==color){
			team_list.add(i);
		}
		}
		for(int i=0; i < team_list.size();i++){
				for(int j=0;j <board.lista.get(team_list.get(i)).neighbors.size();j++)
				{
					if(board.lista.get(team_list.get(i)).neighbors.get(j).getColor()==0 && !possibles.contains(board.lista.indexOf(board.lista.get(team_list.get(i)).neighbors.get(j)))){
						possibles.add(board.lista.indexOf(board.lista.get(team_list.get(i)).neighbors.get(j)));
						
					}
				}
			
			
		}
		//System.out.println(possibles);
		boolean started = false;
		int value=0;
		int position= 0;
		for(int i=0;i< possibles.size();i++)
		{
			Bola bb =board.lista.get(possibles.get(i));
			int result=simulate(!self_turn,possibles.get(i),depth-1);
			if(!started){
				value = result;
				started= true;
			}
			
			else{
				
				
				if(color==1){
				
					if(result > value){
						value = result;
						position=i;
					}
					}
			
				else if(color==2){
					if(result < value){
						value = result;
						position=i;
					}
				}
			
		}		
		}
		
		
		
		
		//temp_positions.set(actual,0);
		return value;
	}*/
public int simulate_v2(boolean self_turn,int actual,int depth,ArrayList <Integer> temporal){
		ArrayList <Integer> temp_positions=new ArrayList();
		int color;
		for (int i = 0;i < temporal.size();i++){
			temp_positions.add(temporal.get(i));
		}
		if(depth <=0){
			return set_heuristics(temporal);
		}
		if(self_turn){
			color= 3-turn;
		}
		else{
			color=  turn;
		
		}
		ArrayList <Integer> possibles=new ArrayList();
		try{
		temp_positions.set(actual,color);
		
		}catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			
			System.out.println(actual);
			System.out.println("");
		}
		//System.out.println(temp_positions);
		conquer(actual,color,temporal);
		ArrayList <Integer> team_list= new ArrayList();
		for(int i=0; i < temporal.size();i++){
		if(temporal.get(i)==color){
			team_list.add(i);
		}
		}
		//System.out.println(team_list);
		for(int i=0; i < team_list.size();i++){
			
				for(int j=0;j <board.lista.get(team_list.get(i)).neighbors.size();j++)
				{
					Bola b1= board.lista.get(team_list.get(i)).neighbors.get(j);
					if( seen.contains(board.lista.indexOf(b1))){
						break;
					}
					seen.add(board.lista.indexOf(b1));
					if(board.lista.get(team_list.get(i)).neighbors.get(j).getColor()==0 && !possibles.contains(board.lista.indexOf(board.lista.get(team_list.get(i)).neighbors.get(j)))){
						possibles.add(board.lista.indexOf(board.lista.get(team_list.get(i)).neighbors.get(j)));
						
					}
					for (int k=0;k< b1.neighbors.size();k++){
						Bola b2=b1.neighbors.get(k);
						if( seen.contains(board.lista.indexOf(b2))){
							break;
						}
						seen.add(board.lista.indexOf(b2));
						if(b2.getColor()==0 && !possibles.contains(board.lista.indexOf(b2))){
							possibles.add(board.lista.indexOf(b2));
							
						}
					}
				}
			
			
		}
		//System.out.println(possibles);
		boolean started = false;
		int value=0;
		int position= 0;
		for(int i=0;i< possibles.size();i++)
		{
			Bola bb =board.lista.get(possibles.get(i));
			int result=simulate_v2(!self_turn,possibles.get(i),depth-1,temp_positions);
			if(!started){
				value = result;
				started= true;
			}
			
			else{
				
				
				if(color==1){
				
					if(result > value){
						value = result;
						position=i;
					}
					}
			
				else if(color==2){
					if(result < value){
						value = result;
						position=i;
					}
				}
			
		}		
		}
		
		
		
		
		//temp_positions.set(actual,0);
		return value;
	}
	public class way{
		int father;
		int actual;
		int distance;
		public way(int f,int a,int d){
			father=f;
			actual=a;
			distance=d;
		}
		public int getFather(){
			return father;
		}
		public int getActual(){
			return actual;
		}
		public int getDistance(){
			return distance;
		}
	}
	public boolean play_v2(){
		if(board.getTurn()==turn){
			seen= new ArrayList();
			act_positions();
			ArrayList <Integer> possibles=new ArrayList();
			ArrayList <way> wayList=new ArrayList();
			for (int j=0;j < team_balls.size();j++){
				Bola b1= board.lista.get(team_balls.get(j));
				for(int n=0; n< (b1.neighbors.size());n++ )
				{
					Bola b2= b1.neighbors.get(n);
					if( seen.contains(board.lista.indexOf(b2))){
						break;
					}
					seen.add(board.lista.indexOf(b2));
					
					if(b2.getColor() == 0 && !possibles.contains(board.lista.indexOf(b2)) ){
						possibles.add(board.lista.indexOf(b2));
						wayList.add(new way(team_balls.get(j),board.lista.indexOf(b2),1));
						//possibles_fathers.add(team_balls.get(j));
					}
					for (int k=0;k< b2.neighbors.size();k++){
						Bola b3=b2.neighbors.get(k);
						if( seen.contains(board.lista.indexOf(b3))){
							break;
						}
						seen.add(board.lista.indexOf(b3));
						if(b3.getColor()==0 && !possibles.contains(board.lista.indexOf(b3))){
							possibles.add(board.lista.indexOf(b3));
							wayList.add(new way(team_balls.get(j),board.lista.indexOf(b3),2));
						}
					}
				}
				
			}
			boolean started = false;
			int value=0;
			int position= 0;
			ArrayList <Integer> temp_positions= new ArrayList();
			for(int i =0;i< board.lista.size();i++)
			{
				temp_positions.add(board.lista.get(i).getColor());
			}
			//conquer(actual,color,temp_positions);
			System.out.println("hello");
			System.out.println(team_balls);
			for(int i=0;i< possibles.size();i++)
			{
				boolean isRed= false;
				
				if(turn == 1){
					isRed = true;
				}
				
				int result=simulate_v2(false,possibles.get(i),dificulty,temp_positions);
				Bola bb =board.lista.get(possibles.get(i));
				if(!started){
					value=result;
					started= true;
					setStart(wayList.get(i).getFather());
					setEnd(wayList.get(i).getActual());
				}
				
				else{
					
					
					if(turn==1){
					
						if(result > value){
							value = result;
							setStart(wayList.get(i).getFather());
							setEnd(wayList.get(i).getActual());
							position=i;
						}
						}
				
					else if(turn==2){
						if(result < value){
							value = result;
							position=i;
							setStart(wayList.get(i).getFather());
							setEnd(wayList.get(i).getActual());
						}
					}
				
			}		}
			//move(position);
			//screen.repaint();
		return true;
		}
		return false;
	}
	public boolean play(){
		if(board.getTurn()==turn){
			act_positions();
			ArrayList <Integer> possibles=new ArrayList();
			ArrayList <Integer> possibles_fathers=new ArrayList();
			for (int j=0;j < team_balls.size();j++){
				Bola b1= board.lista.get(team_balls.get(j));
				for(int n=0; n< (b1.neighbors.size());n++ )
				{
					if(b1.neighbors.get(n).getColor() == 0 && !possibles.contains(board.lista.indexOf(b1.neighbors.get(n)))){
						possibles.add(board.lista.indexOf(b1.neighbors.get(n)));
						possibles_fathers.add(team_balls.get(j));
					}
				}
				
			}
			boolean started = false;
			int value=0;
			int position= 0;
			
			for(int i=0;i< possibles.size();i++)
			{
				int result=1;//simulate(false,possibles.get(i),dificulty);
				Bola bb =board.lista.get(possibles.get(i));
				if(!started){
					value=result;
					started= true;
					setStart(possibles_fathers.get(i));
					setEnd(i);
				}
				
				else{
					
					
					if(turn==1){
					
						if(result > value){
							value = result;
							setStart(possibles_fathers.get(i));
							setEnd(i);
							position=i;
						}
						}
				
					else if(turn==2){
						if(result < value){
							value = result;
							position=i;
							setStart(possibles_fathers.get(i));
							setEnd(i);
						}
					}
				
			}		}
			//move(position);
			//screen.repaint();
		return true;
		}
		return false;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
