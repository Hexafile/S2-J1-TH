package org.treasurehunt.character;
import org.treasurehunt.plateau.*;

public class Character {
	private int team; 
	private boolean hasKey = false;
	private int x;
	private int y; 
	
Character (int team, int x, int y) {
	this.team=team;
	this.x=x;
	this.y=y;
}

public int getTeam() {
	return this.team;
}

public boolean hasKey() {
	return this.hasKey;
}

public void setKey (boolean hasKey) {
	this.hasKey=hasKey;
}

public int getX () {
	return this.x;
}

public int getY () {
	return this.y;
}

/**
 * @return the possibles movements for the character
 */
public boolean[][] getMoves () {
	boolean[][] moves = new boolean[3][3];
	for (int i=0; i<3; i++ ) {
		for (int j=0; j<3; j++) {
			if (Board.getBoard()[x-1+i][y-1+1].getReachable()) moves[i][j]=true;
		}
	}
	return moves;
}

public void move (int x, int y) {
	Board.getBoard()[this.y][this.x].setCharacter(null);;
	this.x=x;
	this.y=y;
	Board.getBoard()[x][y].setCharacter(this); // Je vois pas pourquoi ça marche pas...
}
}

