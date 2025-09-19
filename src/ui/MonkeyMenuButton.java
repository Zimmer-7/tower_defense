package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class MonkeyMenuButton extends Button {

	public MonkeyMenuButton(double x, double y, int width, int height, BufferedImage sprite, int cost, String nome) {
		super(x, y, width, height, sprite, cost, nome);
	}

	@Override
	public void tick() {
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				UI.subButtons.clear();
				UI.subButtons.add(new DartMonkeyButton(754, 68, 40, 40, Game.spriteSheet.getSprite(48, 16, 16, 16), 20, "dardo"));
				UI.subButtons.add(new SuperMonkeyButton(814, 68, 40, 40, Game.spriteSheet.getSprite(64, 16, 16, 16), 90, "Super"));
			}
			
			if(UI.subButtons.get(0).pressed()) {
				UI.subButtons.get(0).dark = true;
				UI.subButtons.get(1).dark = false;
			}
			if(UI.subButtons.get(1).pressed()) {
				UI.subButtons.get(1).dark = true;
				UI.subButtons.get(0).dark = false;
			}
		}
	}
	
}
