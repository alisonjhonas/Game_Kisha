package com.okami.util;

import com.okami.entities.World;

public abstract class CollisionStrategy {
	
	World world;
	
	public CollisionStrategy(World world) {
		super();
		this.world = world;
	}

	public abstract boolean isTileFree(int layer, double x, double y);

}
