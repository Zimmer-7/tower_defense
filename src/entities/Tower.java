package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.monkeys.Dart;
import main.Game;

public class Tower extends Entity {
	
	private int count = 60;
	
	public int xTarget;
	public int yTarget;
	private boolean atacking = false;
	protected double angle;
	protected double dx;
	protected double dy;
	
	protected double range = 25;
	protected double fireRate = 1;
	protected int pierce = 1;
	protected double damage = 1;
	
	private Enemy target = null;

	public Tower(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.depth = 1;
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
		
			if(count >= 60.0/fireRate) {
				attack();
				
				atacking = false;
				count = 0;
			}
		}
		
	}
	
	protected void attack() {
		
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
