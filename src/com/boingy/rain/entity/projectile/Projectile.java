package com.boingy.rain.entity.projectile;

import java.util.Random;

import com.boingy.rain.entity.Entity;
import com.boingy.rain.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double nx, ny;
	protected double distance;
	
	protected final  Random random = new Random(); 
	protected double speed, range, damage;
	
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return this.sprite.SIZE;
	}
	
	protected void move() {}
}
