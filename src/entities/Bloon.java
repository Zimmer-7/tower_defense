package entities;

import java.awt.image.BufferedImage;

import main.Game;

public class Bloon extends Enemy {

	public Bloon(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		this.maxLife = 1;
		this.life = maxLife;
		
		this.maxIndex = 0;
		
		enemyRight = new BufferedImage[1];
		enemyLeft = new BufferedImage[1];
		
		enemyLeft[0] = Game.spriteSheet.getSprite(32, 96, 16, 16);
		enemyRight[0] = Game.spriteSheet.getSprite(32, 96, 16, 16);
		
		enemyDamageLeft = Game.spriteSheet.getSprite(48, 128, 16, 16);
		enemyDamageRight = Game.spriteSheet.getSprite(48, 128, 16, 16);
		
	}

}
