package org.treasurehunt.character;

/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.util.ArrayList;
import java.util.List;

import org.treasurehunt.plateau.Cell;

/**
 * The abstract class of robots.
 *
 * @author amberstar
 */
public abstract class Character {

	/** the current energy of the robot. */
	private int energy;

	/** the current team of the robot. */
	private int team;

	/** the number of energy regenerated from idle in base. */
	private final int regenBase;

	/** the maximum energy it can holds. */
	private final int maxEng;

	/**
	 * Create a robot.
	 *
	 * @param team
	 *            team of robot
	 * @param coordinates
	 *            coordinate of robot in board
	 * @param board
	 *            the board to work with
	 * @param regenBase
	 *            the amount of energy given when in base
	 * @param maxEng
	 *            the maximal amount of energy the robot can have
	 */
	public Character(int team, int regenBase,
			int maxEng) {
		this.team = team;
		this.regenBase = regenBase;
		this.maxEng = maxEng;
		energy = maxEng;
	}
	
	public abstract int getCoordinateX();
	
	public abstract int getCoordinateY();

	/**
	 * Can attack.
	 *
	 * @return if robot has a valid target and can attack it
	 */
	public boolean canAttack() {
		return getAvailableAtacks() != null;
	}

	/**
	 * If can move.
	 *
	 * @return if robot can move
	 */
	public abstract boolean canMove();

	/**
	 * Gets the available attacks as a list.
	 *
	 * @return the available attacks
	 */
	public abstract List<Integer> getAvailableAtacks();

	/**
	 * Gets the the available attacks as a list.
	 *
	 * @return the available attacks
	 */
	abstract public List<Integer> getAvailableMove();

	/**
	 * Get the cell where the robot is in
	 *
	 * @return the cell the robot is in
	 */
	public abstract Cell getCell();

	/**
	 * Gets the cost action.
	 *
	 * @return the cost of a action
	 */
	public abstract int getCostAction();

	/**
	 * Gets the cost of moving.
	 *
	 * @return the coast of moving
	 */
	public abstract int getCostMoving();

	/**
	 * Gets the damage it make.
	 *
	 * @return the amount of damage it takes
	 */
	public abstract int getDamageTaken();

	/**
	 * Gets the current energy of the robot.
	 *
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Gets the maximum energy it can holds.
	 *
	 * @return the maximal amount of energy the robot can have
	 */
	public int getMaxEng() {
		return maxEng;
	}

	/**
	 * Gets the number of energy regenerated from idle in base.
	 *
	 * @return the amount of energy gain per round in base
	 */
	public int getRegenBase() {
		return regenBase;
	}

	/**
	 * Gets the current team of the robot.
	 *
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type of robot
	 */
	public abstract String getType();

	/**
	 * submit damage from a mine.
	 */

	/*public void hasBeenMined() {
		setEnergy(energy - Constant.SCAVENGER_DAMAGE_SUBMIT);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {

				}
				new ThreadSoundRun(getClass().getResource(
						"/org/virtualwar/res/boom.wav").getPath().replaceAll("%20", " "), 800).start();
			}
		}).start();
	}*/

	/**
	 * submit damage from a shoot.
	 *
	 * @param rob
	 *            the robot who's attacking
	 */
	public void hasBeenShoot(Character rob) {
		setEnergy(energy - rob.getDamageTaken());
	}

	/**
	 * Removes the energy.
	 *
	 * @param eng
	 *            the energy to remove
	 */
	public void removeEnergy(int eng) {
		setEnergy(energy - eng);
	}

	/**
	 * Sets the current energy of the robot.
	 *
	 * @param energy
	 *            the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
		if (energy > maxEng) {
			this.energy = maxEng;
		}
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getType() + "[energy=" + energy + ", team=" + team
				 + ", regenBase=" + regenBase
				+ ", maxEng=" + maxEng + "]";
	}

}

