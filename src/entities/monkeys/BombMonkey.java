package entities.monkeys;

import java.awt.image.BufferedImage;

import entities.Bullet;
import main.Game;

public class BombMonkey extends Monkey {

	public BombMonkey(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

	}
	
	@Override
	protected void attack() {
		 
		angle = Math.atan2(yTarget - ((double)this.getY()+8), xTarget - ((double)this.getX()+8));
		dx = Math.cos(angle);
		dy = Math.sin(angle);
					
		Bullet bullet = new Bomb((double)this.getX()+8, (double)this.getY()+8, 3, 3, null, dx, dy, damage, (int)range);
		Game.bullets.add(bullet);
			
	}

}
