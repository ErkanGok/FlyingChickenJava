package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

public class Cat  implements EntityB {
	
	private double x,y;
	private Textures tex;
	private Game game;
	private Controller c;
	
	Random r = new Random();
	
	private int speed = r.nextInt(2) +1;
	
	public Cat(double x,double y,Textures tex,Game game,Controller c) {
		this.x = x;
		this.y = y;
		this.tex = tex;		
		this.game = game;
		this.c = c;
	}
	
	
	public void tick() {
		
		if (game.getlevel() == 1)
			y -=1;
			if (game.getlevel() == 2)
			y -=3;
			if (game.getlevel() == 3)
			y -=5;
		
			if(y <= 114 )
				c.removeEntity(this);
		
		if (x<=64)
			x = 64;
		if(x >= 900 - 64) 
			x= 900 - 64;
		
		if(y<-10) {
			//speed = (r.nextInt(2) +1);			
			if (x<=64)
				x = 64;			
			if(x >= 900 - 64) 				
				x= 900-64;
			//y =  game.HEÝGHT * game.SCALE-30 ;
		}	
		
		 /*for(int i=0;i<game.ea.size();i++) 
		 {
			EntityA tempEnt = game.ea.get(i);
			if (Physics.Collision(this, tempEnt)) 
			 {
				c.removeEntity(tempEnt);
				c.removeEntity(this);//yumurta geldiðin de sepeti yok eder
				game.setCat_killed(game.getCat_killed()+1);		
				
			 }	
		 }*/
		 
		
		 
	
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.cat,(int)x,(int)y,null);
	}



	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}
	
	public double getX() {		
		return x;
	}
		public double getY() {		
		return y;
	}

}
