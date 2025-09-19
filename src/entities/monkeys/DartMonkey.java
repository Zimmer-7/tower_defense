package entities.monkeys;

import java.awt.image.BufferedImage;

public class DartMonkey extends Monkey {

	public DartMonkey(double x, double y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.pierce = 2;
	}

}
