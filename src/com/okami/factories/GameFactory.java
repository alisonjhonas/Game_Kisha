package com.okami.factories;

import com.okami.entities.Game;

public class GameFactory {
	
	public static Game create() {
		Game game = new Game();
		return game;
	}
}
