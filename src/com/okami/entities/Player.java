package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.okami.graficos.Spritesheet;

public class Player extends Entity{
	public int LEFT = 1, RIGHT = 2;
	public boolean right, left, up, down;
	public int direction = RIGHT;
	public boolean moved;
	// Controle para na contagem de frames para exibição da animação.
	public int frame=0, maxFrame=4;
	// Controle do index da animação.
	public int index=0, maxIndex=7;
	public int indexStoped=0, maxIndexStoped=10;
	List<BufferedImage> spritesIdle;
	List<BufferedImage> spritesLeft;
	List<BufferedImage> spritesRight;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		speed = 1.4;
		moved = false;
		initSpriteIdle();
		initSpriteLeft();
		initSpriteRight();
	}
	
	@Override
	public void tick(){
		moved = false;
		if(right) {
			x+=speed;
			moved = true;
			direction = RIGHT;
		}else if(left) {
			x-=speed;
			moved = true;
			direction = LEFT;
		}
		
		if(up) {
			y-=speed;
			moved = true;
		}else if(down) {
			y+=speed;
			moved = true;
		}
		if(moved) {
			frame++;
			if(frame == maxFrame) {
				frame = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
				
			}
		}else {
			frame++;
			if(frame == maxFrame) {
				frame = 0;
				indexStoped++;
				if(indexStoped > maxIndexStoped) {
					indexStoped = 0;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics graphics) {
		if(moved) {
			if(direction == RIGHT) {
				graphics.drawImage(spritesRight.get(index), getX(), getY(), null);
			}else if(direction == LEFT) {
				graphics.drawImage(spritesLeft.get(index), getX(), getY(), null);
			}
		}else {
			if(direction == RIGHT) {
				graphics.drawImage(spritesIdle.get(indexStoped), getX(), getY(), null);
			}else if(direction == LEFT) {
				graphics.drawImage(spritesIdle.get(indexStoped), getX() + width, getY(), -width, height, null);
			}
		}
	}
	
	private void initSpriteIdle() {
		spritesIdle = new ArrayList<BufferedImage>();
		initSprite("/Idle.png", 858, spritesIdle);
	}
	
	private void initSpriteRight() {
		spritesRight = new ArrayList<BufferedImage>();
		initSprite("/Run.png", 624, spritesRight);
	}
	
	private void initSpriteLeft() {
		spritesLeft = new ArrayList<BufferedImage>();
		initSprite("/Run_Left.png", 624, spritesLeft);
	}
	
	private void initSprite(String path, int size, List<BufferedImage> sprites){
		Spritesheet spritesheet = new Spritesheet(path);
		BufferedImage actualSprite;
		for(int i = 0; i < size; i+=width) {
			actualSprite = spritesheet.getSprite(i, 0, width, height);
			sprites.add(actualSprite);
		}
	}
}
