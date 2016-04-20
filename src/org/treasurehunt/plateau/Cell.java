package org.treasurehunt.plateau;

import java.util.ArrayList;
import java.util.List;

import org.treasurehunt.character.Explorer;

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
	
	private boolean revealed = false;
	
	private List<Character> characters = new ArrayList<Character>();
	
	Cell(){}


	
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
	
	public void addExplorer(Explorer explorer){
		if(characters.isEmpty())characters.add(explorer);
	}
	
	public void removeCharacter(){
		if(!characters.isEmpty())characters.remove(0);
	}
	
	public void changeExplorer(Explorer explorer){
		removeCharacter();
		addExplorer(explorer);
	}
	
	public boolean hasCharacter(){
		return !(characters.isEmpty());
	}
}
