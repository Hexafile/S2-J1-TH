package org.treasurehunt.plateau;

import org.treasurehunt.character.Explorer;
import org.treasurehunt.character.Personnage;

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
	 * 0 for no one
	 * 1 for team 1
	 * 2 for team 2
	 * 3 for both team
	 */
	private int visible = 0;
	
	private boolean revealed = false;
	
	private boolean reachable = true; 
	
	private Personnage character = new Explorer(0,0,0);
	
	Cell(){}


	public int getVisible() {
		return this.visible;
	}
	
	public void setVisible(int visible) {
		this.visible=visible;
	}
	
	public boolean isBase(){
		return (this instanceof Base);
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
	
	public Personnage getCharacter() {
		return this.character;
	}

	public void setCharacter(Personnage character2) {
		/*if (this instanceof Base) Base.addCharacter(character2);
		else this.character=character2;*/
		this.character=character2;
	}
	
	public void deleteCharacter() {
		character = new Explorer(0,0,0);
	}
	
	public boolean isRevealed() {
		return revealed;
	}


	public boolean isTaken() {
		if(this.character.getTeam()!=0)return true;
		return false;
	}
}