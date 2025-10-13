package entities.monkeys;

import java.awt.image.BufferedImage;

import entities.Bullet;
import entities.Enemy;
import entities.Entity;
import main.Game;
import world.World;

public class Bomb extends Bullet {

	public Bomb(double x, double y, int width, int height, BufferedImage sprite, 
			double dx, double dy, double damage, int time) {
		super(x, y, width, height, sprite, dx, dy, damage, time);
		
	}
	
	@Override
	public void checkDamage() {
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy en = Game.enemies.get(i);
			
			if(Entity.isColliding(this, en) && !damaged.contains(en)) {
				
				damaged.add(en);
				en.hurt(damage);
				
				if(life == 1) {
					double ex = x;
					double ey = y;
					
					World.generateParticles(50, (int)x, (int)y);
					explode(ex, ey);
					dead = true;
					
					damaged.clear();
					return;
				} else {
					life--;
				}
			
			}
		}
		
	}
	
	public void explode(double x, double y){
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy en = Game.enemies.get(i);
			double dx = en.getX() - x;
	        double dy = en.getY() - y;
	        double dist = dx*dx + dy*dy;
	        if (dist <= 1500 && !damaged.contains(en)) {
	            en.hurt(damage);
	            damaged.add(en);
	        }
		}
	}
	
}
