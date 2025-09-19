package entities.monkeys;

import java.awt.image.BufferedImage;

public class SuperMonkey extends Monkey {

	public SuperMonkey(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.range = 75;
		this.fireRate = 10;
	}

}
