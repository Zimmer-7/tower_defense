package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR_GRASS_1 = Game.spriteSheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL_DARK = Game.spriteSheet.getSprite(16, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x;
	private int y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
	
}
