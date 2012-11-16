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

import java.util.ArrayList;
import java.util.List;

import net.skyrealm.bfish.Main;
import net.skyrealm.bfish.model.Bomb;
import net.skyrealm.bfish.model.Direction;
import net.skyrealm.bfish.model.Entity;
import net.skyrealm.bfish.model.Explosion;
import net.skyrealm.bfish.model.Fish;
import net.skyrealm.bfish.model.FishType;
import net.skyrealm.bfish.model.Player;
import net.skyrealm.bfish.util.Utils;
import net.skyrealm.bfish.util.timer.TimerScheduler;
import net.skyrealm.bfish.util.timer.ext.GameTimer;
import static net.skyrealm.bfish.Main.IMG_PATH;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Represents a world.
 * @author justin.zeng1
 *
 */

public class World {
	
	/**
	 * World constants.
	 */
	public static final int GROUND_LEVEL = 250;
	public static final int GROUND_OFFSET = 4;
	public static final double GRAVITY = 0.5;
	public static final double ACCELERATION = 0.9;
	public static final int JUMP_POWER = 11;
	
	private static final int INIT_TIME_LEFT = 61;
	private static final int FISH_SPAWN_RATE = 30; // orig 50
	private static final int SPAWN_EDGE_DIFF = 25;
	private static final int BOMB_FREQUENCY = 4;
	
	public GameStage stage;
	
	private Image titleScreen;
	private Image background;
	private Ground ground;
	private List<Entity> entities;
	private Player player;
	private int timeLeft = INIT_TIME_LEFT;
	private boolean isDisplayingRules = false;
	private long lastUpdateTime = System.currentTimeMillis();
	
	/**
	 * The constructor of this class.
	 * Initialization that does not explicitly
	 * call Slick methods should go here.
	 * @param maxNpcs
	 */
	public World() {
		ground = new Ground(GROUND_LEVEL);
		player = new Player(Main.WIDTH / 2, 0, "Bika");
		entities = new ArrayList<Entity>();
		stage = GameStage.GAME_MENU;
	}

	/**
	 * Initializes the game. Slick-specific methods
	 * such as image and resource loading should go here.
	 * @param container
	 */
	public void init(GameContainer container) {
		try {
			player.initAnimation();
			player.setCurrentCostume(new Image(IMG_PATH + "player/normal.gif"));
			Fish.initImages();
			background = new Image(IMG_PATH + "bg.png");
			titleScreen = new Image(IMG_PATH + "title.png");
			
			Bomb.initAnimation();
			Explosion.setImage(new Image(IMG_PATH + "pow.png"));
		} catch (SlickException se) {
			se.printStackTrace();
		}
	}
	
	/**
	 * Renders the game.
	 * @param container
	 * @param g
	 */
	public void render(GameContainer container, Graphics g) {
		
		/*
		 * TODO: Don't hardcode Y coordinates.
		 */
		if (isDisplayingRules) {
			g.setColor(Color.white);
			g.drawString("Welcome to Bika's Fish!", 5, 5);
			g.drawString("In this game, you play as 'Bika' the penguin and your", 5, 20);
			g.drawString("primary task is simply to catch fish that fall down.", 5, 35);
			g.drawString("Here is a list of all the fish and their scores:", 5, 65);
			for (int i = 0; i < FishType.values().length; i++) {
				FishType fishType = FishType.values()[i];
				g.drawString(fishType.getName() + " => " + fishType.getScoreGain(), 5, 80 + i * 15);
			}
			g.drawString("Bombs are risky. You may either gain or lose lifepoints", 5, 95 + FishType.values().length * 15);
			g.drawString("depending on how you jump on them; be very cautious!", 5, 110 + FishType.values().length * 15);
			
			g.drawString("The controls are relatively simple; use the left and", 5, 140 + FishType.values().length * 15);
			g.drawString("right arrow keys to walk and the spacebar to jump.", 5, 155 + FishType.values().length * 15);

			g.drawString("Any questions? Visit our site at http://skyrealm.net!", 5, 185 + FishType.values().length * 15);
			g.drawString("Have fun! You may type '1' to unpause the game.", 5, 200 + FishType.values().length * 15);
			return;
		}
		
		/*
		 * Return if the stage is at the game menu.
		 */
		if (stage == GameStage.GAME_MENU) {
			titleScreen.draw(0, 0);
			g.setColor(Color.red);
			g.drawString("Type the '1' key for help. Alternatively, click to play!", 5, Main.HEIGHT - 20);
			return;
		}
		
		/*
		 * Draw the game background.
		 */
		background.draw(0, 0);
		
		/*
		 * Draw the ground.
		 */
		ground.render(container, g);
		
		/*
		 * Return if the game is at the game over stage.
		 */
		if (stage == GameStage.GAME_OVER) {
			g.drawString("Game Over!", Main.WIDTH / 2 - 50, Main.HEIGHT / 2 - 50);
			g.drawString("Score: " + player.getScore(), Main.WIDTH / 2 - 50, Main.HEIGHT / 2 - 35);
			return;
		}
		
		/*
		 * Draw the explosions first.
		 */
		for (Entity e : entities) {
			if (e instanceof Explosion) {
				e.render(container, g);
			}
		}
		
		/*
		 * Draw the other entities last.
		 */
		for (Entity e : entities) {
			if (!(e instanceof Explosion)) {
				e.render(container, g);
			}
		}
		
		/*
		 * Draw the player.
		 */
		player.render(container, g);
		
		/*
		g.drawString("X: " + player.getX(), 5, 5);
		g.drawString("Y: " + player.getY(), 5, 20);
		*/
		
		g.drawString("Score: " + player.getScore(), 5, 5);
		g.drawString("Time:  " + timeLeft, 5, 20);
	}

	/**
	 * Updates the game. Called before every render.
	 * @param container
	 * @param delta
	 */
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		
		/*
		 * Toggle the rules.
		 */
		if (input.isKeyPressed(Input.KEY_1)) {
			isDisplayingRules = !isDisplayingRules;
			System.out.println("You are now reading the rules: " + isDisplayingRules);
		}
		if (isDisplayingRules) {
			return;
		}
		
		/*
		 * Handle mouse clicks.
		 * TODO: Move to its own method?
		 */
		if (stage == GameStage.GAME_OVER || stage == GameStage.GAME_MENU) {
			if (input.isMousePressed(0)) {
				if (stage == GameStage.GAME_MENU) {
					stage = GameStage.GAME_PLAY;
					TimerScheduler.getSingleton().scheduleNewTask(new GameTimer(), 1000);
				} else {
					return;
				}
			}
		}
		
		/*
		 * Game over if the timer is less than zero.
		 */
		if (timeLeft < 0) {
			stage = GameStage.GAME_OVER;
			return;
		}
		
		/*
		 * Handle player movement.
		 */
		player.setIsMoving(false);
		if (input.isKeyDown(Input.KEY_LEFT)) {
			if (player.getX() > 0 && Math.abs(player.getXVelocity()) < Player.MAX_SPEED) {
				player.setXVelocity(player.getXVelocity() - 1);
				player.setIsMoving(true);
			}
			player.setDirection(Direction.WEST);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			if (player.getX() < Main.WIDTH && Math.abs(player.getXVelocity()) < Player.MAX_SPEED) {
				player.setXVelocity(player.getXVelocity() + 1);
				player.setIsMoving(true);
			}
			player.setDirection(Direction.EAST);
		}
		if (ground.isTouching(player)) {
			player.setYVelocity(-1);
			if (input.isKeyDown(Input.KEY_SPACE)) {
				player.setYVelocity(-1 * JUMP_POWER);
			}
		}
		player.setXVelocity(player.getXVelocity() * ACCELERATION);
		player.setYVelocity(player.getYVelocity() + GRAVITY);
		player.setX((int) Math.round(player.getX() + player.getXVelocity()));
		player.setY((int) Math.round(player.getY() + player.getYVelocity()));
		
		/*
		 * Ground displacement glitch "fix".
		 */
		if (player.getYVelocity() == -1 * GRAVITY && ground.isTouching(player) && player.getY() != ground.getY()) {
			player.setY(ground.getY() - player.getCurrentCostume().getHeight());
		}
	
		/*
		 * Secondary boundary checking.
		 */
		if (player.getX() < 0) {
			player.setX(0);
		}
		if (player.getX() + player.getCurrentCostume().getWidth() > Main.WIDTH) {
			player.setX(Main.WIDTH - player.getCurrentCostume().getWidth());
		}
		
		/*
		 * Remove explosions.
		 */
		if (container.getTime() - lastUpdateTime > 500) {
			List<Explosion> explosions = new ArrayList<Explosion>();
			for (Entity e : entities) {
				if (e instanceof Explosion) {
					explosions.add((Explosion) e);
				}
			}
			for (Explosion ex : explosions) {
				entities.remove(ex);
			}
			lastUpdateTime = container.getTime();
		}
		
		List<Entity> entitiesToRemove = new ArrayList<Entity>();
		Bomb explosionBomb = null;
		boolean addBomb = false;
		
		/*
		 * Loop through each entity.
		 */
		for (Entity e : entities) {
			/*
			 * Use a specific boundary checking for bombs.
			 */
			if (!(e instanceof Bomb)) {
				if (ground.isTouching(e)) {
					entitiesToRemove.add(e);
				}
			} else {
				Bomb b = (Bomb) e;
				if (ground.isTouching(b)) {
					entitiesToRemove.add(b);
				}
			}
			/*
			 * Handle the fish.
			 */
			if (e instanceof Fish) {
				Fish f = (Fish) e;
				f.setY(f.getY() + f.getType().getFallSpeed());
				
				Image fishCostume = f.getCostume();
				Rectangle fishRect = new Rectangle(f.getX(), f.getY(), fishCostume.getWidth(), fishCostume.getHeight());
				Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getCurrentCostume().getWidth(), player.getCurrentCostume().getHeight());
				
				if (fishRect.intersects(playerRect)) {
					entitiesToRemove.add(f);
					player.addScore(f.getType().getScoreGain());
				}
			}
			/*
			 * Handle the bombs.
			 */
			if (e instanceof Bomb) {
				Bomb b = (Bomb) e;
				Rectangle bombRect = new Rectangle(b.getX(), b.getY(), Bomb.getAnimation().getImage(0).getWidth(), Bomb.getAnimation().getImage(0).getHeight());
				Rectangle playerRect = new Rectangle(player.getX(), player.getY(), player.getCurrentCostume().getWidth(), player.getCurrentCostume().getHeight());
				if (bombRect.intersects(playerRect)) {
					if (player.getY() < Math.round(b.getY() * 1.2)) {
						/*
						 * You jump successfully on the bomb!
						 */
						player.addScore(1);
					} else {
						/*
						 * You aren't successful at jumping!
						 */
						player.addScore(-3);
						entitiesToRemove.add(b);
						explosionBomb = b;
						addBomb = true;
					}
					/*
					 * Boing!
					 */
					player.setYVelocity(-1 * JUMP_POWER);
					player.setXVelocity(player.getXVelocity() * -1);
				}
				b.setY(b.getY() + 2);
			}
		}
		
		/*
		 * Add an explosion (if any).
		 */
		if (addBomb) {
			entities.add(new Explosion(explosionBomb.getX() - Explosion.getImage().getWidth() / 2, explosionBomb.getY()));
		}
		
		/*
		 * Remove the trashed entities.
		 */
		for (Entity e : entitiesToRemove) {
			entities.remove(e);
		}
		
		/*
		 * Spawn a fish or bomb.
		 */
		if (Utils.random(FISH_SPAWN_RATE) == 1 || entities.size() < 1) {
			if (Utils.random(BOMB_FREQUENCY) == 0) {
				entities.add(new Bomb(Utils.random(Main.WIDTH - SPAWN_EDGE_DIFF), 0));
			} else {
				FishType fishType = FishType.values()[Utils.random(FishType.values().length - 1)];
				entities.add(new Fish(fishType, Utils.random(Main.WIDTH - SPAWN_EDGE_DIFF), 0));
			}
		}
		
		/*
		 * We don't want negative score.
		 */
		if (player.getScore() < 0) {
			player.setScore(0);
		}
	}
	
	/**
	 * Gets the <code>Ground</code>.
	 * @return
	 */
	public Ground getGround() {
		return ground;
	}
	
	/**
	 * Gets the time left of the game.
	 * @return
	 */
	public int getTimeLeft() {
		return timeLeft;
	}
	
	/**
	 * Adds time to the time left.
	 * @param time
	 */
	public void addTime(int time) {
		timeLeft += time;
	}
	
	/**
	 * Sets the time left.
	 * @param timeLeft
	 */
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	/**
	 * Gets the list of the entities.
	 * @return
	 */
	public List<Entity> getEntities() {
		return entities;
	}

}
