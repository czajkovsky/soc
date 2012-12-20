package gameEngine;

public class Board {
	private int posX,posY;
	protected void setPos(int x, int y) {
		posX = x;
		posY = y;
	}
	protected int getX() {
		return posX;
	}
	protected int getY() {
		return posY;
	}
	
	
	protected boolean checkEdge(int x, int y) {
		/*
		 * return statement:
		 * true - connection possible
		 * false - connection impossible
		 */
		return true;		
	}
	protected void addEdge(int x, int y) {
		
	}
	protected void removeEdge() {
		
	}
	
	private boolean[][] links;
	Board(int width, int height) {
		System.out.println("gameEngine.Board");
		System.out.println(String.valueOf(width) + "," + String.valueOf(height));
		links = new boolean[height][width];
	}
}
