package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Boss extends Enemy {
	
	protected BufferedImage enemyDown;

	public Boss(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.maxIndex = 0;
		
		enemyRight = new BufferedImage[1];
		enemyLeft = new BufferedImage[1];
		
		enemyLeft[0] = Game.spriteSheet.getSprite(64, 80, 32, 16);
		enemyRight[0] = Game.spriteSheet.getSprite(64, 80, 32, 16);
		enemyDown = Game.spriteSheet.getSprite(64, 96, 16, 32);
		
		enemyDamageLeft = Game.spriteSheet.getSprite(64, 80, 32, 16);
		enemyDamageRight = Game.spriteSheet.getSprite(64, 96, 16, 32);
		
		this.maxLife *= 100;
		this.life = this.maxLife;
		this.speed = 0.5;
		
	}
	
	@Override
	protected void die() {
		super.die();
		
		new java.util.Timer().schedule(
	            new java.util.TimerTask() {
	                @Override
	                public void run() {

	                	Cockroach c1 = new Cockroach(x, y, 16, 16, null, 10);
	                	Cockroach c2;
	                	if(down) {
	                		c2 = new Cockroach(x, y+16, 16, 16, null, 10);
	                	}else {
	                		c2 = new Cockroach(x+16, y, 16, 16, null, 10);
	                	}
	                	
	            		Game.entities.add(c1);
	            		Game.enemies.add(c1);
	            		Game.entities.add(c2);
	            		Game.enemies.add(c2);
	                }
	            },
	            50
	        );
		
	}
	
	@Override
	public void render(Graphics g) {
		if(down) {
			g.drawImage(enemyDown, this.getX(), this.getY(), null);
			left = false;
			right = false;
		}
		
		super.render(g);
	}

}
