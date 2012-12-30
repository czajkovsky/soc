package gameEngine;

import java.util.Vector;

public class Board {
	private int posX,posY,boardHeight,boardWidth,maxEdges,lastEdgeIndex;
	private int[][][] links;
    private Vector<Integer[]> movements;
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
        movements.add(new Integer[] {posX, posY, x, y, k, val});
		links[y][x][k]=val;
		if (movements.size() - lastEdgeIndex == maxEdges) {
			removeFirstEdge();
		}
	}
	protected int removeEdge(int colorFlag) {
        if (!movements.isEmpty()) {
        	int tmp = movements.lastElement()[5]-1;
            if ((colorFlag >= 0 && colorFlag == tmp) || colorFlag == -1) {
            	links[movements.lastElement()[3]][movements.lastElement()[2]][movements.lastElement()[4]] = 0;
                setPos(movements.lastElement()[0], movements.lastElement()[1]);
                movements.remove(movements.size() - 1);
                if (lastEdgeIndex > 0) {
                	--lastEdgeIndex;
                	links[movements.elementAt(lastEdgeIndex)[3]][movements.elementAt(lastEdgeIndex)[2]][movements.elementAt(lastEdgeIndex)[4]] = movements.elementAt(lastEdgeIndex)[5];
                	System.out.println(lastEdgeIndex);
                }
                return tmp;
            }
        }
        return -1;
	}
	
	private void removeFirstEdge() {
		if (!movements.isEmpty()) {
			links[movements.elementAt(lastEdgeIndex)[3]][movements.elementAt(lastEdgeIndex)[2]][movements.elementAt(lastEdgeIndex)[4]] = 0;
			++lastEdgeIndex;
			System.out.println(lastEdgeIndex);
		}
	} 
	
	Board(int width, int height, int mE) {
		boardHeight=height+4;
		boardWidth=width+2;
		maxEdges = mE;
		lastEdgeIndex = 0;
		posX=boardWidth/2;
		posY=boardHeight/2;		
		System.out.println("gameEngine.Board");
		System.out.println(String.valueOf(width) + "," + String.valueOf(height));
		links = new int[boardHeight+1][boardWidth+1][4];
		movements = new Vector<Integer[]>();
		
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
