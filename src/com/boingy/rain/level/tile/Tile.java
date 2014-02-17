package com.boingy.rain.level.tile;

import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.level.tile.SpawnLevel.SpawnFloorTile;
import com.boingy.rain.level.tile.SpawnLevel.SpawnGrassTile;
import com.boingy.rain.level.tile.SpawnLevel.SpawnHedgeTile;
import com.boingy.rain.level.tile.SpawnLevel.SpawnWallTile;
import com.boingy.rain.level.tile.SpawnLevel.SpawnWaterTile;

public class Tile {
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_road = new SpawnFloorTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public static final int col_spawn_grass = 0xff00ff00;
	public static final int col_spawn_water = 0xff0000ff;
	public static final int col_spawn_floor = 0xff894000;
	public static final int col_spawn_road = 0xff000000;
	public static final int col_spawn_hedge = 0xff268e20;
	public static final int col_spawn_wall = 0xff606060;
	
	
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
