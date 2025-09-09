package entities;

import entities.monkeys.Monkey;
import main.Game;
import world.World;

public class TowerController {

	public boolean pressed;
	public int mx;
	public int my;
	
	private boolean blocked = false;
	
	public void tick() {
		if(pressed) {
			pressed = false;
			int xx = mx/16*16;
			int yy = my/16*16;
			
			Monkey tower = new Monkey(xx, yy, 16, 16, Game.spriteSheet.getSprite(32, 16, 16, 16));
			
			if(Game.player.mana < 20)
				return;
			
			if(World.isFree(xx, yy))
				return;
			
			for(int i = 0; i < Game.towers.size(); i++) {
				Entity e = Game.towers.get(i);
				if(Entity.isColliding(e, tower)) {
					return;
				}
			}
				
			Game.entities.add(tower);
			Game.towers.add(tower);
			Game.player.mana-=20;
			
		}
	}
	
}
