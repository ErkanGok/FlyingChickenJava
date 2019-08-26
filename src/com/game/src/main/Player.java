package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.lang.Thread.State;


import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;


public class Player extends GameObject implements EntityA{

	private double velX = 0;
	private double velY = 0;
	
	Game game;
	Controller c;
	
	private Textures tex;
	
	public Player(double x, double y,Game game,Textures tex,Controller c) {		
		super(x, y);
		this.game = game;	
		this.tex = tex;
		this.c = c;
	}
	
	
		
	public void tick() {		
		x+=velX;
		y+=velY;	
		//tavuðun kenarlardan çýkmamasý
		if(x <= 64) 
			x = 64;
		if(x >= 900-64) 
			x =900-64 ;
		if(y <= 119)
			y = 119;
		if(y >= 650 )
			y = 650;
		
		for(int i = 0;i<game.eb.size();i++)
		{
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt))
			{
			 c.removeEntity(tempEnt);
			 game.HEALTH-=20;
			 game.setCat_killed(game.getCat_killed() +2 );	
			 if(game.HEALTH != 40)
			 Sound.hit();
			 game.healthsound();
		    }
		
		}              }
		
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public void render(Graphics g) {		
		if( game.getdirection()  == 0) 
		g.drawImage(tex.playerL,(int)x,(int)y,null);		
		if( game.getdirection() == 1) 
		g.drawImage(tex.playerR,(int)x,(int)y,null);
		
		 g.drawImage(game.getPanel(), 0, 0, null);
		
		
	}
	
	public double getX() {
		return x;     }
	
	public double getY() {
		return y;     }
	
	public void setX(double x) {
		this.x = x;     }
	
	public void setY(double y) {
		this.y = y;     }
			
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}	
	
	public double getVelX() {
		return velX;     }
	
	public double getVelY() {
		return velY;     }
	
	
	
}
