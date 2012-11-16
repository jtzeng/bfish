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

package net.skyrealm.bfish.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import net.skyrealm.bfish.Main;
import net.skyrealm.bfish.model.Bomb;
import net.skyrealm.bfish.model.Drawable;
import net.skyrealm.bfish.model.Entity;
import net.skyrealm.bfish.model.Player;

/**
 * Represents the ground.
 * @author justin.zeng1
 *
 */

public class Ground implements Drawable {
	private int y;
	
	/**
	 * The constructor of this class.
	 * @param y
	 */
	public Ground(int y) {
		this.y = y;
	}
	
	/**
	 * Gets the Y value at which the
	 * <code>Ground</code> is currently located at.
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Is the <code>Ground</code> touching the <code>Entity</code>?
	 * TODO: Entities should share a common variable containing their
	 * sprite dimensions to be used in boundary and collision checking.
	 * @param e
	 * @return
	 */
	public boolean isTouching(Entity e) {
		return e.getY() >= y;
	}
	
	/**
	 * Is the <code>Ground</code> touching the <code>Player</code>?
	 * @param p
	 * @return
	 */
	public boolean isTouching(Player p) {
		return p.getY() + p.getCurrentCostume().getHeight() >= y;
	}
	
	/**
	 * Is the <code>Ground</code> touching a <code>Bomb</code>?
	 * @param b
	 * @return
	 */
	public boolean isTouching(Bomb b) {
		return b.getY() + Bomb.getAnimation().getCurrentFrame().getHeight() >= y;
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		/*
		 * Draw the ground.
		 */
		g.setColor(Color.green);
		g.fillRect(0, y + World.GROUND_OFFSET, Main.WIDTH, Main.HEIGHT);
		
		/*
		 * Draw the surface.
		 */
		g.setColor(Color.orange.darker().darker());
		g.fillRect(0, y, Main.WIDTH, World.GROUND_OFFSET);
	}
}
