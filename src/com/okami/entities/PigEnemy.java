package com.okami.entities;

import com.okami.graficos.Spritesheet;

public class PigEnemy extends Enemy {
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/PigIdleShadow.png");
	
	public PigEnemy(int x, int y) {
		super(x, y, 34, 28);
		speed = 1.2;
		layer = 1;
		initBodyColide();
	}
	
	@Override
	public void tick() {
		updateBodyColide();
		huntPlayer();
		super.tick();
	}

	@Override
	protected void initSprites() {
		initSpriteIdle();
		
	}

	@Override
	public String getSpritePathIdle() {
		return "/PigIdle.png";
	}

	@Override
	public String getSpritePathRun() {
		return "/PigRun.png";
	}

	@Override
	public int getSpriteWidthIdle() {
		return 374;
	}

	@Override
	public int getSpriteWidthRun() {
		return 204;
	}

	@Override
	public Spritesheet getSpritesheetIdle() {
		return spriteSheetIdle;
	}

	@Override
	public Spritesheet getSpritesheetRun() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void huntPlayer() {
		if(coordinateX < player.getBody().getX() && isMoveRightAllowed()) {
			coordinateX+=speed;
		}else if(coordinateX > player.getBody().getX() && isMoveLeftAllowed()) {
			coordinateX-=speed;
		}
		
		if(coordinateY < player.getBody().getY() && isMoveDownAllowed()) {
			coordinateY+=speed;
		}else if(coordinateY > player.getBody().getY() && isMoveUpAllowed()) {
			coordinateY-=speed;
		}
	}

	/**
	 * @return
	 */
	private boolean isMoveRightAllowed() {
		return isMoveAllowed(body.getX()+speed, body.getY());
	}

	/**
	 * @return
	 */
	private boolean isMoveLeftAllowed() {
		return isMoveAllowed(body.getX()-speed, body.getY());
	}
	/**
	 * @return
	 */
	private boolean isMoveUpAllowed() {
		return isMoveAllowed(body.getX(), body.getY()-speed);
	}
	/**
	 * @return
	 */
	private boolean isMoveDownAllowed() {
		return isMoveAllowed(body.getX(), body.getY()+speed);
	}
	/**
	 * @return
	 */
	private boolean isMoveAllowed(double x, double y) {
		return collisionStrategy.isTileFree(layer, x, y);
	}
	private void initBodyColide(){
		body = new BodyColide(37,13);
	}

	private void updateBodyColide(){
		body.update(coordinateX+9, coordinateY+31);
	}
	@Override
	protected void initIndex() {
		super.maxIndexIdle = 10;
		super.maxFrame = 7;
		
	}
	
}
