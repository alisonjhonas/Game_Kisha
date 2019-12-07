package com.okami.factories;

import com.okami.entities.Player;

public class PlayerFactory extends EntityFactory {
	
	public Player create() {
		return new Player(64, 32, 78, 58, null);
	}
}
