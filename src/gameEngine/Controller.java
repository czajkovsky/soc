package gameEngine;

import java.util.Random;

public class Controller {
	private int color;
	private int gameStatus;
	protected Board board;
	private int width, height;
	protected Player player1, player2;

	public Controller() {
		this.gameStatus = 0;
		System.out.println("gameEngine.Controller");
		rand = new Random();
		this.width = 8;
		this.height = 10;
	}

	public int getGameStatus() {
		return this.gameStatus;
	}

	public void start(int width, int height, int p1, int p2) {
		this.color=1;
		this.gameStatus = 1;
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
	 * getLink return statement: -1 - connection impossible 0 - not connected 1
	 * - connected by player 1 2 - connected by player 2
	 */

	public int getBoardHeight() {
		return this.board.getHeight();
	}
	
	public void updatePos(int x,int y) {
		this.board.setPos(x,y);
	}

	public int getBoardWidth() {
		return this.board.getWidth();
	}
	
	public void updateLinks(int y, int x, int k, int val) {
		this.board.addEdge(y, x, k, val);
	}
	
	public int boardLinks(int i, int j, int k) {
		return this.board.getLinks(i, j, k);
	}

	public int returnPosX() {
		return this.board.getX();
	}
	
	public int returnPosY() {
		return this.board.getY();
	}

	/*
	 * makeMove return statement: 0 - no winner 1 - p1 wins 2 - p2 wins
	 */
	public int makeMove(int x, int y) {
		Player player;
		if (nextPlayer == 0) player = player1;
		else player =player2;
		
		boolean endOfTurn=false;
		int mv = player.makeMove(x,y,board,this.color+1);
		if(mv==3) System.out.println("top player has lost");
		else if(mv==4) System.out.println("top player has win");
		else {
			System.out.println("we're still playing");
			this.color+=1;
			this.color%=2;
		}
		return 0;

	}
}
