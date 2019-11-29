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
					
					int pixelAtual = pixelsMap[xx + (yy*map.getWidth())];
					if(pixelAtual == FLOOR) {
						tiles.add(new FloorTile(xx*32, yy*32));
					}else if(pixelAtual == WALL) {
						tiles.add(new WallTile(xx*32, yy*32));
					}else if(pixelAtual == PLAYER){
						tiles.add(new FloorTile(xx*32, yy*32));
					}else {
						tiles.add(new FloorTile(xx*32, yy*32));
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
