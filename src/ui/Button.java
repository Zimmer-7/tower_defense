package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Button {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected String nome;
	
	protected boolean dark = false;
	
	protected BufferedImage sprite;
	
	public Button(double x, double y, int width, int height, BufferedImage sprite, String nome) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.nome = nome;
		
		this.sprite = sprite;
	}
	
	public void tick() {
		
	}
	
	protected boolean pressed() {
		return Game.mouse.x >= x && 
			   Game.mouse.x <= x+width &&
			   Game.mouse.y >= y && 
			   Game.mouse.y <= y+height;
	}
	
	protected boolean pressedMap() {
		return Game.mouse.x*3 >= x && 
			   Game.mouse.x*3 <= x+width &&
			   Game.mouse.y*3 >= y && 
			   Game.mouse.y*3 <= y+height;
	}
	
	public void render(Graphics g) {
		if(!dark) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.red);
		}
		
		g.fillRect((int)x, (int)y, width, height);
		if(sprite != null) {
			g.drawImage(sprite, (int)x, (int)y, width, height, null);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString(nome, (int)x, (int)(y));
		
	}
	
}
