package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.okami.graficos.Spritesheet;

public class Player extends Entity{
	// Constantes que definem a direção do personagem
	public int LEFT = 1, RIGHT = 2;
	
	public boolean right, left, up, down;
	//Variável que indica a direção do personagem
	public int directionMovement = RIGHT;
	//Indica que se o personagem está em movimento
	public boolean isPlayerMoving;
	// Controle para na contagem de frames para exibição da animação.
	public int frame=0, maxFrame=4;
	// Controle do index da animação.
	public int indexRunning=0, maxIndexRunning=7;
	public int indexIdle=0, maxIndexIdle=10;
	List<BufferedImage> spritesIdle;
	List<BufferedImage> spritesRun;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		speed = 1.4;
		isPlayerMoving = false;
		initSpriteIdle();
		initSpriteRight();
	}
	
	@Override
	public void tick(){
		isPlayerMoving = false;
		if(right) {
			x+=speed;
			isPlayerMoving = true;
			directionMovement = RIGHT;
		}else if(left) {
			x-=speed;
			isPlayerMoving = true;
			directionMovement = LEFT;
		}
		
		if(up) {
			y-=speed;
			isPlayerMoving = true;
		}else if(down) {
			y+=speed;
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
				graphics.drawImage(spritesRun.get(indexRunning), getX(), getY(), null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesRun.get(indexRunning), getX() + width, getY(), -width, height, null);
			}
		}else {
			if(directionMovement == RIGHT) {
				graphics.drawImage(spritesIdle.get(indexIdle), getX(), getY(), null);
			}else if(directionMovement == LEFT) {
				graphics.drawImage(spritesIdle.get(indexIdle), getX() + width, getY(), -width, height, null);
			}
		}
	}
	
	private void initSpriteIdle() {
		spritesIdle = new ArrayList<BufferedImage>();
		initSprite("/Idle.png", 858, spritesIdle);
	}
	
	private void initSpriteRight() {
		spritesRun = new ArrayList<BufferedImage>();
		initSprite("/Run.png", 624, spritesRun);
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
