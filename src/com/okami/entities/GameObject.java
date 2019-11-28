package com.okami.entities;

import java.awt.Graphics;

import com.okami.util.Command;

public interface GameObject {
	public void render(Graphics graphics);

	public void tick();
	
	public void execute(Command command);
}
