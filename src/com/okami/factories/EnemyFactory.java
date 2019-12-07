package com.okami.factories;

import com.okami.entities.Enemy;
import com.okami.entities.PigEnemy;

public class EnemyFactory extends EntityFactory {

	@Override
	public Enemy create() {
		return new PigEnemy(0, 0);
	}

}
