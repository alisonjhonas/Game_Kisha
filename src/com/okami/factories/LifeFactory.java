package com.okami.factories;

import com.okami.entities.BigHeart;

public class LifeFactory extends EntityFactory {

	@Override
	public BigHeart create() {
		return new BigHeart(0, 0);
	}

}
