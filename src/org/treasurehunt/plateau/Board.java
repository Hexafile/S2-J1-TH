package org.treasurehunt.plateau;

import java.util.Random;

import org.treasurehunt.character.Personnage;

//@author Thomas Plouchart
/**
 * @author Samson
 * 
 */
public class Board extends Cell {

	/** the size of the board in height. */
	private int sizeHeight = 20;

	/** the size of the board in width. */
	private int sizeWidth = 20;

	/** percentage of rocks on the board */
	private int percentage = 15;

	/** an array of Cell for store the grind. */
	private Cell[][] board;

	private int base1;

	private int base2;

	public Board() {
		Random rdm = new Random();
		this.base1 = rdm.nextInt(sizeHeight) + 1;
		this.base2 = rdm.nextInt(sizeHeight) + 1;
		generate();
	}

	/**
	 * 
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {
		Random rdm = new Random();
		if (height > 2) {
			sizeHeight = height;
		}
		if (width > 2) {
			sizeWidth = width;
		}
		this.base1 = rdm.nextInt(sizeHeight) + 1;
		this.base2 = rdm.nextInt(sizeHeight) + 1;
		generate();

	}

	/**
	 * @return the ordinate of team base
	 */
	public int getBase(int team) {
		if (team == 2)
			return this.base2;
		else
			return this.base1;
	}

	public Cell[][] getBoard() {
		return board;
	}

	/**
	 * Generate the board with size and obstacles
	 */
	private void generate() {
		board = new Cell[sizeHeight + 2][sizeWidth + 2];
		int rocksNb = (sizeHeight * sizeWidth * percentage) / 100;

		for (int i = 0; i < sizeHeight + 2; i++) {
			for (int j = 0; j < sizeWidth + 2; j++) {
				board[i][j] = new Cell();
			}
		}
		//do {
			for (int i=0; i < sizeHeight +2; i++) {
				for (int j=0; j < sizeWidth +2; j++) {
					board[i][j].setReachable(true);
				}
			}
			for (int i = 0; i < sizeHeight + 2; i++) {
				for (int j = 0; j < sizeWidth + 2; j++) {
					if (j == 0 || i == 0 || i == sizeHeight + 1
							|| j == sizeWidth + 1) { // If it's the border put
														// sea
						board[i][j].setObstacle(4);
						board[i][j].setReachable(false);
						// board[i][j].setBase(0);
					} else {
						board[i][j].setObstacle(0); // Else put herb
					}
				}
			}
			Random rdm = new Random();

			board[base1][0] = new Base(1); // Randomly set the position of team
											// 1's
			// boat
			board[base2][sizeWidth + 1] = new Base(2); // Randomly set the
														// position
			// of team 2's boat

			int rocks = 0;
			while (rocks < rocksNb) {
				int x = rdm.nextInt(sizeWidth) + 1;
				int y = rdm.nextInt(sizeHeight) + 1;
				if (board[x][y].getObstacle() != 3
						&& !(board[x][y - 1].isBase())
						&& !(board[x][y + 1].isBase())) { // If it's not already
															// a rock and
															// it doesn't block
															// a boat
					board[x][y].setObstacle(1);
					board[x][y].setReachable(false);
					rocks++;
				}
			}
			System.out.println(this.toString());
		//} while (!genDone(board, rocksNb)); // while at least one rock is
											// unreachable

		Random random = new Random();
		int cpt = 0;
		int key, chest;
		do {
			key = random.nextInt(rocksNb);
			chest = random.nextInt(rocksNb);
		} while (key == chest); // Key and chest can't be on the same cell

		for (int i = 0; i < sizeHeight + 2; i++) {
			for (int j = 0; j < sizeWidth + 2; j++) {
				if (board[i][j].getObstacle() == 1) {
					if (key == cpt) {
						board[i][j].setObstacle(2); // key is put on a rock
					} else if (chest == cpt) {
						board[i][j].setObstacle(3);
						; // chest is put on a rock

					}
					cpt++;
				}
			}
		}
	}

	/*private boolean genDone(Cell[][] board, int rocksNb) {
		int[][] tab = new int[sizeHeight][sizeWidth];
		tab[0][0] = 1; // This is a virtual player who check if all the rocks
						// are reachable
		int cpt2=0;
		boolean isBlocked = true;
		while (cpt2<2){
		while (isBlocked) { // While the player has moved, we try to keep on
			isBlocked = true;
			for (int i = 0; i < sizeHeight; i++) {
				for (int j = 0; j < sizeWidth; j++) {
					if (board[i + 1][j + 1].getObstacle() == 1 && tab[i][j]==0) {
						if (board[i + 2][j + 1].isObstacle()
								&& board[i][j + 1].isObstacle()
								&& board[i + 1][j + 2].isObstacle()
								&& board[i + 1][j].isObstacle()) {
							 System.out.println("debug2");
							return false; // If it's a rock and unreachable, we
											// generate the map again
						}
						tab[i][j] = 2; // if the rock is not blocked, we give it
										// the indice 2
						isBlocked = false;
						System.out.println(""+i+" "+j);
						System.out.println("Rocher");
					}
					if (tab[i][j] == 1 && !board[i + 1][j + 1].isBase()) {
						System.out.println("" + i + " " + j);
						if (!board[i + 2][j + 1].isObstacle()
								&& tab[i + 1][j] == 0) { // if the bottom cell is
															// herb
							tab[i + 1][j] = 1; // The player could go to this
												// cell so we give it the indice
												// 1
							isBlocked = false; // We're not blocked because the
												// player could go here.
							System.out.println("bas");
						}
						if (!board[i][j + 1].isObstacle() && tab[i - 1][j] == 0) { // if
																					// the
																					// up
																					// cell
																					// is
																					// herb
							tab[i - 1][j] = 1; // see upper
							System.out.println("haut");
							isBlocked = false;
						}
						if (j != sizeWidth - 1
								&& !board[i + 1][j + 2].isObstacle()
								&& tab[i][j + 1] == 0) { // if the right cell is
															// herb
							tab[i][j + 1] = 1;// see upper
							System.out.println("droit");
							isBlocked = false;
						}
						if (j != 0 && !board[i + 1][j].isObstacle()
								&& tab[i][j - 1] == 0) { // if
							// the
							// left
							// cell
							// is
							// herb
							tab[i][j - 1] = 1; // see upper
							System.out.println("gauche");
							isBlocked = false;
						}
						/*if (!board[i + 1][j + 2].isBase()) {
							bateau1 = true;
						}
						if (board[i + 1][j].isBase()) {
							bateau2 = true;
						}
					}
				}
			}
			
		}
		cpt2++;
		System.out.println(""+cpt2);
		}
		int cpt = 0;
		for (int k = 0; k < sizeHeight; k++) {
			for (int l = 0; l < sizeWidth; l++) {
				if (tab[k][l] == 2) {
					int neighbour = 0;
					if (k == 0) {
						neighbour += 2;
					} else {
						neighbour += tab[k - 1][l];
					}
					if (k == sizeHeight - 1) {
						neighbour += 2;
					} else {
						neighbour += tab[k + 1][l];
					}

					if (l == 0) {
						neighbour += 2;
					} else {
						neighbour += tab[k][l - 1];
					}
					if (l == sizeWidth - 1) {
						neighbour += 2;
					} else {
						neighbour += tab[k][l + 1];
					}
					if (neighbour < 4 || neighbour >= 8) {
						System.out.println("FUUUUUUCK");
						return false;
					}
					cpt++; // If we find a 0 that means we cannot reach it
				}
			}
		}
		return cpt == rocksNb;
	}*/

	/**
	 * To string.
	 * 
	 * @return return a debug version only with bases and obstacles
	 */
	public String toString() {
		String ret = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				ret += "+---";
			}
			ret += "+\n";
			for (int j = 0; j < board[0].length; j++) {
				ret += "|";
				if (board[i][j].isBase()) {
					ret += " B ";

				} else {
					if (board[i][j].isObstacle()) {
						if (board[i][j].getObstacle() == 2) {
							ret += " K ";

						} else if (board[i][j].getObstacle() == 3) {
							ret += " C ";
						} else if (board[i][j].getObstacle() == 4) {
							ret += " W ";
						} else {
							ret += " R ";
						}
					} else if (board[i][j].isCharacter()) {
						ret += " P ";
					} else {
						ret += "   ";
					}
				}
			}
			ret += "|\n";
		}
		for (int j = 0; j < board[0].length; j++) {
			ret += "+---";
		}
		ret += "+\n";
		return ret;
	}

	/**
	 * 
	 * @return Size of the height of the map
	 */
	public int getSizeHeight() {
		return sizeHeight;
	}

	/**
	 * 
	 * @return Size of the width of the map
	 */
	public int getSizeWidth() {
		return sizeWidth;
	}

	public Cell getCell(int x, int y) {
		return board[y][x];
	}

	public void move(int xpos, int ypos, int x, int y) {
		if (getCell(xpos, ypos).isCharacter()) {
			// this.setVisible(false);
			getCell(xpos, ypos).getCharacter().setX(x);
			getCell(xpos, ypos).getCharacter().setY(y);

			Personnage c = getCell(xpos, ypos).getCharacter();
			getCell(xpos, ypos).removeCharacter();
			getCell(x, y).setCharacter(c);

			getCell(x, y).setReachable(false);
			getCell(xpos, ypos).setReachable(true);

			// this.setVisible(true);
		} else {
			System.out.println("Not present");
		}
	}

	public boolean[][] getMoves(Personnage character) {
		boolean[][] moves = new boolean[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(character.getX() == 0 && i == 0)
						&& !(character.getY() == 0 && j == 0)
						&& !(character.getY() == getSizeHeight() && j == 2)
						&& !(character.getX() == getSizeWidth() && i == 2)) {
					if (character.getType()==Constant.EXPLORER)
					if (getCell(character.getX() - 1 + i,
							character.getY() - 1 + j).getReachable())
						moves[i][j] = true;
					else
						moves[i][j] = false;
					if (character.getType()==Constant.EXPLORER)
						moves[i][j]=false;
				}
			}
		}
		return moves;
	}

	public void setVisible(boolean refresh, int x, int y, int team) {
		if (!refresh) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (Math.sqrt((double) ((i - x)

					* (i - x) + (j - y) * (j - y))) < 3.0)
						getCell(x, y).setVisible(
								getBoard()[y][x].getVisible() - team);
				}
			}
		} else {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (Math.sqrt((double) ((i - x) * (i - y) + (j - y)
							* (j - y))) < 3.0)
						getCell(x, y).setVisible(team);
				}
			}
		}
	}

	/*
	 * public boolean[][] getAct() { boolean[][] act = new boolean[3][3]; for
	 * (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) { if (getBoard()[x
	 * - 1 + i][y - 1 + j].getObstacle() > 0 && Board.getBoard()[x - 1 + i][y -
	 * 1 + j].getObstacle() < 4) act[i][j] = true; } } return act; }
	 */
}
