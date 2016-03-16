package org.treasurehunt.plateau;

import java.util.ArrayList;
import java.util.Random;

//@author Thomas Plouchart
public class Board extends Cell {

	/** the size of the board in height. */
	private int sizeHeight = 10;

	/** the size of the board in width. */
	private int sizeWidth = 10;

	/** percentage of rocks on the board */
	private int percentage = 10;

	/** an array of Cell for store the grind. */
	private Cell[][] plateau;

	/**
	 * an arraylist that store reachable rocks by both teams to place the key
	 * and the chest
	 */
	private ArrayList<Cell> reachableRocks = new ArrayList<Cell>();

	public Board() {
		generate();
	}

	/**
	 * 
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {
		if (height > 2) {
			sizeHeight = height;
		}
		if (width > 2) {
			sizeWidth = width;
		}
		generate();
	}

	/**
	 * Generate the board with size and obstacles
	 */
	private void generate() {
		boolean[][] visited = new boolean[sizeHeight + 2][sizeWidth + 2]; // Used
																			// by
																			// this.roadExists()

		Random rd = new Random();
		int x=0; //abscissa to place the rocks
		int y=0; //ordinate to place the rocks

		// set the board
		plateau = new Cell[sizeHeight + 2][sizeWidth + 2];
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[0].length; j++) {
				plateau[i][j] = new Cell();
			}
		}
		
		
		
		// Fill the border of the map with water
		for (int i = 0; i < sizeHeight + 2; i++) {
			if (i == 0 || i == (sizeHeight + 1)) {
				for (int j = 0; j < sizeWidth + 2; j++) {
					plateau[i][j].setObstacle(4);
				}
			} else {
				plateau[i][0].setObstacle(4);
				plateau[i][sizeWidth + 1].setObstacle(4);
			}
		}

		// Bases are placed randomly both on the top and the bottom side of the
		// map
		int base1 = rd.nextInt(sizeWidth) + 1;
		int base2 = rd.nextInt(sizeWidth) + 1;
		plateau[0][base1].setObstacle(0);
		plateau[0][base1].setBase(1);
		plateau[sizeHeight + 1][base2].setObstacle(0);
		plateau[sizeHeight + 1][base2].setBase(2);

		//
		do {
			// reset the visited tab
			for (int i = 0; i < sizeHeight + 2; i++) {
				for (int j = 0; j < sizeWidth + 2; j++) {
					visited[i][j] = false;
				}
			}
 
			
			// generate an obstacle (>0) or not (=0) for each cell
			for (int i=0; i<(sizeHeight*sizeWidth*percentage)/100; i++) {
					do {
					x = rd.nextInt(sizeHeight)+1;
					y = rd.nextInt(sizeWidth)+1;
					} while (plateau[y][x].isObstacle());
				plateau[y][x].setObstacle(1);
			}
			
		} while (plateau[0 + 1][base1].isObstacle()
				|| !this.roadExists(base2, sizeHeight+1, base2, sizeHeight + 1, base2, sizeHeight + 1, 'H', visited, -1, -1));

		
		 this.setKeyChest();
		 

	}
	
	/**
	 * @author Samson Vasseur
	 * 
	 * List each reachable rock and randomly chose two of them to put the key and the chest
	 */
	private void setKeyChest() {
		Random rd = new Random();
		boolean[][] visited = new boolean[sizeHeight + 2][sizeWidth + 2];
		
		for (int i=1; i<sizeHeight+1; i++) {
			for (int j=1; j<sizeWidth+1; j++) {
				for (int k=0; k<sizeHeight + 2;k++) {
					for (int l=0; l<sizeWidth + 2; l++) {
						visited[k][l]=false;
					}
				}
				if (plateau[i][j].isObstacle()){
					//System.out.println("test");
					if (this.roadExists(j,i,j, i, j, i, 'H', visited, -1, -1)) {
						reachableRocks.add(plateau[i][j]); 
				}
				}
			}
		}
		
		// set the key and the chest 
		int key =rd.nextInt(reachableRocks.size());
		reachableRocks.get(key).setObstacle(2); 
		reachableRocks.remove(key);
		reachableRocks.get(rd.nextInt(reachableRocks.size())).setObstacle(3);
	}

	/**
	 * @author Samson Vasseur
	 * @return return true if a way exists between team 1's base and team 2's
	 *         base
	 *         
	 * departX is the abscissa of the departure of the algorithm
	 * departY is the ordinate of the departure of the algorithm
	 * x is the currently abscissa of the algorithm
	 * y is the currently ordinate of the algorithm
	 * lastX is the last abscissa of the algorithm
	 * lastX is the last abscissa of the algorithm 
	 * from is the direction the algorithm come from (L for left, R for right, T for top and B for bottom. H is used by default when method is called the first time)
	 * visited is the tabular of cell the algorithm has already visited
	 * demiTourX is the abscissa of the cell where the last turnback began
	 * demiTourY is the ordinate of the cell where the last turnback began
	 */
	
	private boolean roadExists(int departX, int departY, int x, int y, int lastX, int lastY, char from, boolean[][] visited, int demiTourX, int demiTourY) {
		visited[y][x] = true;
		if (y==1 && !plateau[y-1][x].isObstacle()) {
			return true;
		} else {
			
			// check if the cells bordering where the algorithm is are reachable
			
			if (demiTourX!=-1) {
				if (x != (sizeWidth + 1) && x+1!=demiTourX && from != 'R' && !plateau[y][x + 1].isObstacle()) {
					//System.out.println("droite");
					if (x != (sizeWidth + 1) && !visited[y][x + 1])
						demiTourX = -1; 
					return this.roadExists(departX, departY, x + 1, y, x, y, 'L', visited, demiTourX, demiTourY);
				} else if (y != (sizeHeight + 1) && y+1!=demiTourY && from != 'B' && !plateau[y + 1][x].isObstacle()) {
					//System.out.println("bas");
					if (y != (sizeHeight + 1) && !visited[y + 1][x])
						demiTourX = -1;
					return this.roadExists(departX, departY, x, y + 1, x, y, 'T', visited, demiTourX, demiTourY);
				} else if (x != 0 && x-1!=demiTourX && from != 'L' && !plateau[y][x - 1].isObstacle()) {
					if (x != 0 && !visited[y][x - 1])
						demiTourX = -1;
					//System.out.println("gauche");
					return this.roadExists(departX, departY, x - 1, y, x, y, 'R', visited, demiTourX, demiTourY);
				} else if (y != 0 && y-1!=demiTourY && from != 'T' && !plateau[y - 1][x].isObstacle()) {
					//System.out.println("haut");
					if (y != 0 && !visited[y - 1][x])
						demiTourX = -1;
					return this.roadExists(departX, departY, x, y - 1, x, y, 'B', visited, demiTourX, demiTourY);
				}
			} else {
				if (x != 0 && !visited[y][x - 1] && from != 'L' && !plateau[y][x - 1].isObstacle()) {
					//System.out.println("gauche");
					return this.roadExists(departX, departY, x - 1, y, x, y, 'R', visited, -1, -1);
				} else if (y != 0 && !visited[y - 1][x] && from != 'T' && !plateau[y - 1][x].isObstacle()) {
					//System.out.println("haut");
					return this.roadExists(departX, departY, x, y - 1, x, y, 'B', visited, -1, -1);
				} else if (x != (sizeWidth + 1) && !visited[y][x + 1] && from != 'R'
						&& !plateau[y][x + 1].isObstacle()) {
					//System.out.println("droite");
					return this.roadExists(departX, departY, x + 1, y, x, y, 'L', visited, -1, -1);
				} else if (y != sizeHeight + 1 && !visited[y + 1][x] && from != 'B'
						&& !plateau[y + 1][x].isObstacle()) {
					//System.out.println("bas");
					return this.roadExists(departX, departY, x, y + 1, x, y, 'T', visited, -1, -1);
				}

				// Check if the cell is boarded by cells the algorithm has
				// already
				// visited
				if (demiTourX==-1 && x != 0 && y != 0 && x != (sizeWidth + 1) && y != sizeHeight + 1
						&& (visited[y][x - 1] || plateau[y][x - 1].isObstacle())
						&& (visited[y - 1][x] || plateau[y - 1][x].isObstacle())
						&& (visited[y][x + 1] || plateau[y][x + 1].isObstacle())
						&& (visited[y + 1][x] || plateau[y + 1][x].isObstacle()))
					return false;
				
				// Check if we came back to the team2's base
				else if (x==departX && y==departY)
					return false;

				// Make the algorithm go back
				else {
					if (from == 'R') {
						//System.out.println("demi-tour");
						return this.roadExists(departX, departY, x + 1, y, x, y, 'L', visited, x, y);
					} else if (from == 'B') {
						//System.out.println("demi-tour");
						return this.roadExists(departX, departY, x, y + 1, x, y, 'T', visited, x, y);
					} else if (from == 'L') {
						//System.out.println("demi-tour");
						return this.roadExists(departX, departY, x - 1, y, x, y, 'R', visited, x, y);
					} else {
						//System.out.println("demi-tour");
						return this.roadExists(departX, departY, x, y - 1, x, y, 'B', visited, x, y);
					}
				}
			}
		}
		return false;
	}

	/**
	 * To string.
	 * 
	 * @return return a debug version only with bases and obstacles
	 */
	public String toString() {
		String ret = "";
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[0].length; j++) {
				ret += "+---";
			}
			ret += "+\n";
			for (int j = 0; j < plateau[0].length; j++) {
				ret += "|";
				if (plateau[i][j].isBase()) {
					ret += " B ";
				} else {
					if (plateau[i][j].isObstacle()) {
						// don't touch
						if (plateau[i][j].getObstacle() == 2) {
							ret += " K ";
							// don't touch
						} else if (plateau[i][j].getObstacle() == 3) {
							ret += " C ";
						} else if (plateau[i][j].getObstacle() == 4) {
							ret += " W ";
						} else {
							ret += " R ";
						}
					} else {
						ret += "   ";
					}
				}
			}
			ret += "|\n";
		}
		for (int j = 0; j < plateau[0].length; j++) {
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
	
	public Cell getCell(int x,int y){
		return plateau[x][y];
	}
}
