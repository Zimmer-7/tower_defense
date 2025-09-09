package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Spawner extends Entity {
	
	private int timer = 0;
	private int spawnTime = 60;

	public Spawner(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	@Override
	public void tick() {
		timer ++;
		if(timer == spawnTime) {
			Enemy enemy = new Enemy(x, y, 16, 16, null);
			Game.entities.add(enemy);
			Game.enemies.add(enemy);
			timer = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect(this.getX() + this.maskx, this.getY() + this.masky, this.maskw, this.maskh);
	}
	
}
