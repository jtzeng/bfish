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

package net.skyrealm.bfish.util;

import org.newdawn.slick.Color;

public class Utils {

	/**
	 * Returns a random integer with range
	 * zero to the parameter (included).
	 * @param range
	 * @return
	 */
	public static int random(int range) {
		return (int) (java.lang.Math.random() * (range + 1));
	}

	/**
	 * Returns a random float with range
	 * zero to the parameter (included).
	 * @param range
	 * @return
	 */
	public static float random(float range) {
		return (float) (java.lang.Math.random() * (range + 1));
	}

	/**
	 * Returns a random <code>Color</code>.
	 * @return
	 */
	public static Color getRandomColor() {
		return new Color(random(1.0f), random(1.0f), random(1.0f), 1.0f);
	}

}