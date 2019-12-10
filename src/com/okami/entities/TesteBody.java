package com.okami.entities;

import java.awt.Color;
import java.awt.Graphics;

public class TesteBody extends Entity{

	public TesteBody(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		
    	graphics.fillRect(getX(), getY(), width, height);
	}
}
