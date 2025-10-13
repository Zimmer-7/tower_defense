package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class MonkeyMenuButton extends Button {

	public MonkeyMenuButton(double x, double y, int width, int height, BufferedImage sprite, String nome) {
		super(x, y, width, height, sprite, nome);
	}

	@Override
	public void tick() {
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				UI.subButtons.clear();
				UI.subButtons.add(new TowerButton(754, 68, 40, 40, Game.spriteSheet.getSprite(48, 16, 16, 16), 20, "dardo", 1));
				UI.subButtons.add(new TowerButton(814, 68, 40, 40, Game.spriteSheet.getSprite(64, 16, 16, 16), 90, "Super", 2));
				UI.subButtons.add(new TowerButton(754, 128, 40, 40, Game.spriteSheet.getSprite(80, 16, 16, 16), 45, "Bomba", 3));
			}
			
			
		}
	}
	
}
