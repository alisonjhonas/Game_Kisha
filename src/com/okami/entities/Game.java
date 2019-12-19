package com.okami.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.okami.actions.Action;
import com.okami.actions.CameraMovementAction;
import com.okami.actions.KeyBoardAction;
import com.okami.actions.WorldMapAction;
import com.okami.factories.AbstractEntityFactory;
import com.okami.graficos.Camera;
import com.okami.graficos.GameScreen;
import com.okami.util.CollisionStrategy;
import com.okami.util.LayerCollisionStrategy;
import com.okami.util.Observer;

public class Game extends GameObject implements Observer{
	
	
	List<Entity> entities;
	Player player;
	Camera camera;
	World world;
	TesteBody testeBody;
	CollisionStrategy collisionStrategy;
	private List<Observer> observers;
	public Game() {
		testeBody = new TesteBody(0, 0, 37, 13);
		observers = new ArrayList<>();
		entities = new ArrayList<Entity>();
		camera = new Camera();
		world = new World();
		collisionStrategy = new LayerCollisionStrategy(this);
		world.registerObserver(this);
		world.buiild("/map-2.png");
		registerObserver(world);
		registerObserver(testeBody);
		setPlayerToEnemyEntity();
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
		if(player.directionMovement == player.RIGHT) {
			testeBody.setX(player.getX() + 9 - (int)player.offsetCoordinateX);
			testeBody.setY(player.getY() + 31- (int)player.offsetCoordinateY);
		}
		else if(player.directionMovement == player.LEFT) {
			testeBody.setX(player.getX() + 32 - (int)player.offsetCoordinateX);
			testeBody.setY(player.getY() + 31 - (int)player.offsetCoordinateY);
		}
		entities.forEach(entity -> entity.tick());
		if(player != null) 
			moveCamera();
	}
	
	public void moveCamera() {
		double newCoordinateX = Math.min(Math.max(player.coordinateX - GameScreen.WIDTH/2, 0), world.getWidth()- GameScreen.WIDTH);
		double newCoordinateY = Math.max(Math.min(player.coordinateY - GameScreen.HEIGHT/2, world.getHeight() - GameScreen.HEIGHT), 0);
		camera.setCoordinateX(newCoordinateX);
		camera.setCoordinateY(newCoordinateY);
		notifyObserver(CameraMovementAction.builder().xCoordinate((int)newCoordinateX).yCoordinate((int)newCoordinateY));
		
	}
	
	public void movePlayer(KeyBoardAction action) {
		if(player.getMovementePlayerActions().containsKey(action.getKeyCode())) {
			player.getMovementePlayerActions().get(action.getKeyCode()).apply(action);
		}
	}
	
	public void addEntity(WorldMapAction action) {
		try {
			Entity entity = AbstractEntityFactory.create(action.getColor()).create();
			entity.setY(action.getyCoordinate());
			entity.setX(action.getxCoordinate());
			entity.setCollisionStrategy(collisionStrategy);
			entities.add(entity);
			observers.add(entity);
			if(entity instanceof Player) {
				player = (Player) entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() {
		
	}

	private void setPlayerToEnemyEntity(){
		for (Entity entity : entities) {
			if(entity instanceof Enemy) {
				((Enemy)entity).setPlayer(player);
			}
		}
	}
	
	@Override
	public void apply(Action action) {
		if(action instanceof KeyBoardAction) {
			movePlayer((KeyBoardAction)action);
		}else if(action instanceof WorldMapAction) {
			addEntity((WorldMapAction)action);
		}
	}
	
	public void notifyObserver(CameraMovementAction action) {
		for (Observer observer : observers) {
			observer.apply(action);
		}
	}
	
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	public World getWorld() {
		return world;
	}
}
