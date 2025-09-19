package entities;

import entities.monkeys.*;
import main.Game;
import world.World;

public class TowerController {
	
	public boolean ready = false;
	public int towerID;
	public int towerCost;
	private Entity tower;
	
	public void tick() {
		if(Game.mouse.pressedMap) {
			Game.mouse.pressedMap = false;
			
			if(!ready)
				return;
			
			if(Game.player.mana < 20)
				return;
			
			int xx = Game.mouse.x/16*16;
			int yy = Game.mouse.y/16*16;
			
			if(World.isFree(xx, yy))
				return;
			
			if(towerID == 1) 
				tower = new DartMonkey(xx, yy, 16, 16, Game.spriteSheet.getSprite(48, 16, 16, 16));
			if(towerID == 2)
				tower = new SuperMonkey(xx, yy, 16, 16, Game.spriteSheet.getSprite(64, 16, 16, 16));
			
			for(int i = 0; i < Game.towers.size(); i++) {
				Entity e = Game.towers.get(i);
				if(Entity.isColliding(e, tower)) {
					return;
				}
			}
				
			Game.entities.add(tower);
			Game.towers.add(tower);
			Game.player.mana-=towerCost;
			ready = false;
			
		}
	}
	
}
