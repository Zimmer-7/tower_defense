package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.World;

public class Bullet extends Entity {
	
	private double dx;
	private double dy;
	private double speed = 5;
	private int time = 100;

	public Bullet(double x, double y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void tick() {
		x += dx*speed;
		y += dy*speed;
		time --;
		
		if(time == 0) 
			Game.bullets.remove(this);
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(this.getX(), this.getY(), width, height);
	}
	
}
