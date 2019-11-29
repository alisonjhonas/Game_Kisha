package com.okami.entities;

import java.awt.Graphics;

import com.okami.util.Command;

public abstract class GameObject implements Command {
	public abstract void render(Graphics graphics);

	public abstract void tick();

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
