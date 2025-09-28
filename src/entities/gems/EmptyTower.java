package entities.gems;

import java.awt.image.BufferedImage;

import entities.Tower;

public class EmptyTower extends Tower {

	public EmptyTower(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

	}
	
	@Override
	public void tick() {
		
	}

}
