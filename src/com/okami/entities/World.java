package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.okami.util.Command;

public class World extends GameObject {
	
	public final int FLOOR = 0xFF000000;
	public final int WALL = 0xFFFFFFFF;
	public final int PLAYER = 0xFF0000FF;
	public final int ENEMY = 0xFF00FF00;
	public final int BULLET = 0xFFFFFF00;
	public final int LIFE = 0xFFFF0000;
	
	Map<Integer, Command> commands;
	List<Tile> tiles;
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixelsMap = new int[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixelsMap, 0, map.getWidth());
			tiles = new ArrayList<Tile>();
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					int xxPixel = xx*32;
					int yyPixel = yy*32;
					
					int pixelAtual = pixelsMap[xx + (yy*map.getWidth())];
					if(pixelAtual == FLOOR) {
						tiles.add(new FloorTile(xxPixel, yyPixel));
					}else if(pixelAtual == WALL) {
						tiles.add(new WallTile(xxPixel, yyPixel));
					}else if(pixelAtual == ENEMY){
						tiles.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new PigEnemy(xxPixel, yyPixel));
					}else if(pixelAtual == LIFE){
						tiles.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new BigHeart(xxPixel, yyPixel));
					}else if(pixelAtual == BULLET){
						tiles.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new CannonBall(xxPixel, yyPixel));
					}else {
						tiles.add(new FloorTile(xxPixel, yyPixel));
					}
					
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics graphics) {
		tiles.forEach(tile->tile.render(graphics));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
