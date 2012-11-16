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
 * An enum defining each <code>Fish</code>.
 * @author justin.zeng1
 *
 */

public enum FishType {
	
	RED_FISH("Red Fish", 4, 2, 1, 0.2F),
	PURPLE_FISH("Purple Fish", 7, 3, 2, 0.2F),
	YELLOW_FISH("Yellow Fish", 6, 4, 5, 0.2F),
	// RAINBOW_FISH("Rainbow Fish", 14, 7, 9, 0.33F),
	POISON_FISH("Evil Blue Fish", 5, 2, -1, 0.2F),
	GREEN_FISH("Green Fish", 11, 2, 3, 0.2F);
	
	/**
	 * Gets the proper name of the <code>FishType</code>.
	 * TODO: Use this?
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the frequency at which the <code>FishType</code> falls.
	 * TODO: Actually implement this!
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Gets the falling speed in pixels.
	 * @return
	 */
	public int getFallSpeed() {
		return fallSpeed;
	}

	/**
	 * Gets the amount of potential score gain.
	 * @return
	 */
	public int getScoreGain() {
		return scoreGain;
	}
	
	/**
	 * Gets the image scale size.
	 * @return
	 */
	public float getScaleSize() {
		return scaleSize;
	}

	private final String name;
	private final int frequency;
	private final int fallSpeed;
	private final int scoreGain;
	private final float scaleSize;
	
	/**
	 * The constructor of this class.
	 * @param name
	 * @param frequency
	 * @param fallSpeed
	 * @param scoreGain
	 * @param scaleSize
	 */
	private FishType(String name, int frequency, int fallSpeed, int scoreGain, float scaleSize) {
		this.name = name;
		this.frequency = frequency;
		this.fallSpeed = fallSpeed;
		this.scoreGain = scoreGain;
		this.scaleSize = scaleSize;
	}

}
