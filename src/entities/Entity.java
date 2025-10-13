package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import main.Game;
import world.Node;
import world.Vector2i;
import world.Wall;
import world.World;

public class Entity {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	protected int maskx;
	protected int masky;
	protected int maskh;
	protected int maskw;
	
	public int depth;
	
	protected BufferedImage sprite;
	
	protected List<Node> path;
	
	public Entity(double x, double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.maskw = width;
		this.maskh = height;
		
		this.depth = 0;
	}
	
	public void setMask(int maskx, int masky, int maskw, int maskh) {
		this.maskx = maskx;
		this.masky = masky;
		this.maskw = maskw;
		this.maskh = maskh;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public static Comparator <Entity> nodeSorter = new Comparator <Entity>() {
		
		@Override
		public int compare(Entity n1, Entity n2) {
			if(n2.depth < n1.depth) 
				return + 1;
			
			if(n2.depth > n1.depth) 
				return - 1;
			
			return 0;
		}
	};
	
	public double calcDistance(int x1, int x2, int y1, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public static boolean isColliding(Entity en1, Entity en2) {
		Rectangle en1Mask = new Rectangle(en1.getX() + en1.maskx, en1.getY() + en1.masky, en1.maskw, en1.maskh);
		Rectangle en2Mask = new Rectangle(en2.getX() + en2.maskx, en2.getY() + en2.masky, en2.maskw, en2.maskh);
		
		return en2Mask.intersects(en1Mask);
	}
	
	protected boolean isCollidingWall(int xx, int yy) {
		Rectangle en = new Rectangle(xx + maskx, yy + masky, maskw, maskh);
		
		int x1 = (xx+maskx)/16;
		int y1 = (yy+masky)/16;
		int x2 = (xx+maskx+maskw-1) / 16;
		int y2 = (yy+masky+maskh-1) / 16;
		Rectangle wall = null;
		
		if(World.tiles[x1 + (y1*World.WIDTH)] instanceof Wall) {
			wall = new Rectangle(x1*16, y1*16, 16, 16);
			if(en.intersects(wall))
				return true;
		}
				
		if(World.tiles[x1 + (y2*World.WIDTH)] instanceof Wall) {
			wall = new Rectangle(x1*16, y2*16, 16, 16);
			if(en.intersects(wall))
				return true;
		}
		
		if(World.tiles[x2 + (y1*World.WIDTH)] instanceof Wall) {
			wall = new Rectangle(x2*16, y1*16, 16, 16);
			if(en.intersects(wall))
				return true;
		}
				
		if(World.tiles[x2 + (y2*World.WIDTH)] instanceof Wall) {
			wall = new Rectangle(x2*16, y2*16, 16, 16);
			if(en.intersects(wall))
				return true;
		}
			
		return false;
			
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
		//g.setColor(Color.yellow);
		//g.drawRect(this.getX() + this.maskx - Camera.x, this.getY() + this.masky - Camera.y, this.maskw, this.maskh);
	}
	
}
