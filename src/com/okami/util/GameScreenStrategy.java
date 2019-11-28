package com.okami.util;

import com.okami.entities.GameObject;
import com.okami.graficos.GameScreen;

public class GameScreenStrategy {
	public static GameScreen createGameScreen(GameObject game) {
		GameScreen gameScreen = new GameScreen();
		gameScreen.setGame(game);
		return gameScreen;
	}
}
