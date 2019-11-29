package com.okami.util;

public class KeyBoardAction extends Action{
	
	boolean pressed;	
	int keyCode;
	
	public boolean isPressed() {
		return pressed;
	}
	public int getKeyCode() {
		return keyCode;
	}
	
	public static KeyBoardAction createCommand() {
		return new KeyBoardAction();
	}
	
	public KeyBoardAction pressed (boolean pressed) {
		this.pressed = pressed;
		return this;
		
	}
	
	public KeyBoardAction keyCode (int keyCode) {
		this.keyCode = keyCode;
		return this;
	}
	
}
