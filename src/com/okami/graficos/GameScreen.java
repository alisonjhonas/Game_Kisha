package com.okami.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.okami.entities.Game;
import com.okami.entities.GameObject;
import com.okami.util.Command;
import com.okami.util.KeyBoardCommand;
import com.okami.util.Observer;

public class GameScreen extends Canvas implements GameObject, KeyListener {
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
    private final int WIDTH = 220;
    private final int HEIGHT = 240;
    private final int SCALE = 2;
    private BufferedImage image;
    private Game game;
    private List<Observer> observers;
	
    public GameScreen() {
    	addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
    	initFrame();
    	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    	observers = new ArrayList<>();
        registerObserver((Command command) -> game.movePlayer((KeyBoardCommand)command));
    }
    
    public void render() {
    	BufferStrategy bs = this.getBufferStrategy();
    	if(bs == null) {
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics graphics = image.getGraphics();
    	graphics.setColor(Color.GREEN);
    	graphics.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	render(graphics);
    	
    	graphics.dispose();
    	graphics = bs.getDrawGraphics();
    	graphics.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
    	bs.show();
    }
    
	@Override
	public void render(Graphics graphics) {		
    	game.render(graphics);	
	}

	@Override
	public void tick() {
		
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		notifyObserver(KeyBoardCommand.createCommand().keyCode(e.getKeyCode()).pressed(true));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		notifyObserver(KeyBoardCommand.createCommand().keyCode(e.getKeyCode()).pressed(false));
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyObserver(KeyBoardCommand command) {
		for (Observer observer : observers) {
			observer.apply(command);
		}
	}
	
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	public void initFrame() {
    	frame = new JFrame();
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
