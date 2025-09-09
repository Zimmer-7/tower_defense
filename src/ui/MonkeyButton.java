package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class MonkeyButton extends Button {

	public MonkeyButton(double x, double y, int width, int height, BufferedImage sprite, int cost) {
		super(x, y, width, height, sprite, cost);
	}
	
	@Override
	public void tick() {
		if(!Game.towerController.ready)
			dark = false;
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				dark = true;
				Game.towerController.ready = true;
			}
			
		}
	}
	
}
