package entities.monkeys;

import java.awt.image.BufferedImage;

import entities.Bullet;

public class Dart extends Bullet {

	public Dart(double x, double y, int width, int height, BufferedImage sprite, 
			double dx, double dy, int life, double damage, int time) {
		
		super(x, y, width, height, sprite, dx, dy, damage, time);
		this.life = life;
		
	}

}
