package com.okami.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.okami.util.KeyBoardCommand;

public class Game {
	
	
	List<Entity> entities;
	Player player;
	
	public Game() {
		entities = new ArrayList<Entity>();
	}
	
	public static Game createGame() {
		Game game = new Game();
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
	
	public void movePlayer(KeyBoardCommand command) {
		if(command.getKeyCode() == KeyEvent.VK_RIGHT || command.getKeyCode() == KeyEvent.VK_D) {
			player.right = command.isPressed();
		}else if(command.getKeyCode() == KeyEvent.VK_LEFT || command.getKeyCode() == KeyEvent.VK_A) {
			player.left = command.isPressed();
		}
		
		if(command.getKeyCode() == KeyEvent.VK_UP || command.getKeyCode() == KeyEvent.VK_W) {
			player.up = command.isPressed();
		}else if(command.getKeyCode() == KeyEvent.VK_DOWN || command.getKeyCode() == KeyEvent.VK_S) {
			player.down = command.isPressed();
		}
	}
	
}
