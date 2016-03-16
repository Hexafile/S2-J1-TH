package org.treasurehunt.main;

import org.treasurehunt.gui.Gui;
import org.treasurehunt.plateau.Board;

public class Game {
	public static void main(String[] args) {

		Board b = new Board();
		//System.out.println(b.toString());
		Gui g = new Gui(b);
	}
}
