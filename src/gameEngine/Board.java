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
	
	protected void addEdge(int x, int y) {
		
	}
	protected void removeEdge() {
		
	}
	
	private boolean[][][][] links;
	Board(int width, int height) {
		System.out.println("gameEngine.Board");
		System.out.println(String.valueOf(width) + "," + String.valueOf(height));
		links = new boolean[height+1][width+1][3][3];
	}
}
