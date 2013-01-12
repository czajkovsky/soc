package gameEngine;

public class Computer implements Player {

	int val;
	Computer(int val) {
		this.val = val;
	}
	
	@Override
	public int makeMove(int x, int y, Board board) {
		System.out.println("Computer moves");

		return 0;
	}

	@Override
	public void removeMove() {
		
	}

}
