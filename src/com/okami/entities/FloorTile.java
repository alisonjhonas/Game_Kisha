package com.okami.entities;

import java.awt.image.BufferedImage;

public class FloorTile extends Tile {

	
	public FloorTile(int x, int y){
		super(x, y, FLOOR_TILE);
	}
	
	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

}
