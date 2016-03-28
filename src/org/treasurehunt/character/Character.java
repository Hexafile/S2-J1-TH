package org.treasurehunt.character;
import org.treasurehunt.plateau.Board;

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
			if (Board.getBoard()[x-1+i][y-1+j].getReachable()) moves[i][j]=true;
		}
	}
	return moves;
}

public void move (int x, int y) {
	this.setVisible(true);
	Board.getBoard()[this.y][this.x].setCharacter(null);
	this.x=x;
	this.y=y;
	Board.getBoard()[x][y].setCharacter(this);
	this.setVisible(false);
}

public void setVisible(boolean clean) {
	if (clean) {
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (Math.sqrt( (double) ((i-this.getX()) * (i-this.getX()) + (j-this.getY())*(j-getY() ))) < 3.0 )
				Board.getBoard()[y][x].setVisible(Board.getBoard()[y][x].getVisible()-this.team);
			}
		}
	} else {
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				if (Math.sqrt( (double) ((i-this.getX()) * (i-this.getX()) + (j-this.getY())*(j-getY() ))) < 3.0 )
				Board.getBoard()[y][x].setVisible(this.team);
			}
		}
	}
}
}

