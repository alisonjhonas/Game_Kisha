package com.okami.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.okami.graficos.Camera;
import com.okami.graficos.GameScreen;
import com.okami.util.Action;
import com.okami.util.KeyBoardAction;
import com.okami.util.Observer;
import com.okami.util.PlayerStrategy;

public class Game extends GameObject implements Observer{
	
	
	List<Entity> entities;
	static Player player;
	static Camera camera;
	World world;
	public Game() {
		entities = new ArrayList<Entity>();
		player = PlayerStrategy.createPlayer();
		entities.add(player);
		camera = new Camera();
		world = new World("/map.png");
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	@Override
	public void render(Graphics graphics) {
		world.render(graphics);
		entities.forEach(entity -> entity.render(graphics));
	}
	
	@Override
	public void tick() {		
		entities.forEach(entity -> entity.tick());
		moveCamera();
	}
	
	public void moveCamera() {
		double newCoordinateX = Math.min(Math.max(player.coordinateX - GameScreen.WIDTH/2, 0), GameScreen.WIDTH-80);
		double newCoordinateY = Math.max(Math.min(player.coordinateY - GameScreen.HEIGHT/2, GameScreen.HEIGHT+160), 0);
		camera.setCoordinateX(newCoordinateX);
		camera.setCoordinateY(newCoordinateY);
	}
	
	public void movePlayer(KeyBoardAction command) {
		if(player.getMovementePlayerActions().containsKey(command.getKeyCode())) {
			player.getMovementePlayerActions().get(command.getKeyCode()).apply(command);
		}
	}

	@Override
	public void execute() {
		
	}

	@Override
	public void apply(Action action) {
		if(action instanceof KeyBoardAction) {
			movePlayer((KeyBoardAction)action);
		}
	}
	
}
