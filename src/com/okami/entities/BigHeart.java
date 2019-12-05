package com.okami.entities;

import java.awt.image.BufferedImage;

import com.okami.graficos.Spritesheet;

public class BigHeart extends AnimatedEntity {
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/BigHeartIdleShadow.png");
	
	public BigHeart(int x, int y) {
		super(x, y, 18, 14);
	}

	public BigHeart(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	@Override
	protected void initSprites() {
		initSpriteIdle();
	}

	@Override
	public String getSpritePathIdle() {
		return "/BigHeartIdle.png";
	}

	@Override
	public String getSpritePathRun() {
		return null;
	}

	@Override
	public int getSpriteWidthIdle() {
		return 144;
	}

	@Override
	public int getSpriteWidthRun() {
		return 0;
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
		maxIndexIdle = 7;
		maxFrame = 3;
	}
}
