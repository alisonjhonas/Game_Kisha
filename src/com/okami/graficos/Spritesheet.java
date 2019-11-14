package com.okami.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private BufferedImage spritesheet;
	public Spritesheet(String path) {
		try {
			System.out.println(getClass().getResource(path));
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			//TODO adicionar tratamento
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}

}
