package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import entities.gems.Sparkle;
import main.AdvancedSound;
import main.Game;
import main.Sound;
import world.AStar;
import world.Node;
import world.Vector2i;
import world.World;

public class Enemy extends Entity {
	
	private boolean justSpawned = true;
	
	public double speed = 0.5;
	protected boolean right = false;
	protected boolean left = false;
	protected boolean down = false;
	protected double maxLife = 5;
	public double life = maxLife;
	private boolean damaged = false;
	public int poisonTime = 0;
	public int poisonDamage = 0;
	private int time = 0;
	
	private int frames = 0;
	private int damageFrames = 0;
	private int maxFrames = 6;
	private int index = 0;
	protected int maxIndex = 1;
	
	protected BufferedImage[] enemyRight;
	protected BufferedImage[] enemyLeft;
	protected BufferedImage enemyDamageLeft;
	protected BufferedImage enemyDamageRight;

	public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		
		depth = 0;
		
		this.setMask(3, 5, 8, 6);
		
	}
	
	public void tick() {
		
		if(this.calcDistance(this.getX(), Game.player.getX(), this.getY(), Game.player.getY()) < 1000){
			
			if(path == null || path.isEmpty()) {
				Vector2i start = new Vector2i((int)(x/16), (int)(y/16));
				Vector2i end = new Vector2i((int)((Game.player.x+8)/16), (int)((Game.player.y+8)/16));
				path = AStar.findPath(start, end);
			}
			
			followPath(path, speed);
			
			if(justSpawned) {
				justSpawned = false;
				return;
			}
			
			if(touching() && frames == 0) {
				AdvancedSound.hurt.play();
				if(this instanceof Boss) {
					Game.player.life = 0;
				} else {
					Game.player.life --;
				}
				
				Game.player.mana -= life;
				Game.player.damaged = true;
				Game.entities.remove(this);
				Game.enemies.remove(this);
			}
			
			frames ++;
			if(frames == maxFrames) {
				index++;
				frames = 0;
					
				if(index > maxIndex) 
					index = 0;
					
			}
			
			if(poisonTime > 0) {
				time++;
				if(time >= 60) {
					time = 0;
					poisonTime--;
					life-=poisonDamage;
					if(poisonTime%3 == 0 && poisonDamage > 0)
						poisonDamage--;
				}
				
			}
			
			if(life <= 0) {
				die();
				return;
			}
			
			if(damaged) {
				damageFrames ++;
				if(damageFrames == maxFrames) {
					damageFrames = 0;
					damaged = false;
				}
			}
				
		} 
		
	}
	
	public boolean touching() {
		Rectangle current = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		
		return current.intersects(player);
	}
	
	protected void die() {
		Game.entities.remove(this);
		Game.enemies.remove(this);
		Game.player.mana += maxLife;
	}
	
	public boolean isCollidingEn(int xnext, int ynext) {
		Rectangle current = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);
		
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			
			Rectangle target = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			
			if(current.intersects(target))
				return true;
		}
		
		return false;
	}
	
	public void hurt(double damage) {
		life-=damage;
		damaged = true;
		
	}
	
	public void followPath(List<Node> path, double speed) {
		if (path != null && !path.isEmpty()) {
	        Vector2i target = path.get(path.size() - 1).tile;

	        double targetX = target.x * 16;
	        double targetY = target.y * 16;

	        double dx = targetX - x;
	        double dy = targetY - y;

	        double distance = Math.sqrt(dx * dx + dy * dy);

	        if (distance >= speed) {

	            if (dx > 0 && World.isFree((int)(x + speed), (int)y)) {
	            	x += (dx / distance) * speed;
	                right = true;
	                left = false;
	                down = false;
	            } else if (dx < 0 && World.isFree((int)(x - speed), (int)y)) {
	            	x += (dx / distance) * speed;
	                right = false;
	                left = true;
	                down = false;
	            } 
	            if((dy > 0 && World.isFree((int)x, (int)(y + speed))) || (dy < 0 && World.isFree((int)x, (int)(y - speed)))) {
	            	 y += (dy / distance) * speed;
	            	 down = true;
	            	
	            }
	        } else {
	            x = targetX;
	            y = targetY;
	            path.remove(path.size() - 1);
	        }
	    }
			
	}
	
	@Override
	public void render(Graphics g) {
		if(right) {
			g.drawImage(enemyRight[index], this.getX(), this.getY(), null);
			if(damaged) {
				g.drawImage(enemyDamageRight, this.getX(), this.getY(), null);
			}
		}
		if(left) {
			g.drawImage(enemyLeft[index], this.getX(), this.getY(), null);
			if(damaged) {
				g.drawImage(enemyDamageLeft, this.getX(), this.getY(), null);
			}
		}
		
		g.setColor(Color.red);
		g.fillRect(this.getX(),this.getY(), 15, 2);
		g.setColor(Color.green);
		g.fillRect(this.getX(),this.getY(), (int)((life/maxLife)*15), 2);
		
		//g.setColor(Color.blue);
		//g.drawRect(this.getX() + maskx, this.getY() + masky, maskw, maskh);
	}

}
