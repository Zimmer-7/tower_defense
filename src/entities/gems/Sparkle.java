package entities.gems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import entities.Bullet;
import entities.Enemy;
import entities.Entity;
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
	
	@Override
	public void checkDamage() {
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy en = Game.enemies.get(i);
			
			if(Entity.isColliding(this, en) && !damaged.contains(en)) {
				
				damaged.add(en);
				en.hurt(damage);
				en.poisonDamage = poison;
				en.poisonTime = 3;
				Game.player.mana += manaGain;
				
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
		g.setColor(Color.getColor(color.get(0)));
		g.fillOval(this.getX(), this.getY(), width, height);
	}

}
