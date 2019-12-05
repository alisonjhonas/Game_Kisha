package com.okami.entities;

import java.awt.image.BufferedImage;

import com.okami.graficos.Spritesheet;

public class CannonBall extends Entity {
	
	private static Spritesheet spriteSheetIdle = new Spritesheet("/CannonBallShadow.png");

	public CannonBall(int x, int y) {
		super(x, y, 44, 28, spriteSheetIdle.getSprite(0, 0, 44, 28));
	}

	public CannonBall(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

}
