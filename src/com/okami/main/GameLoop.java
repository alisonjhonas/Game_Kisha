package com.okami.main;

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
import com.okami.util.Command;
import com.okami.util.KeyBoardCommand;
import com.okami.util.Observer;

public class GameLoop extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public final double amountOfTicks = 60.0;
    public final double nanoSeconds = 1000000000 / amountOfTicks;
    public boolean isRunning = false;
    private Thread thread;
    public static JFrame frame;
    private final int WIDTH = 220;
    private final int HEIGHT = 240;
    private final int SCALE = 2;
    private List<Observer> observers;
    
    private BufferedImage image;
    
    private Game game;
    public GameLoop(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        addKeyListener(this);
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        game = Game.createGame();
        observers = new ArrayList<>();
        registerObserver((Command command) -> game.movePlayer((KeyBoardCommand)command));
        
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
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
    public synchronized void stop(){
    	
    	isRunning = false;
    	
        try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Adicionar tratamento de exceção.
			e.printStackTrace();
		}
    }
    
    public static void main(String args[]) {
        GameLoop game = new GameLoop();
        game.start();
    }

    public void tick() {
    	game.tick();
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
    	
    	game.render(graphics);
    	
    	graphics.dispose();
    	graphics = bs.getDrawGraphics();
    	graphics.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
    	bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        long now = 0l;
        while(isRunning){
            now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }
            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS: "+ frames);
                frames = 0;
                timer+=1000;
            }
        }
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
}
