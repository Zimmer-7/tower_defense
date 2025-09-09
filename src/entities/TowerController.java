package entities;

import entities.monkeys.Monkey;
import main.Game;
import world.World;

public class TowerController {
	
	public boolean ready = false;
	private boolean blocked = false;
	
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
			
			Monkey tower = new Monkey(xx, yy, 16, 16, Game.spriteSheet.getSprite(32, 16, 16, 16));
			
			for(int i = 0; i < Game.towers.size(); i++) {
				Entity e = Game.towers.get(i);
				if(Entity.isColliding(e, tower)) {
					return;
				}
			}
				
			Game.entities.add(tower);
			Game.towers.add(tower);
			Game.player.mana-=20;
			ready = false;
			
		}
	}
	
}
