package com.okami.util;

import com.okami.entities.Entity;
import com.okami.entities.Game;
import com.okami.tiles.Tile;

public class LayerCollisionStrategy extends CollisionStrategy  {

	public LayerCollisionStrategy(Game game) {
		super(game);
	}

	@Override
	public boolean isTileFree(int layer, double x, double y) {
		return isTileWorldFree(layer, x, y) && haveNoEntityOnXandYCoordinate(layer, x, y);
	}

	/**
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isTileWorldFree(int layer, double x, double y) {
		Tile tile = getTile(x, y);
		return tile != null ? tile.getLayer() < layer : false;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private Tile getTile(double x, double y) {
		x = getInitialTileCoordinate((int)x);
		y = getInitialTileCoordinate((int)y);
		Tile tile = game.getWorld().getTile((int)x, (int)y);
		return tile;
	}
	
	public double getInitialTileCoordinate(int coordinate) {
		return coordinate / Tile.TILE_DIMENSION;
	}
	
	private boolean haveNoEntityOnXandYCoordinate(int layer, double x, double y) {
		Tile tileEntity = null;
		Tile tile = getTile(x, y);
		
		for(Entity entity : game.getEntities()) {
			tileEntity = getTile(entity.getBody().getX(), entity.getBody().getY());
			if(tileEntity  != null && tile != null && tileEntity.equals(tile) && layer == entity.getLayer()) {
				return false;
			}
		}
		return true;
	}
}
