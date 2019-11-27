package com.okami.util;

import com.okami.entities.Game;

public class GameStrategy {
	
	public static Game createGame() {
		Game game = new Game();
		game.initMovementActionPlayer();
		game.setPlayer(PlayerStrategy.createPlayer());
		game.getEntities().add(game.getPlayer());
		return game; 
	}
}
