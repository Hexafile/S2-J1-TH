package org.treasurehunt.character;

import java.util.List;

import org.treasurehunt.plateau.Cell;

public class Warrior extends Character{

	public Warrior(int team, int regenBase, int maxEng) {
		super(team, regenBase, maxEng);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> getAvailableAtacks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAvailableMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell getCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCostAction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCostMoving() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDamageTaken() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
