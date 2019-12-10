package com.okami.util;

import com.okami.entities.World;
import com.okami.tiles.Tile;

public class LayerCollisionStrategy extends CollisionStrategy  {

	public LayerCollisionStrategy(World world) {
		super(world);
	}

	@Override
	public boolean isTileFree(int layer, double x, double y) {
		x = getInitialTileCoordinate((int)x);
		y = getInitialTileCoordinate((int)y);
		System.err.println("Body X: "+(int)x+", Body Y: "+(int)y);
		Tile tile = world.getTile((int)x, (int)y);
		return tile != null ? tile.getLayer() < layer : false;
	}
	
	public double getInitialTileCoordinate(int coordinate) {
		return coordinate / Tile.TILE_DIMENSION;
	}
}
