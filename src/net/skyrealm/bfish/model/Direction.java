/*
 * Bika's Fish is a re-make of an old Scratch project.
 * Copyright (C) 2012	Justin Zeng
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.skyrealm.bfish.model;

/**
 * Represents a direction.
 * TODO: Refactor and remove north and south.
 * 
 * @author justin.zeng1
 *
 */

public enum Direction {
	NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);

	private final int deltaX, deltaY;
	
	/**
	 * The constructor of this enum.
	 * @param deltaX
	 * @param deltaY
	 */
	private Direction(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	/**
	 * Gets the delta X.
	 * @return
	 */
	public int getDeltaX() {
		return deltaX;
	}

	/**
	 * Gets the delta Y.
	 * @return
	 */
	public int getDeltaY() {
		return deltaY;
	}
}