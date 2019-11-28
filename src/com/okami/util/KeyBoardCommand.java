package com.okami.util;

public class KeyBoardCommand extends Command{
	
	boolean pressed;	
	int keyCode;
	
	public boolean isPressed() {
		return pressed;
	}
	public int getKeyCode() {
		return keyCode;
	}
	
	public static KeyBoardCommand createCommand() {
		return new KeyBoardCommand();
	}
	
	public KeyBoardCommand pressed (boolean pressed) {
		this.pressed = pressed;
		return this;
		
	}
	
	public KeyBoardCommand keyCode (int keyCode) {
		this.keyCode = keyCode;
		return this;
	}
	
}
