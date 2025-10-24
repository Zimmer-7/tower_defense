package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;

public class WaveButton extends Button {
	
	private int cooldown = 5;
	private int time = height/cooldown;
	private int count = 60;
	public static boolean cockroachSpawn = true;
	public static boolean bloonSpawn = true;

	public WaveButton(double x, double y, int width, int height, BufferedImage sprite, String nome) {
		super(x, y, width, height, sprite, nome);
	
	}
	
	@Override
	public void tick() {
		if(cockroachSpawn || bloonSpawn) {
			if(count > 0) {
				count--;
			} else {
				if(cooldown > 0)
					cooldown--;
				count = 60;
			}
		}
		
		if(cooldown == 0 || pressedMap()) {
			Game.player.mana += cooldown;
			if(cockroachSpawn) {
				Game.spawner.ready = true;
				Game.spawner.cockroachs = true;
				Game.spawner.bloons = false;
				this.sprite = Game.spriteSheet.getSprite(32, 96, 16, 16);
			}
			if(bloonSpawn) {
				Game.spawner.ready = true;
				Game.spawner.cockroachs = false;
				Game.spawner.bloons = true;
				this.sprite = Game.spriteSheet.getSprite(48, 48, 16, 16);
			}
			
			cooldown = 5;
			cockroachSpawn = false;
			bloonSpawn = false;
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		int cooldownBar = height-cooldown*time;
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0, 128));
		g2.fillRect((int)x, (int)y + cooldownBar, width, height-cooldownBar);
	}

}
