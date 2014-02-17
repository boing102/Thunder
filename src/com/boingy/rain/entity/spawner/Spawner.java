package com.boingy.rain.entity.spawner;

import com.boingy.rain.entity.Entity;
import com.boingy.rain.entity.Particle.Particle;
import com.boingy.rain.level.Level;

public class Spawner extends Entity {

	public enum Type {
		PARTICLE, MOB;
	}

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		
		}
}

