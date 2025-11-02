package entities;

import java.awt.image.BufferedImage;

import main.Game;

public class Cockroach extends Enemy {

	public Cockroach(double x, double y, int width, int height, BufferedImage sprite, int level) {
		super(x, y, width, height, sprite);
		
		this.maxIndex = 1;
		
		enemyRight = new BufferedImage[2];
		enemyLeft = new BufferedImage[2];
		
		enemyLeft[0] = Game.spriteSheet.getSprite(32, 48, 16, 16);
		enemyLeft[1] = Game.spriteSheet.getSprite(32, 64, 16, 16);
		enemyRight[0] = Game.spriteSheet.getSprite(48, 48, 16, 16);
		enemyRight[1] = Game.spriteSheet.getSprite(48, 64, 16, 16);
		enemyDamageLeft = Game.spriteSheet.getSprite(32, 80, 16, 16);
		enemyDamageRight = Game.spriteSheet.getSprite(48, 80, 16, 16);
		
		this.maxLife *= (level+4)/5.0;
		this.life = this.maxLife;
		this.speed *= (level+11)/12.0;
		
	}

}
