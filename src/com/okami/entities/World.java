package com.okami.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.okami.actions.CameraMovementAction;
import com.okami.actions.WorldMapAction;
import com.okami.factories.TileFactory;
import com.okami.tiles.Tile;
import com.okami.util.Observer;

public class World extends GameObject {
	
	List<Tile> tiles;
	Map<String, Tile> tilesCoordinateMap;
	int width, height;
	BufferedImage map;
	int[] pixelsMap;
	private List<Observer> observers;
	
	public World() {
		observers = new ArrayList<>();
	}
	
	public void buiild(String path) {
		try {
			loadImageMap(path);
			createInstanceOfTiles(map, pixelsMap);
			orderTileMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadImageMap(String path) throws IOException {
		map = ImageIO.read(getClass().getResource(path));
		width = map.getWidth();
		height = map.getHeight();
		pixelsMap = new int[map.getWidth()*map.getHeight()];
		map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixelsMap, 0, map.getWidth());
	}

	private void createInstanceOfTiles(BufferedImage map, int[] pixelsMap) {
		tiles = new ArrayList<Tile>();
		tilesCoordinateMap = new HashMap<>();
		for(int yy = 0; yy < map.getHeight(); yy++) {
			for(int xx = 0; xx < map.getWidth(); xx++) {
				
				int xxPixel = xx*Tile.TILE_DIMENSION;
				int yyPixel = yy*Tile.TILE_DIMENSION;
				
				int pixelAtual = pixelsMap[xx + (yy*map.getWidth())];
				Tile tile = TileFactory.create(pixelAtual, xxPixel, yyPixel);
				tiles.add(tile);
				tilesCoordinateMap.put(xx+""+yy, tile);
				if(tile.isEntityColor(pixelAtual)) {
					notifyObserver(WorldMapAction.builder().color(pixelAtual).xCoordinate(xxPixel).yCoordinate(yyPixel));
				}
			}
		}
	}

	private void orderTileMap() {
		tiles.sort((Tile t1, Tile t2) -> t1.layer - t2.layer);
	}

	@Override
	public void render(Graphics graphics) {
		tiles.forEach(tile->tile.render(graphics));
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public int getWidth() {
		return width*Tile.TILE_DIMENSION;
	}

	public int getHeight() {
		return height*Tile.TILE_DIMENSION;
	}
	
	public void notifyObserver(WorldMapAction action) {
		for (Observer observer : observers) {
			observer.apply(action);
		}
	}
	
	public void registerObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	protected void updateOffset(CameraMovementAction action) {
		tiles.forEach((tile)->tile.updateOffset(action));
	}
	
	public Tile getTile(int xCoordinate, int yCoordinate) {
		return tilesCoordinateMap.get(xCoordinate+""+yCoordinate);
	}
}
