package com.okami.entities;

import java.awt.Graphics;

import com.okami.actions.Action;
import com.okami.actions.CameraMovementAction;
import com.okami.util.Command;
import com.okami.util.Observer;

public abstract class GameObject implements Command, Observer{
	protected double coordinateX;
	protected double coordinateY;
	protected double offsetCoordinateX;
	protected double offsetCoordinateY;
	protected int layer = 0;
	public abstract void render(Graphics graphics);

	public abstract void tick();

	@Override
	public void execute() {
		
	}
	
	protected void updateOffset(CameraMovementAction action){
		offsetCoordinateX = action.getxCoordinate();
		offsetCoordinateY = action.getyCoordinate();
	}
	
	@Override
	public void apply(Action action) {
		if(action instanceof CameraMovementAction) {
			updateOffset((CameraMovementAction)action);
		}
	}
	
}
