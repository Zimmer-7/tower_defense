package entities.gems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import entities.Bullet;
import entities.Enemy;
import main.Game;

public class Sparkle extends Bullet {
	
	private List<String> color;
	private int poison;
	private int manaGain;

	public Sparkle(double x, double y, int width, int height, BufferedImage sprite, 
			double dx, double dy, List<String> color, double damage, int time, int level) {
		
		super(x, y, width, height, sprite, dx, dy, damage, time);
		this.color = color;
		
		if(color.contains("green") && color.contains("orange")) {	
			this.poison = 1*level/2;
			this.manaGain = 1*level/2;
		} else if(color.contains("green")) {
			this.poison = 1*level;
		} else if(color.contains("orange")) {
			this.manaGain = 1*level;
		}
		
	}
	
	public void hit(Enemy e) {
		super.hit(e);
		
		Game.player.mana += manaGain;
		e.poisonTime = 3;
		e.poisonDamage = poison;
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.getColor(color.get(0)));
		g.fillOval(this.getX(), this.getY(), width, height);
	}

}
