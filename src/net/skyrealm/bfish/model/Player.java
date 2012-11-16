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

import java.util.ArrayList;
import java.util.List;

import net.skyrealm.bfish.Main;
import static net.skyrealm.bfish.Main.IMG_PATH;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents the player.
 * @author justin.zeng1
 *
 */

public class Player extends Entity implements Drawable {
	
	/**
	 * Player constants.
	 */
	public static final int MAX_SPEED = 20;
	private static final int ANIM_DURATION = 100;
	private static final String[] ANIM_PATHS = {
		"walkleft", "normal", "walkleft2", "normal"
	};

	private int score;
	private double xVelocity;
	private double yVelocity;
	
	private List<Image> costumes;
	private Image currentCostume;
	private Animation leftAnimation;
	private Animation rightAnimation;
	
	private Direction direction;
	private boolean isMoving = false;

	/**
	 * The constructor for this class.
	 * @param x
	 * @param y
	 * @param name
	 */
	public Player(int x, int y, String name) {
		super(x, y, name);
		score = 0;
		xVelocity = 0;
		yVelocity = 0;
		direction = Direction.WEST;
		costumes = new ArrayList<Image>();
	}
	
	/**
	 * Gets the <code>Player</code>'s current <code>Direction</code>.
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the <code>Direction</code> for this <code>Player</code>.
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Gets the X velocity.
	 * @return
	 */
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * Sets the X velocity.
	 * @param xVelocity
	 */
	public void setXVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * Gets the Y Velocity.
	 * @return
	 */
	public double getYVelocity() {
		return yVelocity;
	}

	/**
	 * Sets the Y Velocity.
	 * @param yVelocity
	 */
	public void setYVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	/**
	 * Sets the score.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Adds a value to the score.
	 * @param score
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	/**
	 * Get the score.
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Is the <code>Player</code> moving?
	 * @return
	 */
	public boolean isMoving() {
		return isMoving;
	}
	
	/**
	 * Set the <code>Player</code>'s movement.
	 * @param isMoving
	 */
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	@Deprecated
	public void setCostumes(List<Image> costumes) {
		this.costumes = costumes;
	}
	
	@Deprecated
	public boolean addCostume(Image costume) {
		return costumes.add(costume);
	}
	
	@Deprecated
	public List<Image> getCostumes() {
		return costumes;
	}
	
	/**
	 * Gets the current costume.
	 * TODO: Refactor!
	 * 
	 * @return
	 */
	public Image getCurrentCostume() {
		return currentCostume;
	}
	
	/**
	 * Sets the current costume.
	 * TODO: Refactor!
	 * 
	 * @param currentCostume
	 */
	public void setCurrentCostume(Image currentCostume) {
		this.currentCostume = currentCostume;
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		if (!isMoving || !Main.getWorld().getGround().isTouching(this)) {
			g.drawImage(direction == Direction.WEST ? currentCostume : currentCostume.getFlippedCopy(true, false), getX(), getY());
		} else {
			if (direction == Direction.WEST) {
				g.drawAnimation(leftAnimation, getX(), getY());
			} else {
				g.drawAnimation(rightAnimation, getX(), getY());
			}
		}
	}
	
	/**
	 * Initializes the <code>Player</code>'s animations.
	 * TODO: Implement direction handling better! Remove shitty code.
	 * 
	 * @throws SlickException
	 */
	public void initAnimation() throws SlickException {
		Image[] leftFrames = new Image[4];
		for (int i = 0; i < ANIM_PATHS.length; i++) {
			leftFrames[i] = new Image(IMG_PATH + "player/" + ANIM_PATHS[i] + ".gif");
		}
		leftAnimation = new Animation(leftFrames, ANIM_DURATION, true);
		Image[] rightFrames = new Image[4];
		for (int i = 0; i < leftFrames.length; i++) {
			rightFrames[i] = leftFrames[i].getFlippedCopy(true, false);
		}
		rightAnimation = new Animation(rightFrames, ANIM_DURATION, true);
	}

}
