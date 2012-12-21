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
		for (int i=0; i<height+1; i++) {
			for (int j=0; j<width+1; j++) {
				for (int k=0; k<3; k++) {
					for (int l=0; l<3; l++) {
						links[i][j][k][l] = false;
					}
				}
			}
		}
	}
}
