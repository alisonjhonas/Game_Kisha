package com.okami.util;

import com.okami.entities.Player;

public class PlayerStrategy {
	public static Player createPlayer() {
		return new Player(0, 0, 78, 58, null);
	}
}
