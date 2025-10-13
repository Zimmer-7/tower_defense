package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class TowerButton extends Button {
	
	public int cost;
	public int id;

	public TowerButton(double x, double y, int width, int height, BufferedImage sprite, int cost, String nome, int id) {
		super(x, y, width, height, sprite, nome);
		this.cost = cost;
		this.id = id;
	}
	
	@Override
	public void tick() {
		if(!Game.towerController.ready && !Game.gemController.ready)
			dark = false;
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				for(int i = 0; i < UI.subButtons.size(); i++) {
					UI.subButtons.get(i).dark = false;
				}
				
				dark = true;
				
				if(id < 5) {
					Game.towerController.ready = true;
					Game.gemController.ready = false;
					Game.towerController.towerID = id;
					Game.towerController.towerCost = cost;
				} else {
					Game.gemController.ready = true;
					Game.towerController.ready = false;
					Game.gemController.gemID = id;
					Game.gemController.gemCost = cost;
				}
			}
			
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("$:"+cost, (int)x+8, (int)(y+width+8));
	}
	
}
