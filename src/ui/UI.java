package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import main.Game;

public class UI {
	
	public static BufferedImage FULL_HEART = Game.spriteSheet.getSprite(128, 0, 8, 8);
	
	public List<Button> BigButtons;
	public static List<Button> subButtons;
	
	public UI() {
		BigButtons = new ArrayList<>();
		subButtons = new ArrayList<>();
		BigButtons.add(new MonkeyMenuButton(754, 8, 40, 40, Game.spriteSheet.getSprite(32, 16, 16, 16), 0));
		BigButtons.add(new GemMenuButton(814, 8, 40, 40, null, 0));
	}
	
	public void tick() {
		for(int i = 0; i < BigButtons.size(); i++) {
			Button b = BigButtons.get(i);
			b.tick();
		}
		
		if(!subButtons.isEmpty()) {
			for(int i = 0; i < subButtons.size(); i++) {
				Button b = subButtons.get(i);
				b.tick();
			}
		}
		
		Game.mouse.pressedUI = false;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(18,8, 204, 24);
		g.setColor(Color.blue);
		g.fillRect(20, 10, (int)((Game.player.mana/Game.player.maxMana)*200), 20);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Mana : "+Game.player.mana+"/"+Game.player.maxMana, 50, 25);
		for(int i = 0; i < Game.player.life; i++) {
			g.drawImage(FULL_HEART, 20+(i*20), 40, 24, 24, null);
		}
		
		g.setColor(Color.gray);
		g.fillRect(744, 0, 120, 400);
		for(int i = 0; i < BigButtons.size(); i++) {
			Button b = BigButtons.get(i);
			b.render(g);
		}
		
		if(!subButtons.isEmpty()) {
			for(int i = 0; i < subButtons.size(); i++) {
				Button b = subButtons.get(i);
				b.render(g);
			}
		}
	}
	
}
