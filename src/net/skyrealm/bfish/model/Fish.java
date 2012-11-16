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

import java.util.HashMap;
import java.util.Map;

import static net.skyrealm.bfish.Main.IMG_PATH;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a fish.
 * @author justin.zeng1
 *
 */

public final class Fish extends Entity implements Drawable {

	private FishType type;
	
	private static Map<FishType, Image> costumes = new HashMap<FishType, Image>();

	/**
	 * The constructor of this class.
	 * @param type
	 * @param x
	 * @param y
	 */
	public Fish(FishType type, int x, int y) {
		super(x, y, type.getName());
		this.type = type;
	}
	
	/**
	 * Gets the <code>FishType</code> of the fish.
	 * @return
	 */
	public FishType getType() {
		return type;
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		g.drawImage(getCostume(), getX(), getY());
	}
	
	/**
	 * Gets the costume <code>Image</code>.
	 * @return
	 */
	public Image getCostume() {
		return costumes.get(getType());
	}
	
	/**
	 * Initializes the images for all the fish.
	 * @throws SlickException
	 */
	public static void initImages() throws SlickException {
		for (FishType fishType : FishType.values()) {
			costumes.put(fishType, new Image(IMG_PATH + fishType.name().toLowerCase() + ".gif").getScaledCopy(fishType.getScaleSize()));
		}
	}

}
