package com.okami.util;

import com.okami.entities.Game;

public abstract class CollisionStrategy {
	
	Game game;
	
	public CollisionStrategy(Game game) {
		super();
		this.game = game;
	}

	public abstract boolean isTileFree(int layer, double x, double y);

}
