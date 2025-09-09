package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class MonkeyMenuButton extends Button {

	public MonkeyMenuButton(double x, double y, int width, int height, BufferedImage sprite, int cost) {
		super(x, y, width, height, sprite, cost);
	}

	@Override
	public void tick() {
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				UI.subButtons.clear();
				UI.subButtons.add(new MonkeyButton(754, 68, 40, 40, Game.spriteSheet.getSprite(32, 16, 16, 16), 20));
				UI.subButtons.add(new MonkeyButton(814, 68, 40, 40, Game.spriteSheet.getSprite(32, 16, 16, 16), 20));
			}
		}
	}
	
}
