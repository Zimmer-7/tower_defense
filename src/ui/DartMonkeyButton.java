package ui;

import java.awt.image.BufferedImage;

import main.Game;

public class DartMonkeyButton extends Button {

	public DartMonkeyButton(double x, double y, int width, int height, BufferedImage sprite, int cost, String nome) {
		super(x, y, width, height, sprite, cost, nome);
	}
	
	@Override
	public void tick() {
		if(!Game.towerController.ready)
			dark = false;
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				Game.towerController.ready = true;
				Game.towerController.towerID = 1;
				Game.towerController.towerCost = 20;
			}
			
		}
	}
	
}
