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

import com.okami.entities.Entity;
import com.okami.entities.Player;
import com.okami.graficos.Spritesheet;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public final double amountOfTicks = 60.0;
    public final double nanoSeconds = 1000000000 / amountOfTicks;
    public boolean isRunning = false;
    private Thread thread;
    public static JFrame frame;
    private final int WIDTH = 220;
    private final int HEIGHT = 240;
    private final int SCALE = 2;
    
    private BufferedImage image;
    public Spritesheet spritesheet;
    public List<Entity> entities;
    public static Player player;
    public Game(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        addKeyListener(this);
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        spritesheet = new Spritesheet("/Idle.png");
        entities = new ArrayList<Entity>();
        player = new Player(0, 0, 78, 58, spritesheet.getSprite(0, 0, 78, 58));
        entities.add(player);
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
        Game game = new Game();
        game.start();
    }

    public void tick() {
    	for (Entity entity : entities) {
    		entity.tick();
		}
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
    	
    	for(Entity entity : entities) {
    		entity.render(graphics);
    	}
    	
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
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
