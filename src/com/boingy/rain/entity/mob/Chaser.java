package com.boingy.rain.entity.mob;

import com.boingy.rain.entity.mob.Mob.Direction;
import com.boingy.rain.graphics.AnimatedSprite;
import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.graphics.SpriteSheet;

public class Chaser extends Mob	{
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private int xa = 0, ya = 0;

	
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	private void move() {
		xa = 0;
		ya = 0;
		
		Player player = level.getClientPlayer();
		if (x < player.getX()) xa++;
		if (x > player.getX()) xa--;
		if (y < player.getY()) ya++;
		if (y > player.getY()) ya--;		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;	
	}
	
	public void update() {
		move();
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		}
		if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		}
		if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
		
	}

	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
	}
	
	
}
