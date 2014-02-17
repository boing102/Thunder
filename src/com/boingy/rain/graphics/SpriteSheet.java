package com.boingy.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE, WIDTH, HEIGHT;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/spawnlevels.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/projectiles/wizard.png", 48);
	
	public static SpriteSheet player = new SpriteSheet("/player_sheet.png", 160, 128);
	public static SpriteSheet player_down = new SpriteSheet(player, 0 , 0, 5, 1, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 0 , 2, 5, 1, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 0 , 3, 5, 1, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 0 , 1, 5, 1, 32);
	
	public static SpriteSheet dummy = new SpriteSheet("/king_cherno.png", 128, 96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0 , 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1 , 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2 , 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3 , 0, 1, 3, 32);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) {
			SIZE = width;			
		}else SIZE = -1;
		HEIGHT = h;
		WIDTH = w;
		pixels = new int[w * h];
		
		for(int yi = 0; yi < h; yi++) {
			int yp = yy + yi; 
			for(int xi = 0; xi < w; xi++) {
				int xp = xx + xi;
				pixels[xi + yi * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
			
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int spritePixels[] = new int[spriteSize * spriteSize];
				for(int yi = 0; yi < spriteSize; yi++) {
					for(int xi = 0; xi < spriteSize; xi++) {
						spritePixels[xi + yi * spriteSize] = pixels[(xi + xa * spriteSize) + (yi + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
 	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		this.WIDTH = size;
		this.HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
 	
 	public SpriteSheet(String path, int width, int height){
		this.path = path;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SIZE = -1;		
		pixels = new int[HEIGHT * WIDTH];
		load();
	}
 	
 	public Sprite[] getSprites() {
 		return sprites;
 	} 
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
