package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import grafics.SpriteSheet;
import main.AdvancedSound;
import main.Game;

public class Player extends Entity {
	
	public int maxLife = 10;
	public int life = maxLife;
	
	public double maxMana = 100;
	public double mana = maxMana;
	
	private int timer = 0;
	private int damageFrames = 0;
	private int maxFrames = 6;
	
	public boolean damaged = false;
	
	private BufferedImage playerStop;
	private BufferedImage playerDamage;
	
	public Player(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		playerStop = Game.spriteSheet.getSprite(64, 0, 16, 16);
		
		depth = 1;
	}
	
	@Override
	public void tick() {
		if(mana < 0)
			mana = 0;
		
		if(mana > maxMana)
			mana = maxMana;
		
		timer++;
		if(timer == 60) {
			if(mana < maxMana)
				mana++;
			timer = 0;
		}

		if(damaged) {
			damageFrames ++;
			if(damageFrames == maxFrames) {
				damageFrames = 0;
				damaged = false;
			}
		}
		
		if(life <= 0) {
			Game.gameState = "Game Over";
		}
		
	}
	
	@Override
	public void render(Graphics g){
		
		if(damaged) {
			g.drawImage(playerDamage, this.getX(), this.getY(), null);
		} else {
			g.drawImage(playerStop, this.getX(), this.getY(), null);
		}
		//g.setColor(Color.yellow);
		//g.drawRect(this.getX() + this.maskx, this.getY() + this.masky, this.maskw, this.maskh);
	}
	
}
