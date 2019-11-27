package com.okami.entities;

import java.awt.Graphics;

public interface GameObject {
	public void render(Graphics graphics);

	public void tick();
}
