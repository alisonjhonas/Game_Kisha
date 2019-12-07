package com.okami.factories;

import com.okami.tiles.FloorTile;
import com.okami.tiles.RoofTile;
import com.okami.tiles.Tile;
import com.okami.tiles.WallTile;

public class TileFactory {
	
	public static Tile create(Integer tileColor, int x, int y) {
		
		Tile tile = new FloorTile(x, y, Tile.getSpriteTile(tileColor));
		
		if(tile.isWallTileColor(tileColor)) {
			tile = new WallTile(x, y, Tile.getSpriteTile(tileColor));
		}else if(tile.isRoofTileColor(tileColor)) {
			tile = new RoofTile(x, y, Tile.getSpriteTile(tileColor));
		}
		
		return tile;
	}
	
}

