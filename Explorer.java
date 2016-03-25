package org.treasurehunt.character;

import java.util.ArrayList;
import java.util.List;

import org.treasurehunt.plateau.Board;
import org.treasurehunt.plateau.Cell;

public class Explorer extends Character{
	private int x;
	private int y;
	private int mouv = 1;
	private int souleve = 2;
	
	public Explorer(int team, int regenBase, int maxEng) {
		super(team, regenBase, maxEng);
	}

	public int getCostAction() {

		return 0;
	}


	public int getCostMoving() {

		return 0;
	}

	
	public int getDamageTaken() {

		return 0;
	}

	public boolean canMove() {
		if (Board.getBoard()[x-1][y].getReachable()){
			return true;
		} else if (Board.getBoard()[x+1][y].getReachable()){
			return true;
		} else if (Board.getBoard()[x][y-1].getReachable()){
			return true;
		} else if (Board.getBoard()[x][y+1].getReachable()){
			return true;
		}
		return false;	
	}


	public List<Integer> getAvailableAtacks() {
	
		return null;
	}


	public List<Integer> getAvailableMove() {
		List<Integer> actions = new ArrayList<>();
		if(canMove()){
			actions.add(1);
		} else if(Board.getBoard()[x][y] == null){
			actions.add(2);
		}
		return actions;
	}

	public Cell getCell() {
		return Board.getBoard()[x][y];
	}

}
