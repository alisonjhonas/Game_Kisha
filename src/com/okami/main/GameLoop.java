package com.okami.main;

import com.okami.entities.GameObject;
import com.okami.graficos.GameScreen;
import com.okami.util.GameScreenStrategy;
import com.okami.util.GameStrategy;

public class GameLoop implements Runnable {
	
	
	public final double amountOfTicks = 60.0;
    public final double nanoSeconds = 1000000000 / amountOfTicks;
    public boolean isRunning = false;
    private Thread thread;
    
    
    private GameObject game;
    private GameScreen gameScreen;
    public GameLoop(){
        game = GameStrategy.createGame();
        gameScreen = GameScreenStrategy.createGameScreen(game);
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
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
    	gameScreen.render();
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
}
