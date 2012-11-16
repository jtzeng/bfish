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

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Represents an entity.
 * @author justin.zeng1
 *
 */

public abstract class Entity implements Drawable {
	
	private int x, y;
	private String name;
	
	/**
	 * The constructor.
	 * @param x
	 * @param y
	 * @param name
	 */
	public Entity(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	/**
	 * Gets the X coordinate.
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the X coordinate.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the Y coordinate.
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the name of this <code>Entity</code>.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this <code>Entity</code>.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public abstract void render(GameContainer container, Graphics g);
}
