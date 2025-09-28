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
		if(!Game.towerController.ready)
			dark = false;
		
		if(Game.mouse.pressedUI) {
			
			if(pressed()) {
				Game.towerController.ready = true;
				Game.towerController.towerID = id;
				Game.towerController.towerCost = cost;
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
