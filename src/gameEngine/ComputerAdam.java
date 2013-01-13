package gameEngine;

import main.Main;

public class ComputerAdam implements Player {

	int boardWidth;
	int boardHeight;
	
	int val;
	
	ComputerAdam(int val) {
		this.val = val;
	}

	public int createMove(int x, int y, Board board) {

		int boardWidth=Main.controller.getBoardWidth();
		int boardHeight=Main.controller.getBoardHeight();
		int posX=Main.controller.returnPosX();
		int posY=Main.controller.returnPosY();
		
		
		int distX=posX-x;
		if(distX < 0) distX*=-1;
		
		int distY=posY-y;
		if(distY < 0) distY*=-1;
		
		if(distX<=1 && distY<=1 && distY + distX != 0) {
			int gameRes=0;
			if(x>=1 && x<=boardWidth-1 && y>=2 && y<=boardHeight-2) gameRes=0;
			else {
				//top goal-post reached
				if((x==boardWidth/2 || x==boardWidth/2-1 || x==boardWidth/2+1) && y==1) gameRes=3;
				//bottom goal-post reached
				else if((x==boardWidth/2 || x==boardWidth/2-1 || x==boardWidth/2+1) && y==boardHeight-1) gameRes=4;
				else gameRes=-1;
			}
			if(gameRes >= 0) {
				if(posY>y && posX>x) {
					//upper left
					if(Main.controller.boardLinks(posY-1,posX-1,2)==0) Main.controller.updateLinks(posY-1, posX-1, 2, val);
					else return -1;
				}
				else if(posY>y && posX==x) {
					//upper top
					if(Main.controller.boardLinks(posY-1,posX,1)==0) Main.controller.updateLinks(posY-1,posX,1,val);
					else return -1;
				}
				else if(posY>y && posX<x) {
					//upper right
					if(Main.controller.boardLinks(posY-1,posX,3)==0) Main.controller.updateLinks(posY-1,posX,3,val);
					else return -1;
				}
				else if(posY==y && posX>x) {
					//middle left
					if(Main.controller.boardLinks(posY,posX-1,0)==0) Main.controller.updateLinks(posY,posX-1,0,val);
					else return -1;
				}
				else if(posY==y && posX<x) {
					//middle right
					if(Main.controller.boardLinks(posY,posX,0)==0) Main.controller.updateLinks(posY,posX,0,val);
					else return -1;
				}
				else if(posY<y && posX<x) {
					//bottom right
					if(Main.controller.boardLinks(posY,posX,2)==0) Main.controller.updateLinks(posY,posX,2,val);
					else return -1;
				}
				else if(posY<y && posX==x) {
					//bottom bottom
					if(Main.controller.boardLinks(posY,posX,1)==0) Main.controller.updateLinks(posY,posX,1,val);
					else return -1;
				}
				else if(posY<y && posX>x) {
					//bottom left
					if(Main.controller.boardLinks(posY,posX-1,3)==0) Main.controller.updateLinks(posY,posX-1,3,val);
					else return -1;
					
				}
				else return -1;
				Main.controller.updatePos(x,y);
				if(gameRes==0) {
					int neighbours=Main.controller.countNeighbours(x, y);
					//reflection
					if(neighbours==1) return 0;
					//dead-end
					else if(neighbours==8) return 5;
					else return 1;
				}
				else return gameRes;
				
			}
			else return -1;
		}
		else {
			return -1;
		}
	}

	int depth;
	
	private int makeMoveVisit(Board board, boolean lastLap) {
		int posX = Main.controller.returnPosX();
		int posY = Main.controller.returnPosY();
		
		int lastX = 0, lastY = 0;
		int x,y,endX,endY;
		
		for (int i=-1; i<=+1; i++) {
			for (int j=-1; j<=+1; j++) {
				
				if (val == 1) {
					y = posY + i;
					endY = posY + 1;
				}
				else {
					y = posY - i;
					endY = posY - 1;
				}
				if (posX > board.getWidth()/2) {
					x = posX + j;
					endX = posX + 1;
				}
				else {
					x = posX - j;
					endX = posX - 1;
				}
				
				int mv = createMove(x,y,board);
				if(mv==3) {
					return 3;
				}
				else if(mv==4) {
					return 4;
				}
				else if(mv==5) {
					if (!(y == endY && x == endX)) {
						lastX = x;
						lastY = y;
						removeMove();
					}
				}
				else if(mv==1) {
					return makeMoveVisit(board, false);
				}
				else if(mv==0) {
					return 0;	
				}
			}				
		}

		createMove(lastX, lastY, board);

		return 5;
	}
	
	@Override
	public int makeMove(int x, int y, Board board) {

		depth = 0;
		
		return makeMoveVisit(board, false);		
		
	}

	@Override
	public void removeMove() {
		Main.controller.undo(-2);
	}

}
