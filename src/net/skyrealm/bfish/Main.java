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

package net.skyrealm.bfish;

import net.skyrealm.bfish.util.timer.TimerScheduler;
import net.skyrealm.bfish.world.GameStage;
import net.skyrealm.bfish.world.World;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * The game's main class.
 * @author justin.zeng1
 *
 */

public class Main extends BasicGame {
	
	/**
	 * Game constants.
	 */
	public static final String TITLE = "Bika's Fish";
	public static final int WIDTH = 512;
	public static final int HEIGHT = 360;
	public static final int FPS_RATE = 60;
	public static final String IMG_PATH = "./res/";
	
	private static AppGameContainer container;
	private static World world;
	
	/**
	 * The constructor for this class.
	 * @param title
	 */
	public Main(String title) {
		super(title);
		world = new World();
	}
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		world.render(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		world.init(container);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		world.update(container, delta);
	}

	/**
	 * The main entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/*
			 * Start the game!
			 */
			container = new AppGameContainer(new Main(TITLE), WIDTH, HEIGHT, false);
			container.setAlwaysRender(true);
			container.setShowFPS(false);
			container.setTargetFrameRate(FPS_RATE);
			container.setVSync(true);
			container.start();
		} catch (SlickException se) {
			se.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		try {
			if (world.stage == GameStage.GAME_OVER) {
				TimerScheduler.resetTasks();
				world = new World();
				container.reinit();
			}
		} catch (SlickException se) {
			se.printStackTrace();
		}
	}
	
	/**
	 * Gets the <code>AppGameContainer</code>.
	 * @return
	 */
	public static AppGameContainer getContainer() {
		return container;
	}
	
	/**
	 * Gets the <code>World</code>.
	 * @return
	 */
	public static World getWorld() {
		return world;
	}

}
