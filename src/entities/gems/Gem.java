package entities.gems;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import entities.Bullet;
import entities.Tower;
import main.Game;

public class Gem extends Tower{
	
	public int grade;
	private List<String> color;
	private int pos = 0;

	public Gem(double x, double y, int width, int height, BufferedImage sprite, String color) {
		super(x, y, width, height, sprite);
		this.color = new ArrayList<>();
		this.addColor(color);
		this.damage = 2;
		this.range = 15;
		this.fireRate = 0.9;
	}
	
	public void updateGem(int grade) {
		this.grade = grade;
		this.damage *= grade;
		this.range += range*grade/2;
		this.fireRate += fireRate*grade/10;
		if(color.contains("orange"))
			pos = 32;
		if(color.size() == 2)
			pos += 16;
		this.sprite = Game.spriteSheet.getSprite(32+16*grade+pos, 32, 16, 16);
	}
	
	public void addColor(String newColor) {
		if(!color.contains(newColor))
			color.add(newColor);
	}
	
	public String getColor() {
		return color.get(0);
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
