package game;
import utilities.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.CORBA.Bounds;

public class menu {
boolean ended= false;
JList l;
int q=0;
public void show(Screen screen){
	String [] lista={"9","8","7","6","5"};
	l=new JList(lista);
	JLabel label=new JLabel("select size");
	JLabel title = new JLabel("Start Menu");
	title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
	title.setPreferredSize(new Dimension(200,120));
	JPanel panel = new JPanel();
	JPanel subpanel = new JPanel();
	subpanel.setPreferredSize(new Dimension(200,100));
	panel.setPreferredSize(new Dimension(300,300));
	subpanel.add(BorderLayout.EAST,label);
	l.setSelectedIndex(4);
	
	//l.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	JScrollPane scroller= new JScrollPane(l);
	l.ensureIndexIsVisible(l.getSelectedIndex());
	scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	l.addListSelectionListener(new qListener());
	
	scroller.getVerticalScrollBar().addAdjustmentListener(new scrollListener());
	JButton button=new JButton();
	//screen.getContentPane().add();
	button.setText("click to start");
	goListener goL=new goListener();
	button.addActionListener(goL);
	panel.add(BorderLayout.NORTH,title);
	panel.add(BorderLayout.EAST,subpanel);
	subpanel.add(button);
	subpanel.add(scroller);
	l.setVisibleRowCount(1);
	l.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	screen.getContentPane().add(panel);
	//screen.pack();
	//screen.setBounds(screen.getX(), screen.getY(), 300,300);
	q= Integer.parseInt((String)l.getSelectedValue());
	System.out.println(q);
	
	screen.repaint();
	screen.init(300, 300);
	while(ended ==false){
		ended= goL.getEnded();
		System.out.println(ended);
	}
	System.out.println("exiting...");
	screen.getContentPane().remove(panel);
	
}

public int getQ(){
	return q;
}
public class qListener implements ListSelectionListener{

	

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
	q= Integer.parseInt((String)l.getSelectedValue());	
	System.out.println(q);
	}
	
}
public class scrollListener implements AdjustmentListener{

	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		l.setSelectedIndex(l.getFirstVisibleIndex());
	}
	
}

public class goListener implements ActionListener{
	boolean end=false;
	public void setEnded(boolean e){
		this.end= e;
	}
	public boolean getEnded(){
		return this.end;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(q!=0){
			System.out.println("continuing...");
		// TODO Auto-generated method stub
		setEnded(true);
		System.out.println(this.end);
	}
	}
	
}
}
