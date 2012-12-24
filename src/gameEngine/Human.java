package gameEngine;

import main.Main;



public class Human implements Player {
	Human() {
		
	}

	@Override
	public void removeMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int makeMove(int x, int y, Board board, int val) {
		
		int boardWidth=Main.controller.getBoardWidth();
		int boardHeight=Main.controller.getBoardHeight();
		int posX=Main.controller.returnPosX();
		int posY=Main.controller.returnPosY();
		
		
		int distX=posX-x;
		if(distX<0) distX*=-1;
		
		int distY=posY-y;
		if(distY<0) distY*=-1;
		
		if(distX<=1&&distY<=1&&distY+distX!=0) {
			int gameRes=0;
			if(x>=1&&x<=boardWidth-1&&y>=2&&y<=boardHeight-2) gameRes=0;
			else {
				if((x==boardWidth/2||x==boardWidth/2-1||x==boardWidth/2+1)&&y==1) gameRes=1;
				if((x==boardWidth/2||x==boardWidth/2-1||x==boardWidth/2+1)&&y==boardHeight-1) gameRes=2;
				else gameRes=-1;
			}
			if(gameRes>=0) {
				if(posY>y&&posX>x) {
					//upper left
					if(Main.controller.boardLinks(posY-1,posX-1,2)==0) Main.controller.updateLinks(posY-1, posX-1, 2, val);
					else return -1;
				}
				else if(posY>y&&posX==x) {
					//upper top
					if(Main.controller.boardLinks(posY-1,posX,1)==0) Main.controller.updateLinks(posY-1,posX,1,val);
					else return -1;
				}
				else if(posY>y&&posX<x) {
					//upper right
					if(Main.controller.boardLinks(posY-1,posX,3)==0) Main.controller.updateLinks(posY-1,posX,3,val);
					else return -1;
				}
				else if(posY==y&&posX>x) {
					//middle left
					if(Main.controller.boardLinks(posY,posX-1,0)==0) Main.controller.updateLinks(posY,posX-1,0,val);
					else return -1;
				}
				else if(posY==y&&posX<x) {
					//middle right
					if(Main.controller.boardLinks(posY,posX,0)==0) Main.controller.updateLinks(posY,posX,0,val);
					else return -1;
				}
				else if(posY<y&&posX<x) {
					//bottom right
					if(Main.controller.boardLinks(posY,posX,2)==0) Main.controller.updateLinks(posY,posX,2,val);
					else return -1;
				}
				else if(posY<y&&posX==x) {
					//bottom bottom
					if(Main.controller.boardLinks(posY,posX,1)==0) Main.controller.updateLinks(posY,posX,1,val);
					else return -1;
				}
				else if(posY<y&&posX>x) {
					//bottom left
					if(Main.controller.boardLinks(posY,posX-1,3)==0) Main.controller.updateLinks(posY,posX-1,3,val);
					else return -1;
					
				}
				else return -1;
				Main.controller.updatePos(x,y);
				
			}
			else return -1;
		}
		else {
			return -1;
		}
		
		
		
		return 0;
	}

}
