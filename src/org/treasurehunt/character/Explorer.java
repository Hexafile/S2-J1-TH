package org.treasurehunt.character;


import org.treasurehunt.plateau.Board;


public class Explorer extends Character{
	public int x;
	public int y;

	public Explorer(int team, int x, int y) {
		super(team, x, y);
	}
	
	public boolean[][] getAct () {
		boolean[][] act = new boolean[3][3];
		for (int i=0; i<3; i++ ) {
			for (int j=0; j<3; j++) {
				if (Board.getBoard()[x-1+i][y-1+j].getObstacle()>0 && Board.getBoard()[x-1+i][y-1+j].getObstacle() <4) act[i][j]=true;
			}
		}
		return act;
	}
}