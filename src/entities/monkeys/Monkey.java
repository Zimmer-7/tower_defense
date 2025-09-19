package entities.monkeys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entities.Bullet;
import entities.Enemy;
import entities.Entity;
import main.Game;
import main.Sound;

public class Monkey extends Entity {
	
	public int xTarget;
	public int yTarget;
	private boolean atacking = false;
	private double angle;
	private double dx;
	private double dy;
	private int count = 60;
	
	protected double range = 30;
	protected double fireRate = 1;
	protected int pierce = 1;
	protected double damage = 1;
	
	private Enemy target = null;

	public Monkey(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	@Override
	public void tick() {
		target = findEnemy();
		
		if(target != null) {
			atacking = true;
			xTarget = target.getX()+8;
			yTarget = target.getY()+8;
		} 
		
		if(atacking) {
			count ++;
			
			if(count >= 60/fireRate) {
				angle = Math.atan2(yTarget  - ((double)this.getY()+8), xTarget - ((double)this.getX()+8));
				dx = Math.cos(angle);
				dy = Math.sin(angle);
						
				Bullet bullet = new Bullet((double)this.getX()+8, (double)this.getY()+8, 3, 3, null, dx, dy, pierce, damage);
				Game.bullets.add(bullet);
				atacking = false;
				count = 0;
			}
		}
	}
	
	private Enemy findEnemy() {
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			
			if(calcDistance(this.getX(), e.getX(), this.getY(), e.getY()) < range) 
				return e;
			
		}
		return null;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

}
