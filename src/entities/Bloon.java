package entities;

import java.awt.image.BufferedImage;

import main.Game;

public class Bloon extends Enemy {
	private int grade = 1;

	public Bloon(double x, double y, int width, int height, BufferedImage sprite, int level, int grade) {
		super(x, y, width, height, sprite);
		this.maxLife = 1;
		this.life = maxLife;
		
		this.maxIndex = 0;
		
		enemyRight = new BufferedImage[1];
		enemyLeft = new BufferedImage[1];
		
		if(grade == 0) {
			if(level > 2)
				this.grade++;
			if(level > 4)
				this.grade++;
			if(level > 6)
				this.grade++;
			if(level > 8)
				this.grade++;
		}else {
			this.grade = grade;
		}
		
		if(this.grade == 2) 
			this.speed = 0.6;
		if(this.grade == 3)
			this.speed = 0.8;
		if(this.grade == 4)
			this.speed = 1.0;
		if(this.grade == 5)
			this.speed = 1.3;
		
		int posY = (this.grade*16+16)/32;
		int posX = (this.grade*16)%32;
		
		enemyLeft[0] = Game.spriteSheet.getSprite(48 - posX, 80 + (16*posY), 16, 16);
		enemyRight[0] = Game.spriteSheet.getSprite(48 - posX, 80 + (16*posY), 16, 16);
		
		enemyDamageLeft = Game.spriteSheet.getSprite(48, 128, 16, 16);
		enemyDamageRight = Game.spriteSheet.getSprite(48, 128, 16, 16);
		
	}
	
	@Override
	protected void die() {
		super.die();
		if(grade == 1)
			return;
		
		new java.util.Timer().schedule(
	            new java.util.TimerTask() {
	                @Override
	                public void run() {

	                	Bloon b = new Bloon(x, y, 16, 16, null, 0, grade-1);
	            		
	            		Game.entities.add(b);
	            		Game.enemies.add(b);
	                }
	            },
	            50
	        );
		
		
		
	}

}
