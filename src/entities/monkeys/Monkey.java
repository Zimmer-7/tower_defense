package entities.monkeys;

import java.awt.image.BufferedImage;

import entities.Bullet;
import entities.Tower;
import main.Game;

public class Monkey extends Tower {
	
	protected int level = 1;

	public Monkey(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	@Override
	protected void attack() {
		 
		angle = Math.atan2(yTarget - ((double)this.getY()+8), xTarget - ((double)this.getX()+8));
		dx = Math.cos(angle);
		dy = Math.sin(angle);
					
		Bullet bullet = new Dart((double)this.getX()+8, (double)this.getY()+8, 3, 3, null, dx, dy, pierce, damage, (int)range);
		Game.bullets.add(bullet);
			
	}

}
