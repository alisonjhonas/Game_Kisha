package com.okami.entities;

public abstract class Enemy extends AnimatedEntity {
	
	
	public Player player;
	
	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		layer = 1;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}
