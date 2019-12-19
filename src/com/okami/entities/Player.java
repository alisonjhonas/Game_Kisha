package com.okami.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.okami.actions.Action;
import com.okami.actions.KeyBoardAction;
import com.okami.graficos.Spritesheet;
import com.okami.util.Observer;

public class Player extends AnimatedEntity{
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/IdleShadow2.png");
	private static Spritesheet spriteSheetRun = new Spritesheet("/RunShadow.png");
	
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
		layer = 1;
		initMovementActionPlayer();
		initBodyColide();
	}
	
	@Override
	public void tick(){
		updateBodyColide();
		isPlayerMoving = false;
		if(right) {
			if(isMovementeAllowed(body.getX()+body.getWidth()+speed, body.getY()) && isMovementeAllowed(body.getX()+body.getWidth()+speed, body.getY()+bode)) {
				coordinateX+=speed;
				isPlayerMoving = true;
			}
			if(directionMovement != RIGHT) {
				directionMovement = RIGHT;
				coordinateX += 20;
			}
		}else if(left) {
			if(isMovementeAllowed(body.getX()-speed, body.getY())) {
				coordinateX-=speed;
				isPlayerMoving = true;
			}
			if(directionMovement != LEFT) {
				directionMovement = LEFT;
				coordinateX -= 20;
			}
		}
		
		if (up && isMovementeAllowed(body.getX(), body.getY()-speed)) {
			coordinateY-=speed;
			isPlayerMoving = true;
		} else if (down && isMovementeAllowed(body.getX(), body.getY()+body.getHeight()+speed)) {
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

	/**
	 * @return
	 */
	private boolean isMovementeAllowed(double x, double y) {
		return collisionStrategy.isTileFree(layer, x, y);
	}
	
	@Override
	public void render(Graphics graphics) {
		if(isPlayerMoving) {
			if(directionMovement == RIGHT) {
				graphics.drawImage(spritesRun.get(indexRunning), getX() - (int)offsetCoordinateX, getY() - (int)offsetCoordinateY, null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesRun.get(indexRunning), (getX() + width) - (int)offsetCoordinateX, getY() - (int)offsetCoordinateY, -width, height, null);
			}
		}else {
			if(directionMovement == RIGHT) {
				graphics.drawImage(spritesIdle.get(indexIdle), getX() - (int)offsetCoordinateX, getY() - (int)offsetCoordinateY, null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesIdle.get(indexIdle), (getX() + width) - (int)offsetCoordinateX, getY() - (int)offsetCoordinateY, -width, height, null);
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

	private void initBodyColide(){
		body = new BodyColide(37,13);
	}

	private void updateBodyColide(){
		if(directionMovement == RIGHT) {
			body.update(coordinateX+9, coordinateY+31);
		}else if(directionMovement == LEFT){
			body.update(coordinateX+32, coordinateY+31);
		}
	}

}
