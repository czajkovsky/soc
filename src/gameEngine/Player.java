package gameEngine;

public interface Player {
	int makeMove(int x, int y, Board board);
	/*
	 * return statements:
	 * -1 - step impossible, has to be changed
	 * 0 - move done without reflection
	 * 1 - move done with reflection
	 * 2 - game finished - winner
	 * 3 - game finished - looser
	 */
	void removeMove();
}
