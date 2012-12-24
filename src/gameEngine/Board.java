package gameEngine;

public class Board {
	private int posX,posY,boardHeight,boardWidth;
	private int[][][] links;
	protected void setPos(int x, int y) {
		posX = x;
		posY = y;
	}
	protected int getX() {
		return posX;
	}
	protected int getHeight() {
		return boardHeight;
	}
	protected int getWidth() {
		return boardWidth;
	}
	protected int getY() {
		return posY;
	}
	private int ifE(int i, int j, int k) {
		if(links[i][j][k]>0) return 1;
		else return 0;
	}
	
	protected int countNeighbours(int x, int y) {
		int res=0;
		res+=ifE(y,x,0);
		res+=ifE(y,x,2);
		res+=ifE(y,x,1);
		res+=ifE(y-1,x,3);
		res+=ifE(y-1,x,1);
		res+=ifE(y-1,x-1,2);
		res+=ifE(y,x-1,3);
		res+=ifE(y,x-1,0);
		return res;
	}
	protected int getLinks(int i, int j, int k) {
		return links[i][j][k];
	}
	protected void addEdge(int y, int x, int k, int val) {
		links[y][x][k]=val;
	}
	protected void removeEdge() {
		
	}
	Board(int width, int height) {
		boardHeight=height+4;
		boardWidth=width+2;
		posX=boardWidth/2;
		posY=boardHeight/2;		
		System.out.println("gameEngine.Board");
		System.out.println(String.valueOf(width) + "," + String.valueOf(height));
		links = new int[boardHeight+1][boardWidth+1][4];
		/*
		 * 0 - brak
		 * 1 - player 1
		 * 2 - player 2
		 * 3 - board
		 * 4 - hidden
		 */
		
		/*
		 * 0 - lewy
		 * 1 - górny
		 * 2 - \
		 * 3 - /
		 */
		
		for(int i=0; i<boardHeight; i++) {
			for(int j=0; j<boardWidth; j++) {
				for(int k=0; k<=3; k++) links[i][j][k]=4;
			}
		}
		//field
		for(int i=2; i<boardHeight-2; i++) {
			for(int j=1; j<boardWidth-1; j++) {
				for(int k=0; k<=3; k++) links[i][j][k]=0;
			}
		}
		//border
		for(int i=2; i<boardHeight-2; i++) links[i][1][1]=3;
		for(int i=2; i<boardHeight-2; i++) links[i][boardWidth-1][1]=3;
		
		//border
		for(int i=1; i<boardWidth-1; i++) links[2][i][0]=3;
		for(int i=1; i<boardWidth-1; i++) links[boardHeight-2][i][0]=3;
		
		//gates
		for(int k=0; k<=3; k++) links[1][boardWidth/2][k]=0;
		for(int k=0; k<=3; k++) links[2][boardWidth/2][k]=0;
		for(int k=0; k<=3; k++) links[2][boardWidth/2-1][k]=0;
		for(int k=0; k<=3; k++) links[1][boardWidth/2-1][k]=0;
		
		for(int k=0; k<=3; k++) links[boardHeight-2][boardWidth/2][k]=0;
		for(int k=0; k<=3; k++) links[boardHeight-2][boardWidth/2-1][k]=0;
		
		//gates-border
		links[boardHeight-2+1][boardWidth/2][0]=3;
		links[boardHeight-2][boardWidth/2-1][1]=3;
		links[boardHeight-2+1][boardWidth/2-1][0]=3;
		links[boardHeight-2][boardWidth/2+1][1]=3;
		
		links[1][boardWidth/2][0]=3;
		links[1][boardWidth/2-1][1]=3;
		links[1][boardWidth/2-1][0]=3;
		links[1][boardWidth/2+1][1]=3;
		
		
		
		
		
		
		
		
		
		
		
	
		
	}
}
