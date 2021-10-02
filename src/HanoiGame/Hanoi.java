package HanoiGame;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import jdk.jshell.spi.ExecutionControl.RunException;

public class Hanoi {
	private stack source;
	private stack aux;
	private stack drain;
	private int size;
	
	//constructor
	public Hanoi(int n) {
		this.size = n-1;
		source = new stack(n,0);
		aux = new stack(n,-1);
		drain = new stack(n,-1);
		this.drawTower();
	}
	
	
	//getters + setters
	public stack getSource() {
		return source;
	}
	public stack getAux() {
		return aux;
	}
	public stack getDrain() {
		return drain;
	}
	public int getSize() {
		return size;
	}
	
	
	//outputs tower on a message pane- could of implemented graphics but just wanted a fast implementation
	public void drawTower()
	{
		String tower =new String("");
		for(int x = size; x>-1; x--) {
			tower = tower + source.printStack(x)+"         "; // tabs arent shown in jOption so using spaces
			tower = tower + aux.printStack(x)+"         ";
			tower = tower + drain.printStack(x)+"         \n";
		}
		tower = tower + "================\n";
		JOptionPane.showMessageDialog(null, tower);
	}
	
	
	public void move(stack Source, stack Drain) {

		//cant place a disk on top of a smaller disc
		if( Drain.getDisc().size() < Source.getDisc().size() && Drain.getDisc().size() != 0 && Source.getDisc().size() != 0) {
			
			this.drawTower();
			System.out.println("Problem as the object you wish to place is bigger than the top object\n");
			return;
		}
		else {
			//increase the height of drain after placing disk-used for indexing
			 Drain.setHeight(1);
			 Drain.getDisc().setSize(Source.getDisc().size());
			 //decrease height of source after placing disk- used for indexing
			 Source.getDisc().setSize(0);
			 Source.setHeight(-1);
			this.drawTower();
			return;
		}
		
	}
	
	//allows user to  input 1 2 or 3 for stack source aux and drain
	public stack parseStack(int stack, stack Stack) {
		switch(stack){
		case 1:
			return this.getSource();
		case 2:
			return this.getAux();
		case 3:
			return this.getDrain();
		default:	
			System.out.println("Invalid input\n");
	}
		
		return null;
	}
	
	
	//algorithm used to solve the tower recursively - will display for the user and show them how game works
	public void towersOfHanoi(int num,stack source, stack dest, stack aux ) throws InterruptedException {
		if(num == 1) {
			move(source,dest);
			return;	
		}
		
		towersOfHanoi(num-1,source,aux,dest);
		move(source,dest);
		towersOfHanoi(num-1,aux,dest,source);
	}
		
	
	//
	public static void main(String[] args) throws InterruptedException {
		 
		 JOptionPane.showMessageDialog(null, "Welcome to hanoi! Press okay to see a demonstration of the game");
		 //runs the recursive algorithm shows how to solve a 3 high stack
		 Hanoi demo = new Hanoi(3);
		 demo.towersOfHanoi(4, demo.getSource(),demo.getDrain(), demo.getAux());
		
		 int take = 0;
		 int put;
		 //taking in input for size of game 
		 int discCount = parseMessage(1);
		 Hanoi game  = new Hanoi(discCount);
		 
		 //while the height of drain isnt the height of the number of discs
		 while(game.getDrain().height() != discCount-1) {
			//user input
			 boolean issue = true;
			  take = parseMessage(2);
			 if(take == 0) {break;}
			  put = parseMessage(3);

			stack Source= null;
			stack Drain= null;
			
			
			//error checking
			if(take == put || take < 1 || take > 3 || put < 1 || put >3) {
				System.out.println("Invalid input\n");
				continue;
			}
			
			//assigning input to stack
			Source= game.parseStack(take, Source);
			Drain= game.parseStack(put, Drain);
			
			//executing move
			game.move(Source,Drain);
			
		}
		
		 //winning output+
		 if(take!= 0) {
		 JOptionPane.showMessageDialog(null, "You won thanks for playing!");
		 }
		
	}
	
	private static int parseMessage(int index) {
		boolean issue= true;
		while(issue) {
		 try {
			 switch(index) {
			 case 1:
				 
				 int size = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter how many discs you wish to play between 2 and 5"));
				 
				 if(size < 2 || size > 5) {continue;}
				 
				 return size;
			 case 2:
				 return Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter Stack you wish to take from (1,2,3)(0 to quit)"));	
			 case 3: 
				 return Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Please enter Stack you wish to put disc(1,2,3)"));
			 }
		 }
		catch(NumberFormatException e) {
				System.err.println("Input only integers");
		}
		}
		return 0;
	}
	
	
	
}
