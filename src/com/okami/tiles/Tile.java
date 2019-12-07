package com.okami.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.okami.entities.GameObject;
import com.okami.graficos.Spritesheet;

public class Tile extends GameObject {
	public static final Integer FLOOR_TILE_COLOR = 0xFF000000;
	
	public static final Integer ROOF_LEFT_TILE_COLOR = 0xFF666666;
	public static final Integer ROOF_SHADOW_LEFT_TILE_COLOR = 0xFF777777;
	public static final Integer ROOF_RIGHT_TILE_COLOR = 0xFF555555;
	public static final Integer ROOF_TOP_TILE_COLOR = 0xFFFFFFFF;
	public static final Integer ROOF_BOTTOM_TILE_COLOR = 0xFF999999;
	
	public static final Integer CORNER_LEFT_TOP_TILE_COLOR = 0xFFDDDDDD;
	public static final Integer CORNER_LEFT_BOTTOM_TILE_COLOR = 0xFF111111;
	public static final Integer CORNER_RIGHT_TOP_TILE_COLOR = 0xFFBBBBBB;
	public static final Integer CORNER_RIGHT_BOTTOM_TILE_COLOR = 0xFF333333;
	
	public static final Integer PLAYER_COLOR = 0xFF0000FF;
	public static final Integer ENEMY_COLOR = 0xFF00FF00;
	public static final Integer BULLET_COLOR = 0xFFFFFF00;
	public static final Integer LIFE_COLOR = 0xFFFF0000;
	public static final Integer WALL_TILE_COLOR = 0xFFFFCBDB;
	
	
	public static int TILE_DIMENSION = 32;
	private static Spritesheet spritesheet = new Spritesheet("/TerrainShadow2.png");
	protected static BufferedImage FLOOR_SPRITE = spritesheet.getSprite(160, 352, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage WALL_SPRITE = spritesheet.getSprite(160, 160, TILE_DIMENSION, TILE_DIMENSION+5);
	protected static BufferedImage ROOF_SPRITE = spritesheet.getSprite(64, 66, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage ROOF_LEFT_SPRITE = spritesheet.getSprite(248, 320, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage ROOF_SHADOW_LEFT_SPRITE = spritesheet.getSprite(248, 320, TILE_DIMENSION+3, TILE_DIMENSION);
	protected static BufferedImage ROOF_RIGHT_SPRITE = spritesheet.getSprite(216, 320, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage ROOF_TOP_SPRITE = spritesheet.getSprite(248, 352, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage ROOF_BOTTOM_SPRITE = spritesheet.getSprite(216, 352, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage CORNER_LEFT_TOP_SPRITE = spritesheet.getSprite(288, 320, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage CORNER_LEFT_BOTTOM_SPRITE = spritesheet.getSprite(320, 320, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage CORNER_RIGHT_TOP_SPRITE = spritesheet.getSprite(320, 352, TILE_DIMENSION, TILE_DIMENSION);
	protected static BufferedImage CORNER_RIGHT_BOTTOM_SPRITE = spritesheet.getSprite(288, 352, TILE_DIMENSION, TILE_DIMENSION);
	
	public static Map<Integer, BufferedImage> mapSpritesTile = new HashMap<>();
	
	BufferedImage sprite;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.coordinateX = x;
		this.coordinateY = y;
		this.sprite = sprite;
	}
	
	public static BufferedImage getSpriteTile(Integer tile) {
		if(mapSpritesTile.isEmpty()) {
			initSpritesTileMap();
		}
		BufferedImage tileSprite = mapSpritesTile.get(tile);
		return tileSprite != null ? tileSprite : FLOOR_SPRITE ;
	}
	
	public static void initSpritesTileMap() {
		mapSpritesTile.put(FLOOR_TILE_COLOR, FLOOR_SPRITE);
		mapSpritesTile.put(ROOF_LEFT_TILE_COLOR, ROOF_LEFT_SPRITE);
		mapSpritesTile.put(ROOF_RIGHT_TILE_COLOR, ROOF_RIGHT_SPRITE);
		mapSpritesTile.put(ROOF_TOP_TILE_COLOR, ROOF_TOP_SPRITE);
		mapSpritesTile.put(ROOF_BOTTOM_TILE_COLOR, ROOF_BOTTOM_SPRITE);
		mapSpritesTile.put(CORNER_LEFT_TOP_TILE_COLOR, CORNER_LEFT_TOP_SPRITE);
		mapSpritesTile.put(CORNER_LEFT_BOTTOM_TILE_COLOR, CORNER_LEFT_BOTTOM_SPRITE);
		mapSpritesTile.put(CORNER_RIGHT_TOP_TILE_COLOR, CORNER_RIGHT_TOP_SPRITE);
		mapSpritesTile.put(CORNER_RIGHT_BOTTOM_TILE_COLOR, CORNER_RIGHT_BOTTOM_SPRITE);
		mapSpritesTile.put(WALL_TILE_COLOR, WALL_SPRITE);
		mapSpritesTile.put(PLAYER_COLOR, FLOOR_SPRITE);
		mapSpritesTile.put(ENEMY_COLOR, FLOOR_SPRITE);
		mapSpritesTile.put(BULLET_COLOR, FLOOR_SPRITE);
		mapSpritesTile.put(LIFE_COLOR, FLOOR_SPRITE);
		mapSpritesTile.put(ROOF_SHADOW_LEFT_TILE_COLOR, ROOF_SHADOW_LEFT_SPRITE);
		
	}
	
	public double getCoordinateX() {
		return coordinateX;
	}

	public Tile setX(int x) {
		this.coordinateX = x;
		return this;
	}

	public double getCoordinateY() {
		return coordinateY;
	}

	public Tile setY(int y) {
		this.coordinateY = y;
		return this;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(sprite, (int)this.getCoordinateX() - (int)offsetCoordinateX, (int)this.getCoordinateY() - (int)offsetCoordinateY, null);		
	}
	
	private List<Integer> getListFloorTileColor(){
		List<Integer> floorTileColors = new ArrayList<>();
		floorTileColors.add(FLOOR_TILE_COLOR);
		return floorTileColors; 
	}
	
	private List<Integer> getListWallTileColor(){
		List<Integer> wallTileColors = new ArrayList<>();
		wallTileColors.add(WALL_TILE_COLOR);
		return wallTileColors;
	}
	
	private List<Integer> getListRoofTileColor(){
		List<Integer> roofTileColors = new ArrayList<>();
		roofTileColors.add(ROOF_BOTTOM_TILE_COLOR);
		roofTileColors.add(ROOF_TOP_TILE_COLOR);
		roofTileColors.add(ROOF_LEFT_TILE_COLOR);
		roofTileColors.add(ROOF_RIGHT_TILE_COLOR);
		roofTileColors.add(ROOF_SHADOW_LEFT_TILE_COLOR);

		roofTileColors.add(CORNER_LEFT_BOTTOM_TILE_COLOR);
		roofTileColors.add(CORNER_LEFT_TOP_TILE_COLOR);
		roofTileColors.add(CORNER_RIGHT_BOTTOM_TILE_COLOR);
		roofTileColors.add(CORNER_RIGHT_TOP_TILE_COLOR);
		
		
		return roofTileColors;
	}
	
	private List<Integer> getListEntityTileColor(){
		List<Integer> entityTileColors = new ArrayList<>();
		entityTileColors.add(PLAYER_COLOR);
		entityTileColors.add(ENEMY_COLOR);
		entityTileColors.add(LIFE_COLOR);
		entityTileColors.add(BULLET_COLOR);
		return entityTileColors; 
	}
	
	public boolean isFloorTileColor(Integer tileColor) {
		return getListFloorTileColor().contains(tileColor);
	}
	
	public boolean isWallTileColor(Integer tileColor) {
		return getListWallTileColor().contains(tileColor);
	}
	
	public boolean isRoofTileColor(Integer tileColor) {
		return getListRoofTileColor().contains(tileColor);
	}
	
	public boolean isEntityColor(Integer tileColor) {
		return getListEntityTileColor().contains(tileColor);
	}
	
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub		
	}
	
}
