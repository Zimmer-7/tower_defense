package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.*;
import grafics.SpriteSheet;
import main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH;
	public static int HEIGHT;
	
	public World(String path, int level) {
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			int pos;
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					pos = xx + (yy*WIDTH);
					//enche de Floor
					tiles[pos] = new Wall(xx*16, yy*16, Tile.TILE_FLOOR_GRASS_1);
					if(pixels[pos] == 0xFFFFFFFF) {
						//parede
						tiles[pos] = new Floor(xx*16, yy*16, Tile.TILE_WALL_DARK);
					}
					if(pixels[pos] == 0xFFFF0000) {
						//spawn
						tiles[pos] = new Floor(xx*16, yy*16, Tile.TILE_WALL_DARK);
						Game.spawner.setX(xx*16.0);
						Game.spawner.setY(yy*16.0);
					}
					if(pixels[pos] == 0xFF0000FF) {
						//base
						tiles[pos] = new Floor(xx*16, yy*16, Tile.TILE_WALL_DARK);
						Game.player.setX(xx*16.0);
						Game.player.setY(yy*16.0);
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isFree(int x, int y) {
		int x1 = x/16;
		int y1 = y/16;
		int x2 = (x+16-1) / 16;
		int y2 = (y+16-1) / 16;
		
		return !(tiles[x1 + (y1*WIDTH)] instanceof Wall ||
				tiles[x1 + (y2*WIDTH)] instanceof Wall ||
				tiles[x2 + (y1*WIDTH)] instanceof Wall ||
				tiles[x2 + (y2*WIDTH)] instanceof Wall);
	}
	
	public static void startLevel(int level) {
		Game.entities.clear();
		Game.enemies.clear();
		Game.towers.clear();
		Game.emptyTowers.clear();
		Game.bullets.clear();
		Game.entities = new ArrayList<>();
		Game.enemies = new ArrayList<>();
		Game.towers = new ArrayList<>();
		Game.emptyTowers = new ArrayList<>();
		Game.bullets = new ArrayList<>();
		Game.spriteSheet = new SpriteSheet("/recursos.png");	
		Game.player = new Player(0, 0, 16, 16, Game.spriteSheet.getSprite(32, 0, 16, 16));
		Game.spawner = new Spawner(0, 0, 16, 16, null);
		Game.world = new World("/mapa"+level+".png", level);
		Game.entities.add(Game.player);
		Game.entities.add(Game.spawner);
		Game.gameState = "Normal";
	}
	
	public static void renderMiniMap(int[] miniMap) {
		/*for(int i = 0; i < miniMap.length; i++) {
			miniMap[i] = 0;
		}*/
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				if(tiles[xx + yy*WIDTH] instanceof Wall)
					miniMap[xx + yy*WIDTH] = 0xffffff;
			}
		}
		
		int x;
		int y;
		Entity en;
		
		for(int i = 0; i < Game.entities.size(); i++) {
			en = Game.entities.get(i);
			x = en.getX()/16;
			y = Game.entities.get(i).getY()/16;
			
			//Logica do minimapa
		}
	}
	
	public static void generateParticles(int amount, int x, int y) {
		for(int i = 0; i < amount; i++) 
			Game.entities.add(new Particle(x, y, 1, 1, null));
	}

	public void render(Graphics g) {
		
		int xfinal = (Game.WIDTH >> 4);
		int yfinal = (Game.HEIGHT >> 4);
		
		Tile tile;
		for(int xx = 0; xx <= xfinal; xx++) {
			for(int yy = 0; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				
				tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
