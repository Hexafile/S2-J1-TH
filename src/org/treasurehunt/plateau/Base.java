package org.treasurehunt.plateau;

import java.util.ArrayList;

import org.treasurehunt.character.Personnage;

public class Base extends Cell{
	private ArrayList <Personnage> character = new ArrayList <Personnage> ();
	private int team;
	
	Base(int team) {
		this.team=team;
	}
	
	public int getTeam() {
		return this.team;
	}
	
	public void addCharacter (Personnage character) {
		this.character.add(character);
	}
	
	public void removeCharacter (Personnage character) {
		this.character.remove(this.character.indexOf(character));
	}
	
	
}