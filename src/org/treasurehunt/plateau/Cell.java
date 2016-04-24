package org.treasurehunt.plateau;

import org.treasurehunt.character.Personnage;

public class Cell {

	private boolean trap = false;

	/**
	 * 0 for not 1 for a present rock 2 with key 3 with chest 4 for water
	 */
	private int obstacle = 0;

	/**
	 * 0 for not 1 for team 1 2 for team 2 ...
	 */
	private int base = 0;

	private int visible = 0;

	private boolean revealed = false;

	private boolean reachable = true;

	private Personnage character = new Personnage(0, 0, 0, 0,0);

	Cell() {
	}

	public boolean isBase() {
		return (this instanceof Base);
	}

	public boolean isObstacle() {
		return !(this.obstacle == 0);
	}

	public boolean isTrap() {
		return trap;
	}

	public void setTrap(boolean trap) {
		this.trap = trap;
	}

	public int getObstacle() {
		return obstacle;
	}

	public void setObstacle(int obstacle) {
		this.obstacle = obstacle;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean r) {
		this.revealed = r;
	}

	public void setCharacter(Personnage c) {
		if (character.getTeam() == 0)
			character = c;
	}

	public void removeCharacter() {
		character = new Personnage(0, 0, 0, 0,0);
	}

	public Personnage getCharacter() {
		return this.character;
	}

	public boolean isCharacter() {
		return character.getTeam() != 0;
	}

	public boolean getReachable() {
		return this.reachable;
	}

	public void setReachable(boolean b) {
		if(b==false && getObstacle()!= Constant.GROUND)this.reachable = b;
		else if(b==true && getObstacle()== Constant.GROUND)this.reachable = b;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

}
