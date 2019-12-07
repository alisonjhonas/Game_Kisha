package com.okami.actions;

public class CameraMovementAction extends Action {
	
	int xCoordinate;
	int yCoordinate;
	
	public static CameraMovementAction builder() {
		return new CameraMovementAction();
	}
	
	public CameraMovementAction xCoordinate(int x) {
		this.xCoordinate = x;
		return this;
	}
	
	public CameraMovementAction yCoordinate(int y) {
		this.yCoordinate = y;
		return this;
	}
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}
}
