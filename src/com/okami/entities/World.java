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
	
	public final int ROOF_LEFT_TILE = 0xFF777777;
	public final int ROOF_RIGHT_TILE = 0xFF555555;
	public final int ROOF_TOP_TILE = 0xFFFFFFFF;
	public final int ROOF_BOTTOM_TILE = 0xFF999999;
	
	public final int CORNER_LEFT_TOP_TILE = 0xFFDDDDDD;
	public final int CORNER_LEFT_BOTTOM_TILE = 0xFF111111;
	public final int CORNER_RIGHT_UP_TILE = 0xFFBBBBBB;
	public final int CORNER_RIGHT_BOTTOM_TILE = 0xFF333333;
	
	public final int PLAYER = 0xFF0000FF;
	public final int ENEMY = 0xFF00FF00;
	public final int BULLET = 0xFFFFFF00;
	public final int LIFE = 0xFFFF0000;
	public final int WALL = 0xFFFFCBDB;
	
	Map<Integer, Command> commands;
	List<Tile> chao;
	List<Tile> parede;
	List<Tile> teto;
	Game game;
	int width, height;
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			width = map.getWidth();
			height = map.getHeight();
			int[] pixelsMap = new int[map.getWidth()*map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixelsMap, 0, map.getWidth());
			chao = new ArrayList<Tile>();
			parede = new ArrayList<Tile>();
			teto = new ArrayList<Tile>();
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					
					int xxPixel = xx*Tile.TILE_DIMENSION;
					int yyPixel = yy*Tile.TILE_DIMENSION;
					
					int pixelAtual = pixelsMap[xx + (yy*map.getWidth())];
					
					if(pixelAtual == FLOOR) {
						chao.add(new FloorTile(xxPixel, yyPixel));
					}else if(pixelAtual == WALL) {
						parede.add(new WallTile(xxPixel, yyPixel));
					}else if(pixelAtual == ENEMY){
						chao.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new PigEnemy(xxPixel, yyPixel));
					}else if(pixelAtual == LIFE){
						chao.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new BigHeart(xxPixel, yyPixel));
					}else if(pixelAtual == BULLET){
						chao.add(new FloorTile(xxPixel, yyPixel));
						Game.entities.add(new CannonBall(xxPixel, yyPixel));
					}else if(pixelAtual == ROOF_TOP_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.ROOF_UP_TILE));
					}else if(pixelAtual == ROOF_BOTTOM_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.ROOF_BOTTOM_TILE));
					}else if(pixelAtual == ROOF_RIGHT_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.ROOF_RIGHT_TILE));
					}else if(pixelAtual == ROOF_LEFT_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.ROOF_LEFT_TILE));
					}else if(pixelAtual == CORNER_LEFT_BOTTOM_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.CORNER_LEFT_BOTTOM_TILE));
					}else if(pixelAtual == CORNER_LEFT_TOP_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.CORNER_LEFT_UP_TILE));
					}else if(pixelAtual == CORNER_RIGHT_BOTTOM_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.CORNER_RIGHT_BOTTOM_TILE));
					}else if(pixelAtual == CORNER_RIGHT_UP_TILE){
						teto.add(new RoofTile(xxPixel, yyPixel, Tile.CORNER_RIGHT_UP_TILE));
					}else if(pixelAtual == PLAYER){
						chao.add(new FloorTile(xxPixel, yyPixel));
					}else {
						teto.add(new RoofTile(xxPixel, yyPixel));
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
		chao.forEach(tile->tile.render(graphics));
		parede.forEach(tile->tile.render(graphics));
		teto.forEach(tile->tile.render(graphics));
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
	
}
