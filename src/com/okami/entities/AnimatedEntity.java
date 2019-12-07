package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.okami.graficos.Spritesheet;

public abstract class AnimatedEntity extends Entity {
	
	List<BufferedImage> spritesIdle;
	List<BufferedImage> spritesRun;
	
	// Controle para na contagem de frames para exibição da animação.
	public int frame=0, maxFrame=4;
	// Controle do index da animação.
	public int indexRunning=0, maxIndexRunning=7;
	public int indexIdle=0, maxIndexIdle=10;
	protected String spritePathIdle;
	protected String spritePathRun;
	protected int spriteWidthIdle;
	protected int spriteWidthRun;
	
	public AnimatedEntity(int x, int y, int width, int height) {
		super(x, y, width, height);
		initSprites();
		initIndex();
	}

	public AnimatedEntity(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		initSprites();
	}
	
	protected abstract void initSprites();
	protected abstract void initIndex();
	
	protected void initSpriteIdle() {
		spritesIdle = new ArrayList<BufferedImage>();
		initSprite(getSpriteWidthIdle(), spritesIdle, getSpritesheetIdle());
	}
	
	protected void initSpriteRun() {
		spritesRun = new ArrayList<BufferedImage>();
		initSprite(getSpriteWidthRun(), spritesRun, getSpritesheetRun());
	}
	
	protected void initSprite(int size, List<BufferedImage> sprites, Spritesheet spriteSheet){
		BufferedImage actualSprite;
		for(int i = 0; i < size; i+=width) {
			actualSprite = spriteSheet.getSprite(i, 0, width, height);
			sprites.add(actualSprite);
		}
	}
	
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(spritesIdle.get(indexIdle), getX() - (int)offsetCoordinateX, getY() - (int)offsetCoordinateY, null);
	}
	
	public abstract Spritesheet getSpritesheetIdle();
	public abstract Spritesheet getSpritesheetRun();
	
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
	
	public abstract String getSpritePathIdle() ;

	public abstract String getSpritePathRun();

	public abstract int getSpriteWidthIdle();

	public abstract int getSpriteWidthRun();	
	
}
