package com.okami.factories;

import com.okami.entities.CannonBall;

public class BulletFactory extends EntityFactory {

	@Override
	public CannonBall create() {
		return new CannonBall(0, 0);
	}

}
