package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class GemMenuButton extends Button {

	public GemMenuButton(double x, double y, int width, int height, BufferedImage sprite, int cost) {
		super(x, y, width, height, sprite, cost);
	}

	@Override
	public void tick() {
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				UI.subButtons.clear();
				
			}
		}
	}
	
}
