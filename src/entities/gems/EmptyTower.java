package entities.gems;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Tower;

public class EmptyTower extends Tower {
	
	public boolean wGem = false;
	public Gem gem;

	public EmptyTower(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

	}
	
	@Override
	public void tick() {
		if(wGem)
			gem.tick();
	}
	
	@Override
	public void render(Graphics g) {
		if(wGem) {
			gem.render(g);
		} else {
			super.render(g);
		}
	}

}
