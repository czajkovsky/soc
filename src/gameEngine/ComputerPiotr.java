package gameEngine;

import main.Main;
import java.util.Vector;
import java.math.*;

public class ComputerPiotr implements Player {

	int val;
	int boardWidth;
	int boardHeight;
	private Vector<Integer[]> moves;
	ComputerPiotr(int val) {
		this.val = val;
		moves = new Vector<Integer[]>();
	}
	
	private boolean inBoard(int y, int x) {
		boolean l = (x>=1 && x<=boardWidth-1 && y>=2 && y<=boardHeight-2) ? true : false;
		if (!l && (x == boardWidth/2 || x == boardWidth/2-1 || x == boardWidth/2+1))
			return true;
		return l;
	}
	
	private void chooseCorrectMoves(int x, int y) {
		if (inBoard(y-1,x-1) && Main.controller.boardLinks(y-1,x-1,2) == 0) {
			moves.add(new Integer[] {y-1, x-1});
		}
		if (inBoard(y-1,x) && Main.controller.boardLinks(y-1,x,1) == 0) {
			moves.add(new Integer[] {y-1, x});
		}
		if (inBoard(y-1,x+1) && Main.controller.boardLinks(y-1,x,3) == 0) {
			moves.add(new Integer[] {y-1, x+1});
		}
		if (inBoard(y, x-1) && Main.controller.boardLinks(y,x-1,0) == 0) {
			moves.add(new Integer[] {y, x-1});
		}
		if (inBoard(y, x+1) && Main.controller.boardLinks(y,x,0) == 0) {
			moves.add(new Integer[] {y, x+1});
		}
		if (inBoard(y+1, x+1) && Main.controller.boardLinks(y,x,2) == 0) {
			moves.add(new Integer[] {y+1, x+1});
		}
		if (inBoard(y+1, x) && Main.controller.boardLinks(y,x,1) == 0) {
			moves.add(new Integer[] {y+1, x});
		}
		if (inBoard(y+1, x-1) && Main.controller.boardLinks(y,x-1,3) == 0) {
			moves.add(new Integer[] {y+1, x-1});
		}
	}
	
	int chooseBestMove(int goalX, int goalY) {
		double distance = 1000, buf; // all distances will definitely be less then that
		int index = -1;
		int x, y;
		for (int i = 0; i < moves.size(); ++i) {
			x = moves.elementAt(i)[1];
			y = moves.elementAt(i)[0];
			buf = Math.sqrt((goalX - x) * (goalX - x) + (goalY - y) * (goalY - y));
			if (buf < distance) {
				distance = buf;
				index = i;
			}
			if (Main.controller.countNeighbours(x, y) > 0 &&  Main.controller.countNeighbours(x, y) < 7) { // reflection
				if (goalY == 1) {
					buf = -1;
					if (inBoard(y-1,x-1) && Main.controller.boardLinks(y-1,x-1,2) == 0) {
						buf = Math.sqrt((goalX - (x-1)) * (goalX - (x-1)) + (goalY - (y-1)) * (goalY - (y-1)));	
					}
					if (inBoard(y-1,x) && Main.controller.boardLinks(y-1,x,1) == 0) {
						buf = Math.sqrt((goalX - (x)) * (goalX - (x)) + (goalY - (y-1)) * (goalY - (y-1)));
					}
					if (inBoard(y-1,x+1) && Main.controller.boardLinks(y-1,x,3) == 0) {
						buf = Math.sqrt((goalX - (x+1)) * (goalX - (x+1)) + (goalY - (y-1)) * (goalY - (y-1)));
					}
					if (buf > -1 && buf < distance) {
						distance = buf;
						index = i;
					}
				}
				else {
					buf = -1;
					if (inBoard(y+1,x+1) && Main.controller.boardLinks(y,x,2) == 0) {
						buf = Math.sqrt((goalX - (x+1)) * (goalX - (x+1)) + (goalY - (y+1)) * (goalY - (y+1)));
					}
					if (inBoard(y+1,x) && Main.controller.boardLinks(y,x,1) == 0) {
						buf = Math.sqrt((goalX - (x)) * (goalX - (x)) + (goalY - (y+1)) * (goalY - (y+1)));
					}
					if (inBoard(y+1,x-1) && Main.controller.boardLinks(y,x-1,3) == 0) {
						buf = Math.sqrt((goalX - (x-1)) * (goalX - (x-1)) + (goalY - (y+1)) * (goalY - (y+1)));
					}
					if (buf > -1 && buf < distance) {
						distance = buf;
						index = i;
					}
				}
			}
		
		}
		return index;
	}
	
	@Override
	public int makeMove(int x, int y, Board board) {
		if (x == 0 && y == 0) {
			x = board.getX();
			y = board.getY();
		}
		boardWidth=Main.controller.getBoardWidth();
		boardHeight=Main.controller.getBoardHeight();
		int goalX = boardWidth/2;
		int goalY = (val == 1) ? 1 : boardHeight-1; 
		int best, bestX, bestY;
		int gameRes=1;
		while (gameRes == 1) {
			chooseCorrectMoves(x, y);
			best = chooseBestMove(goalX, goalY);
			bestX = moves.elementAt(best)[1];
			bestY = moves.elementAt(best)[0];
			moves.clear();
			//top goal-post reached
			if((x==boardWidth/2 || x==boardWidth/2-1 || x==boardWidth/2+1) && y==1) gameRes=3;
			//bottom goal-post reached
			else if((x==boardWidth/2 || x==boardWidth/2-1 || x==boardWidth/2+1) && y==boardHeight-1) gameRes=4;
			else {
				if(bestY>y && bestX>x) {
					//upper left
					if(Main.controller.boardLinks(bestY-1,bestX-1,2)==0)
						Main.controller.updateLinks(bestY-1, bestX-1, 2, val);
					else return -1;
				}
				else if(bestY>y && bestX==x) {
					//upper top
					if(Main.controller.boardLinks(bestY-1,bestX,1)==0)
						Main.controller.updateLinks(bestY-1,bestX,1,val);
					else return -1;
				}
				else if(bestY>y && bestX<x) {
					//upper right
					if(Main.controller.boardLinks(bestY-1,bestX,3)==0)
						Main.controller.updateLinks(bestY-1,bestX,3,val);
					else return -1;
				}
				else if(bestY==y && bestX>x) {
					//middle left
					if(Main.controller.boardLinks(bestY,bestX-1,0)==0)
						Main.controller.updateLinks(bestY,bestX-1,0,val);
					else return -1;
				}
				else if(bestY==y && bestX<x) {
					//middle right
					if(Main.controller.boardLinks(bestY,bestX,0)==0)
						Main.controller.updateLinks(bestY,bestX,0,val);
					else return -1;
				}
				else if(bestY<y && bestX<x) {
					//bottom right
					if(Main.controller.boardLinks(bestY,bestX,2)==0) 
						Main.controller.updateLinks(bestY,bestX,2,val);
					else return -1;
				}
				else if(bestY<y && bestX==x) {
					//bottom bottom
					if(Main.controller.boardLinks(bestY,bestX,1)==0)
						Main.controller.updateLinks(bestY,bestX,1,val);
					else return -1;
				}
				else if(bestY<y && bestX>x) {
					//bottom left
					if(Main.controller.boardLinks(bestY,bestX-1,3)==0)
						Main.controller.updateLinks(bestY,bestX-1,3,val);
					else return -1;
					
				}
				else return -1;
				Main.controller.updatePos(bestX,bestY);
				x = bestX;
				y = bestY;
				int neighbours=Main.controller.countNeighbours(bestX, bestY);
				//reflection
				if(neighbours==1) gameRes = 0;
				//dead-end
				else if(neighbours==8) gameRes = 5;
			}
		}
		return gameRes;
	}

	@Override
	public void removeMove() {
		
	}

}