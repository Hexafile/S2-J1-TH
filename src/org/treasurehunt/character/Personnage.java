package org.treasurehunt.character;

import org.treasurehunt.plateau.Board;

public abstract class Personnage {
	private int team;
	private boolean hasKey = false;
	private int x;
	private int y;

	public Personnage(int team, int x, int y) {
		this.team = team;
		this.x = x;
		this.y = y;
	}

	public boolean isHasKey() {
		return hasKey;
	}

	public void setHasKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTeam() {
		return this.team;
	}

	public boolean hasKey() {
		return this.hasKey;
	}

	public void setKey(boolean hasKey) {
		this.hasKey = hasKey;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * @return the possibles movements for the character
	 */
}