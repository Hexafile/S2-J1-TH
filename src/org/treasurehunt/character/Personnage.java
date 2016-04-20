package org.treasurehunt.character;


public class Personnage {
	private int team;
	private boolean hasKey = false;
	private int x;
	private int y;
	private int energy;
	private int maxenergy;

	public Personnage(int team, int x, int y, int maxenergy) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.energy = maxenergy;
		this.maxenergy = maxenergy;
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
	public int getEnergy (){
		return this.energy;
	}

	public int getmaxEnergy(){
		return this.maxenergy;
	}

	public void Energy() {
		if(getEnergy() > getmaxEnergy()){
			energy = maxenergy;
		}
		System.out.println("Energie =" + energy + "/" + maxenergy);
	}
}