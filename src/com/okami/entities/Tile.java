package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.okami.graficos.Spritesheet;

public class Tile extends GameObject {
	protected static int TILE_DIMENSION = 32;
//	private static Spritesheet spritesheet = new Spritesheet("/Terrain.png");
//	protected static BufferedImage FLOOR_TILE = spritesheet.getSprite(160, 352, TILE_DIMENSION, TILE_DIMENSION);
//	protected static BufferedImage WALL_TILE = spritesheet.getSprite(160, 160, TILE_DIMENSION, TILE_DIMENSION);
	private static Spritesheet spritesheet = new Spritesheet("/Mega.png");
	protected static BufferedImage FLOOR_TILE = spritesheet.getSprite(256, 832, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage WALL_TILE = spritesheet.getSprite(128, 32, TILE_DIMENSION, TILE_DIMENSION);
	
	private int x, y;
	BufferedImage sprite;
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX() - (int) Game.camera.getCoordinateX(), this.getY() - (int)Game.camera.getCoordinateY(), null);		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
