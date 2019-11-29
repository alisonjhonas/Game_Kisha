package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity extends GameObject {
	
	protected double coordinateX;
	protected double coordinateY;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	protected double speed = 0.0;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		super();
		this.coordinateX = x;
		this.coordinateY = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public int getY() {
		return (int) coordinateY;
	}
	
	public void setY(int y) {
		this.coordinateY = y;
	}
	
	public int getX() {
		return (int) coordinateX;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX() - (int)Game.camera.getCoordinateX(), this.getY() - (int)Game.camera.getCoordinateY(), null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
