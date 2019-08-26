package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {
	
	private int direction;
	
	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y,Game game,int direction,Textures tex) {
		super(x, y);
		this.direction = direction;		
		this.tex = tex;
		this.game = game;
	}	
	
	public void tick() {	
		
		if (direction == 1)
		x+=10;		
		if (direction == 0)
		x-=10;
		
		
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public void render(Graphics g) {		
		g.drawImage(tex.egg,(int)x,(int)y,null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}	

}
