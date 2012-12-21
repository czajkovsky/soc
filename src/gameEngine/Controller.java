package gameEngine;

import java.util.Random;

public class Controller {
	protected Board board;
	private int width, height;
	protected Player player1,player2;
	public Controller() {
		System.out.println("gameEngine.Controller");
		rand = new Random();
		this.width = 8;
		this.height = 10;
	}
	
	public void start(int width, int height, int p1, int p2) {
		board = new Board(width, height);
		this.width = width;
		this.height = height;
		player1 = (p1 == 0) ? (new Human()) : (new Computer());
		player2 = (p2 == 0) ? (new Human()) : (new Computer());
		nextPlayer = 0;
		System.out.println("gameEngine.Controller start");
	}
	
	private int nextPlayer;
	
	Random rand;
	
	/*
	 * getLink return statement:
	 * -1 - connection impossible
	 * 0 - not connected
	 * 1 - connected by player 1
	 * 2 - connected by player 2
	 */
	public int getLink(int x1, int y1, int x2, int y2) {
		//System.out.println(x1+","+y1+" "+x2+","+y2);
		if (x1<0 || y1<0 || x2<0 || y2<0) return -1;
		if (x1>width || x2>width || y1>height || y2>height) return -1;
		int res = rand.nextInt(3);
		return res;
	}
	
	/*
	 * makeMove return statement:
	 * 0 - no winner
	 * 1 - p1 wins
	 * 2 - p2 wins
	 */
	public int makeMove(int x, int y) {
		/*Player player;
		if (nextPlayer == 0) player = player1;
		else player = player2;
		
		int mv = player.makeMove(1, 1, board); // position has to be calculated !
		switch(mv) {
		case -1: 
			System.out.println("Step impossible");
			break;
		case 0: 
			System.out.println("Move without reflection");
			nextPlayer = (nextPlayer+1)%2;
			break;
		case 1:
			System.out.println("Move with reflection");
			break;
		case 2:
			System.out.println(String.valueOf(nextPlayer+1) + " wins");
			return nextPlayer;
		case 3:
			nextPlayer = (nextPlayer+1)%2;
			System.out.println(String.valueOf(nextPlayer+1) + " wins");
			return 1;
		}
		
		return 0;*/
		
		System.out.println(x+","+y);
		
		return 0;
		
	}	
}
