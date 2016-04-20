package org.treasurehunt.main;

import java.util.List;

import org.treasurehunt.gui.Gui;
import org.treasurehunt.gui.Menu;
import org.treasurehunt.plateau.Board;
import org.treasurehunt.plateau.Cell;
import org.treasurehunt.character.*;
import org.treasurehunt.character.Personnage;

public class Game {
	public static void main(String[] args) {

		Board b = new Board();
		
		Menu m = new Menu();
		while(!m.getPressedEnter()){
			m.repaint();
		}

		List<Integer> l = m.getCharacter();
		m.dispose();
		
		b.getBoard()[2][2].setCharacter(new Personnage(1, 2, 2,0));

		
		for(int i = 0; i<l.get(0);i++){
			b.getBoard()[b.getBase(1)][0].setCharacter(new Personnage(0, 0, 0,0));
		}
		
		for(int i = 0; i<l.get(0);i++){
			b.getBoard()[b.getBase(2)][b.getSizeWidth()-1].setCharacter(new Personnage(0, 0, 0,0));
		}
		System.out.println(b.toString());

		/*while(menu.getSelected()){
			
		}*/
		new Gui(b);
	}
	
	
}
