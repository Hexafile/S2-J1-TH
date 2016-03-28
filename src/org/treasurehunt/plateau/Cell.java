package org.treasurehunt.plateau;

import org.treasurehunt.character.Character;

public class Cell {

	private boolean trap = false;

	/**
	 * 0 for not
	 * 1 for a present rock
	 * 2 with key
	 * 3 with chest
	 * 4 for water
	 */
	private int obstacle = 0;

	/**
	 * 0 for not
	 * 1 for team 1
	 * 2 for team 2 ...
	 */
	private int base = 0;
	
	/**
	 * 0 for no one
	 * 1 for team 1
	 * 2 for team 2
	 * 3 for both team
	 */
	private int visible = 0;
	
	private boolean revealed = false;
	
	private boolean reachable = true; 
	
	private Character character = null;
	

	
	
	Cell(){}


	public int getVisible() {
		return this.visible;
	}
	
	public void setVisible(int visible) {
		this.visible=visible;
	}
	
	public boolean isBase(){
		return !(this.base == 0);
	}
	
	public boolean isObstacle(){
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
		if (obstacle!=0) this.reachable=false;
	}
	
	public boolean getReachable () {
		return this.reachable;
	}
	
	public void setReachable (boolean reachable) {
		this.reachable=reachable;
	}
	
	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character2) {
		this.character=character2;
	}
	
	public void deleteCharacter() {
		this.character=null; 
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
}
