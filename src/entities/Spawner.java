package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import ui.WaveButton;

public class Spawner extends Entity {
	
	private int timer = 0;
	private int spawnTime = 60;
	private int enemyQttd = 5;
	private int enemyCount = enemyQttd;
	private Enemy enemy;
	public boolean ready = false;
	public boolean bloons = false;
	public boolean cockroachs = false;
	public int level = 1;

	public Spawner(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	@Override
	public void tick() {
		if(ready) {
			timer ++;
			if(timer == spawnTime && enemyCount > 0) {
				if(level%10 == 0) {
					enemy = new Boss(x, y, 16, 16, null);
					Game.entities.add(enemy);
					Game.enemies.add(enemy);
					timer = 0;
					enemyCount = 0;
					return;
				}
				if(cockroachs)
					enemy = new Cockroach(x, y, 16, 16, null, level);
				
				if(bloons)
					enemy = new Bloon(x, y, 16, 16, null, level, 0);
				
				Game.entities.add(enemy);
				Game.enemies.add(enemy);
				timer = 0;
				enemyCount--;
			}
		}
		
		if(enemyCount == 0) {
			ready = false;
			level ++;
			if(bloons) {
				WaveButton.cockroachSpawn = true;
				enemyCount = enemyQttd;
			}
			
			if(cockroachs) {
				WaveButton.bloonSpawn = true;
				enemyCount = enemyQttd*2;
			}
			
			enemyQttd++;
			spawnTime--;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawRect(this.getX() + this.maskx, this.getY() + this.masky, this.maskw, this.maskh);
	}
	
}
