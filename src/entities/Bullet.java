package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.Game;

public class Bullet extends Entity {
	
	private double dx;
	private double dy;
	private double speed = 4;
	private int time;
	public int life = 1;
	public double damage;
	public List<Enemy> damaged;
	
	public boolean dead = false;

	public Bullet(double x, double y, int width, int height, BufferedImage sprite, double dx, double dy, double damage, int time) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		this.damage = damage;
		damaged = new ArrayList<>();
		this.time = time;
	}
	
	@Override
	public void tick() {
		x += dx*speed;
		y += dy*speed;
		time --;
		
		if(time == 0) {
			Game.bullets.remove(this);
			damaged.clear();
		}
		
		checkDamage();
		
	}
	
	public void checkDamage() {
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy en = Game.enemies.get(i);
			
			if(Entity.isColliding(this, en) && !damaged.contains(en)) {
				
				damaged.add(en);
				en.hurt(damage);
				if(life == 1) {
					dead = true;
					damaged.clear();
					return;
				} else {
					life--;
				}
			
			}
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(this.getX(), this.getY(), width, height);
	}
	
}
