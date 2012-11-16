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
import org.newdawn.slick.Image;

/**
 * Represents an explosion.
 * @author justin.zeng1
 *
 */

public final class Explosion extends Entity implements Drawable {
	
	private static Image image;

	/**
	 * The constructor.
	 * @param type
	 * @param x
	 * @param y
	 */
	public Explosion(int x, int y) {
		super(x, y, "Bomb");
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		g.drawImage(image, getX(), getY());
	}
	
	/**
	 * Gets the <code>Image</code> of the explosion.
	 * @return
	 */
	public static Image getImage() {
		return image;
	}
	
	/**
	 * Sets the <code>Image</code>.
	 * @param image
	 */
	public static void setImage(Image image) {
		Explosion.image = image;
	}

}
