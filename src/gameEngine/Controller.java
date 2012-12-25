package gameEngine;

import java.util.Random;

public class Controller {
	private int color;
	private int gameStatus;
	protected Board board;
	protected Player[] player;

	public Controller() {
		this.gameStatus = 0;
		System.out.println("gameEngine.Controller");
		rand = new Random();
	}

	public int getGameStatus() {
		return this.gameStatus;
	}

	public void start(int width, int height, int p2) {
		this.color=1;
		this.gameStatus = 1;
		board = new Board(width, height);
		player = new Player[2];
		player[0] = new Human(2);
		player[1] = (p2 == 0) ? (new Human(1)) : (new Computer(1));
		System.out.println("gameEngine.Controller start");
	}

	public void stop() {
		this.gameStatus = 0;
	}
	
	public void undo(int flag) {
        int tmpColor = this.board.removeEdge(-1); // -1 normal redo, >-1 loop redo
        if (tmpColor >= 0) {
            this.color = tmpColor;
            System.out.println(tmpColor);
            while (flag >= 0 && ((tmpColor = this.board.removeEdge(tmpColor)) == this.color)) {
            	System.out.println(tmpColor);
            	continue;
            }
        }
	}

	Random rand;

	/*
	 * getLink return statement: -1 - connection impossible 0 - not connected 1
	 * - connected by player 1 2 - connected by player 2
	 */

	public int getBoardHeight() {
		return this.board.getHeight();
	}
	
	public int getCurrentPlayer() {
		return this.color+1;
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

	public int countNeighbours(int x, int y) {
		return this.board.countNeighbours(x,y);
	}
	
	/*
	 * makeMove return statement: 0 - no winner 1 - p1 wins 2 - p2 wins
	 */
	public int makeMove(int x, int y) {
		Player curPlayer;
		if (this.color == 1) curPlayer = player[0];
		else curPlayer = player[1];
		
		boolean endOfTurn=false;
		int mv = curPlayer.makeMove(x,y,board);
		if(mv==-1) System.out.println("impossible");
		else if(mv==3) {
			System.out.println("top player has lost");
			return 1;
		}
		else if(mv==4) {
			System.out.println("top player has win");
			return 2;
		}
		else {
			if(mv==5) System.out.println("dead end");
			else if(mv==1) System.out.println("reflection");
			else if(mv==0) {
				System.out.println("change player");
				this.color+=1;
				this.color%=2;
				if (!player[0].getClass().equals(player[1].getClass())) {
					curPlayer = player[this.color];
					mv = curPlayer.makeMove(0, 0, board);
				}
			}
		}		
		
		return 0;
	}
}
