package com.okami.actions;

public class WorldMapAction extends Action {

	int xCoordinate;
	int yCoordinate;
	int color;
	
	private WorldMapAction() {
		
	}
	
	public static WorldMapAction builder() {
		return new WorldMapAction();
	}
	
	public WorldMapAction xCoordinate(int x) {
		this.xCoordinate = x;
		return this;
	}
	
	public WorldMapAction yCoordinate(int y) {
		this.yCoordinate = y;
		return this;
	}
	
	public WorldMapAction color(int color) {
		this.color = color;
		return this;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public int getColor() {
		return color;
	}
	
}
