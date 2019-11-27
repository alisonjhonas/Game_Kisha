package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity implements GameObject {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	protected double speed = 0.0;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX(), this.getY(), null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
}
