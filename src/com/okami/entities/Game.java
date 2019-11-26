package com.okami.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.okami.util.Command;
import com.okami.util.KeyBoardCommand;
import com.okami.util.Observer;

public class Game {
	
	
	List<Entity> entities;
	Player player;
	Map<Integer, Observer> movementePlayerActions;
	public Game() {
		entities = new ArrayList<Entity>();
		movementePlayerActions = new HashMap<Integer, Observer>();
	}
	
	public static Game createGame() {
		Game game = new Game();
		game.initMovementActionPlayer();
		game.setPlayer(new Player(0, 0, 78, 58, null));
		game.getEntities().add(game.getPlayer());
		return game; 
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
	
	public void render(Graphics graphics) {
		for (Entity entity : entities) {
			entity.render(graphics);
		}
	}
	
	public void tick() {
		for (Entity entity : entities) {
			entity.tick();
		}
	}
	
	public void initMovementActionPlayer() {
		Observer movePlayerRight = (Command command) -> {player.right = ((KeyBoardCommand)command).isPressed();};
		Observer movePlayerLeft = (Command command) -> {player.left = ((KeyBoardCommand)command).isPressed();};
		Observer movePlayerUp = (Command command) -> {player.up = ((KeyBoardCommand)command).isPressed();};
		Observer movePlayerDown = (Command command) -> {player.down = ((KeyBoardCommand)command).isPressed();};
		
		movementePlayerActions.put(KeyEvent.VK_RIGHT, movePlayerRight);
		movementePlayerActions.put(KeyEvent.VK_D, movePlayerRight);
		
		movementePlayerActions.put(KeyEvent.VK_LEFT, movePlayerLeft);
		movementePlayerActions.put(KeyEvent.VK_A, movePlayerLeft);
		
		movementePlayerActions.put(KeyEvent.VK_UP, movePlayerUp);
		movementePlayerActions.put(KeyEvent.VK_W, movePlayerUp);
		
		movementePlayerActions.put(KeyEvent.VK_DOWN, movePlayerDown);
		movementePlayerActions.put(KeyEvent.VK_S, movePlayerDown);
	}
	
	public void movePlayer(KeyBoardCommand command) {
		if(movementePlayerActions.containsKey(command.getKeyCode())) {
			movementePlayerActions.get(command.getKeyCode()).apply(command);
		}
	}
	
}
