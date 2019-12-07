package com.okami.entities;

import com.okami.graficos.Spritesheet;

public class PigEnemy extends Enemy {
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/PigIdleShadow.png");
	
	public PigEnemy(int x, int y) {
		super(x, y, 34, 28);
	}
	
	@Override
	public void tick() {
		frame++;
		if(frame == maxFrame) {
			frame = 0;
			indexIdle++;
			if(indexIdle > maxIndexIdle) {
				indexIdle = 0;
			}
		}
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

	@Override
	protected void initIndex() {
		super.maxIndexIdle = 10;
		super.maxFrame = 7;
		
	}
	
}
