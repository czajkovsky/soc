package main;

import gameEngine.*;
import screen.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Paper Soccer");

		Controller ctl = new Controller();

		Screen scr = new Screen(ctl);
		
		System.out.println("See you later :)");
	}

}