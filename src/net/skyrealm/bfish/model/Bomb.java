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

import static net.skyrealm.bfish.Main.IMG_PATH;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a bomb.
 * @author justin.zeng1
 *
 */

public final class Bomb extends Entity implements Drawable {
	
	private static Animation animation;
	private static final float SCALE_SIZE = 0.5F;

	/**
	 * The constructor of this <code>Bomb</code>.
	 * @param type
	 * @param x
	 * @param y
	 */
	public Bomb(int x, int y) {
		super(x, y, "Bomb");
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		g.drawAnimation(animation, getX(), getY());
	}
	
	/**
	 * Initializes the <code>Bomb</code>'s animations.
	 * @throws SlickException
	 */
	public static void initAnimation() throws SlickException {
		Image[] frames = new Image[2];
		frames[0] = new Image(IMG_PATH + "bomb.png").getScaledCopy(SCALE_SIZE);
		frames[1] = new Image(IMG_PATH + "bomb2.png").getScaledCopy(SCALE_SIZE);
		animation = new Animation(frames, 500, true);
	}
	
	/**
	 * Gets the <code>Animation</code> of this <code>Bomb</code>.
	 * @return
	 */
	public static Animation getAnimation() {
		return animation;
	}

}
