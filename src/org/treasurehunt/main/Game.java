package org.treasurehunt.main;

import java.util.List;

import org.treasurehunt.gui.Gui;
import org.treasurehunt.gui.Menu;
import org.treasurehunt.plateau.Board;
import org.treasurehunt.plateau.Cell;
import org.treasurehunt.character.*;
import org.treasurehunt.character.Character;

public class Game {
	static Board b;
	public static void main(String[] args) {

		b = new Board();
		System.out.println(b.toString());
		
		Menu m = new Menu();
		while(!m.getPressedEnter()){
			m.repaint();
		}

		List<Integer> l = m.getCharacter();
		m.dispose();
		
		/*for(int i = 0 ; i<l.size();i++){
			if(i%2==0){
				
			}
		}*/
		
		for(int i = 0; i<l.get(0);i++){
			Board.getBoard()[b.getBase(1)][0].setCharacter(new Explorer(0, 0, 0));
		}
		
		for(int i = 0; i<l.get(0);i++){
			Board.getBoard()[b.getBase(2)][b.getSizeWidth()-1].setCharacter(new Explorer(0, 0, 0));
		}
		
		
		//Character c = new Explorer(0, 0, 0);

		/*while(menu.getSelected()){
			
		}*/
		new Gui(b);
	}
	
	
}
