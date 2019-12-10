package com.okami.entities;

public class BodyColide {

	private double height;
	private double width;
	private double x, y;

	public BodyColide (double width, double height){
		this.height = height;
		this.width = width;
	}

	public double getHeight(){
		return height;
	}

	public double getWidth(){
		return width;
	}

	public void setHeight(double height){
		this.height = height;
	}

	public void setWidth(double width){
		this.width = width;
	}

	public void update(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}
}