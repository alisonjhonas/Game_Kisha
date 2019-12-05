package com.okami.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.okami.graficos.Spritesheet;
import com.okami.util.Action;
import com.okami.util.KeyBoardAction;
import com.okami.util.Observer;

public class Player extends AnimatedEntity{
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/Idle.png");
	private static Spritesheet spriteSheetRun = new Spritesheet("/Run.png");
	
	// Constantes que definem a direção do personagem
	public int LEFT = 1, RIGHT = 2;
	
	public boolean right, left, up, down;
	//Variável que indica a direção do personagem
	public int directionMovement = RIGHT;
	//Indica que se o personagem está em movimento
	public boolean isPlayerMoving;
	
	Map<Integer, Observer> movementePlayerActions;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		speed = 1.4;
		isPlayerMoving = false;
		initMovementActionPlayer();
	}
	
	@Override
	public void tick(){
		isPlayerMoving = false;
		if(right) {
			coordinateX+=speed;
			isPlayerMoving = true;
			directionMovement = RIGHT;
		}else if(left) {
			coordinateX-=speed;
			isPlayerMoving = true;
			directionMovement = LEFT;
		}
		
		if(up) {
			coordinateY-=speed;
			isPlayerMoving = true;
		}else if(down) {
			coordinateY+=speed;
			isPlayerMoving = true;
		}
		if(isPlayerMoving) {
			frame++;
			if(frame == maxFrame) {
				frame = 0;
				indexRunning++;
				if(indexRunning > maxIndexRunning) {
					indexRunning = 0;
				}
				
			}
		}else {
			frame++;
			if(frame == maxFrame) {
				frame = 0;
				indexIdle++;
				if(indexIdle > maxIndexIdle) {
					indexIdle = 0;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics graphics) {
		if(isPlayerMoving) {
			if(directionMovement == RIGHT) {
				graphics.drawImage(spritesRun.get(indexRunning), getX() - (int)Game.camera.getCoordinateX(), getY() - (int)Game.camera.getCoordinateY(), null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesRun.get(indexRunning), (getX() + width) - (int)Game.camera.getCoordinateX(), getY() - (int)Game.camera.getCoordinateY(), -width, height, null);
			}
		}else {
			if(directionMovement == RIGHT) {
				graphics.drawImage(spritesIdle.get(indexIdle), getX() - (int)Game.camera.getCoordinateX(), getY() - (int)Game.camera.getCoordinateY(), null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesIdle.get(indexIdle), (getX() + width) - (int)Game.camera.getCoordinateX(), getY() - (int)Game.camera.getCoordinateY(), -width, height, null);
			}
		}
	}
	
	private void initMovementActionPlayer() {
		movementePlayerActions = new HashMap<>();
		Observer movePlayerRight = (Action command) -> {right = ((KeyBoardAction)command).isPressed();};
		Observer movePlayerLeft = (Action command) -> {left = ((KeyBoardAction)command).isPressed();};
		Observer movePlayerUp = (Action command) -> {up = ((KeyBoardAction)command).isPressed();};
		Observer movePlayerDown = (Action command) -> {down = ((KeyBoardAction)command).isPressed();};
		
		movementePlayerActions.put(KeyEvent.VK_RIGHT, movePlayerRight);
		movementePlayerActions.put(KeyEvent.VK_D, movePlayerRight);
		
		movementePlayerActions.put(KeyEvent.VK_LEFT, movePlayerLeft);
		movementePlayerActions.put(KeyEvent.VK_A, movePlayerLeft);
		
		movementePlayerActions.put(KeyEvent.VK_UP, movePlayerUp);
		movementePlayerActions.put(KeyEvent.VK_W, movePlayerUp);
		
		movementePlayerActions.put(KeyEvent.VK_DOWN, movePlayerDown);
		movementePlayerActions.put(KeyEvent.VK_S, movePlayerDown);
	}
	
	public Map<Integer, Observer> getMovementePlayerActions() {
		return movementePlayerActions;
	}

	public void setMovementePlayerActions(Map<Integer, Observer> movementePlayerActions) {
		this.movementePlayerActions = movementePlayerActions;
	}

	@Override
	protected void initSprites() {
		initSpriteIdle();
		initSpriteRun();
		
	}

	@Override
	public String getSpritePathIdle() {
		return "/Idle.png";
	}

	@Override
	public String getSpritePathRun() {
		return "/Run.png";
	}

	@Override
	public int getSpriteWidthIdle() {
		return 858;
	}

	@Override
	public int getSpriteWidthRun() {
		return 624;
	}

	@Override
	public Spritesheet getSpritesheetIdle() {
		return spriteSheetIdle;
	}

	@Override
	public Spritesheet getSpritesheetRun() {
		return spriteSheetRun;
	}

	@Override
	protected void initIndex() {
		super.maxIndexIdle = 10;
		super.maxFrame = 4;
		
	}

}
