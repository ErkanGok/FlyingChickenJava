package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Basket extends GameObject implements EntityB {
	
	Random r = new Random();
	int sepet =  r.nextInt(4);
	
	
	private Game game;
	private Controller c;
	
	private Textures tex;
	private int direction;
	Bullet b;
	Sound sound;
	
	//====================================

	
		   
	
	
	
	
	
	
	
	//====================================
	
	
	
	
	private int speed = r.nextInt(2) +1;
	
	public Basket(double x,double y,Textures tex,int direction,Controller c,Game game) {		
		super(x, y);
		this.tex = tex;
		this.direction = direction;
		this.c = c;
		this.game = game;
	}
	
	public void tick() {	
		
		if (game.getlevel() == 1)
		y -=1;
		else if (game.getlevel() == 2) 
		y -=3;        	
		else if (game.getlevel() == 3)
		y -=5;
		
		if(y <= 114 ) 
			c.removeEntity(this);
		 
		
		
		
		/*if (y<-10 && this.direction == 1) {			
			 //speed = (r.nextInt(2) +1);
			 x = 0;
			 y = Game.HEÝGHT * Game.SCALE -10 ;		
		 }
		 
		 if (y<-10 && this.direction == 0 ) {				
			 //speed = (r.nextInt(2) +1);
			 x = Game.WÝDTH * Game.SCALE -55;
			 y = Game.HEÝGHT * Game.SCALE -10 ;			
		 }*/
		 
		 for(int i=0;i<game.ea.size();i++) 
		 {
			EntityA tempEnt = game.ea.get(i);
			
			if (Physics.Collision(this, tempEnt)) 
			 {
				c.removeEntity(this);//yumurta geldiðin de sepeti yok eder				
				//game.setBasket_killed(game.getBasket_killed()+1);
				if(sepet==1)
				game.setsepet_point(game.getsepet_point()+250);
				if(sepet==2)
				game.setsepet_point(game.getsepet_point()+500);	
				if(sepet==3)
				game.setsepet_point(game.getsepet_point()+750);	
			    //c.createBasket(game.getBasket_count());				
				sound.point();
				game.levelsound();
										
			 }	
		 }
		 
		 
		
	}
	
	public void render(Graphics g) {
		
		
		
		if(sepet==1)
		g.drawImage(tex.basket1,(int)x,(int)y,null);
		if(sepet==2)
		g.drawImage(tex.basket2,(int)x,(int)y,null);
		if(sepet==3)
		g.drawImage(tex.basket3,(int)x,(int)y,null);
			
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {		
		return x;
	}

}
