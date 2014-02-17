package com.boingy.rain.entity.mob;

import com.boingy.rain.Game;
import com.boingy.rain.entity.projectile.Projectile;
import com.boingy.rain.entity.projectile.WizardProjectile;
import com.boingy.rain.graphics.AnimatedSprite;
import com.boingy.rain.graphics.Screen;
import com.boingy.rain.graphics.Sprite;
import com.boingy.rain.graphics.SpriteSheet;
import com.boingy.rain.input.Keyboard;
import com.boingy.rain.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down,
			32, 32, 5);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32,
			32, 5);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left,
			32, 32, 5);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right,
			32, 32, 5);

	private AnimatedSprite animSprite = null;

	Projectile p;
	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		this.sprite = Sprite.player_forward;
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = WizardProjectile.FIRE_RATE;
		animSprite = down;
	}

	public void update() {
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		if (fireRate > 0)
			fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		if (input.up) {
			animSprite = up;
			ya--;
		}
		if (input.down) {
			animSprite = down;
			ya++;
		}
		if (input.left) {
			animSprite = left;
			xa--;
		}
		if (input.right) {
			animSprite = right;
			xa++;
		}
		clear();
		updateShooting();

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
	}

	private void updateShooting() {

		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double adj, opp;
			adj = Mouse.getX() - Game.getWindowWidth() / 2;
			opp = Mouse.getY() - Game.getWidnowHeight() / 2;
			double direc = Math.atan2(opp, adj);
			shoot(x, y, direc);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, flip);

	}

	public void clear() {
		for (int i = 0; i < level.projectiles.size(); i++) {
			Projectile p = level.projectiles.get(i);
			if (p.isRemoved())
				level.projectiles.remove(i);
		}
	}

}
