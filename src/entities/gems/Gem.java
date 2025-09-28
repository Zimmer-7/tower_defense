package entities.gems;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import entities.Bullet;
import entities.Tower;
import main.Game;

public class Gem extends Tower{
	
	public int grade = 1;
	public List<String> color;

	public Gem(double x, double y, int width, int height, BufferedImage sprite, String color) {
		super(x, y, width, height, sprite);
		this.color = new ArrayList<>();
		this.color.add(color);
		this.damage = 2;
	}
	
	@Override
	protected void attack() {
		 
		angle = Math.atan2(yTarget  - ((double)this.getY()+8), xTarget - ((double)this.getX()+8));
		dx = Math.cos(angle);
		dy = Math.sin(angle);
					
		Bullet bullet = new Sparkle((double)this.getX()+8, (double)this.getY()+8, 3, 3, null, dx, dy, color, damage, (int)range, grade);
		Game.bullets.add(bullet);
			
	}

}
