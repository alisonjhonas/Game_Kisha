package com.okami.entities;

public class TileFactory {
	
	public static Tile createTile(Integer tileColor) {
		Tile tile = new Tile(0, 0, Tile.getSpriteTile(tileColor));
		if(tileColor.equals(Tile.FLOOR_TILE_COLOR))
			tile.layer = 0;
		else
			tile.layer = 1;
		return tile;
	}
	
	
	
}

