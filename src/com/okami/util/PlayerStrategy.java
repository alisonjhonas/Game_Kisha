package com.okami.util;

import com.okami.entities.Player;

public class PlayerStrategy {
	public static Player createPlayer() {
		return new Player(64, 32, 78, 58, null);
	}
}
