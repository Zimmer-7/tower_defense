package entities;

import entities.gems.EmptyTower;
import entities.gems.Gem;
import entities.monkeys.DartMonkey;
import entities.monkeys.SuperMonkey;
import main.Game;
import world.World;

public class GemController {

	public boolean ready = false;
	public int gemID;
	public int gemCost;
	private Gem gem;
	
	public void tick() {
		if(Game.mouse.pressedMap) {
			
			if(!ready)
				return;
			
			if(Game.player.mana < gemCost)
				return;
			
			int xx = Game.mouse.x/16*16;
			int yy = Game.mouse.y/16*16;
			
			if(World.isFree(xx, yy))
				return;
		
			if(gemID == 5) 
				gem = new Gem(xx, yy, 16, 16, Game.spriteSheet.getSprite(48, 32, 16, 16), "green");
			if(gemID == 6)
				gem = new Gem(xx, yy, 16, 16, Game.spriteSheet.getSprite(64, 32, 16, 16), "orange");
			
			for(int i = 0; i < Game.emptyTowers.size(); i++) {
				EmptyTower e = Game.emptyTowers.get(i);
	
				if(Entity.isColliding(e, gem)) {
					
					if(!e.wGem) {
						e.gem = gem;
						e.wGem = true;
						e.gem.updateGem(1);
						Game.player.mana-=gemCost;
						ready = false;
						return;
					}else if(e.gem.grade == 1) {
						gem.addColor(e.gem.getColor());
						e.gem = gem;
						e.gem.updateGem(2);
						Game.player.mana-=gemCost;
						ready = false;
						return;
					}
				
				}
				
			}
			
		}
	}
	
}
