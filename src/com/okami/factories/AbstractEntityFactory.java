package com.okami.factories;

import com.okami.tiles.Tile;

public abstract class AbstractEntityFactory {
	
	
	public static EntityFactory create(Integer colorEntity) throws Exception{
		EntityFactory facntory = null;
		if(colorEntity.equals(Tile.PLAYER_COLOR)) {
			facntory = new PlayerFactory();
		}else if(colorEntity.equals(Tile.ENEMY_COLOR)) {
			facntory = new EnemyFactory();
		}else if(colorEntity.equals(Tile.BULLET_COLOR)) {
			facntory = new BulletFactory();
		}else if(colorEntity.equals(Tile.LIFE_COLOR)) {
			facntory = new LifeFactory();
		}else {
			throw new Exception();
		}
		return facntory;
	}

}
