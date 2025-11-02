package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class GemMenuButton extends Button {

	public GemMenuButton(double x, double y, int width, int height, BufferedImage sprite, String nome) {
		super(x, y, width, height, sprite, nome);
	}

	@Override
	public void tick() {
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				dark = true;
				UI.BigButtons.get(0).dark = false;
				UI.subButtons.clear();
				UI.subButtons.add(new TowerButton(754, 68, 40, 40, Game.spriteSheet.getSprite(32, 32, 16, 16), 25, "torre", 4));
				UI.subButtons.add(new TowerButton(814, 68, 40, 40, Game.spriteSheet.getSprite(48, 0, 16, 16), 20, "poison", 5));
				UI.subButtons.add(new TowerButton(754, 128, 40, 40, Game.spriteSheet.getSprite(64, 0, 16, 16), 20, "mana-gen", 6));
			}
			
			
			
		}
	}
	
}
