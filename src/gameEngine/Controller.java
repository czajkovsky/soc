package gameEngine;

public class Controller {
	protected Board board;
	protected Player player0,player1;
	public Controller() {
		System.out.println("gameEngine.Controller");
		board = new Board(10,12);
		player0 = new Human();
		player1 = new Human();
		nextPlayer = 0;
	}
	
	private int nextPlayer;
	
	/*
	 * return statement
	 * -1 - no winner
	 * 0 - p0 wins
	 * 1 - p1 wins
	 */
	int makeMove(int x, int y) {
		Player player;
		if (nextPlayer == 0) player = player0;
		else player = player1;
			int mv = player.makeMove(1, 1); // position has to be calculated !
			switch(mv) {
			case -1: 
				System.out.println("Step impossible");
				break;
			case 0: 
				System.out.println("Move without reflection");
				nextPlayer = (nextPlayer+1)%2;
				break;
			case 1:
				System.out.println("Move with reflection");
				break;
			case 2:
				System.out.println(String.valueOf(nextPlayer) + " wins");
				return nextPlayer;
			case 3:
				nextPlayer = (nextPlayer+1)%2;
				System.out.println(String.valueOf(nextPlayer) + " wins");
				return 1;
			}
		
		return -1;
	}	
}
