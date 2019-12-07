package com.okami.factories;

import com.okami.entities.GameObject;
import com.okami.graficos.GameScreen;

public class GameScreenFactory {
	public static GameScreen create(GameObject game) {
		GameScreen gameScreen = new GameScreen();
		gameScreen.setGame(game);
		return gameScreen;
	}
}
