package grafics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class UI {
	
	public static BufferedImage FULL_HEART = Game.spriteSheet.getSprite(128, 0, 8, 8);

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
	}
	
}
