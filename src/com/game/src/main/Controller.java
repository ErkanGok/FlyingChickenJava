package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;


public class Controller {

	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	private LinkedList<EntityC> ec = new LinkedList<EntityC>();
	
	
	
	EntityA enta;
	EntityB entb;
	EntityC entc;
	
	
	int x = Game.WÝDTH * Game.SCALE;
	int y = Game.HEÝGHT * Game.SCALE;
	
	private Game game;
	
	
	
	Random r = new Random();
	private Textures tex;
	
	public Controller(Textures tex,Game game) {
	this.tex = tex;
	this.game = game;	
	}
	
	public void createBasket(int basket_count) {
		
    	addEntity(new Basket(0,y,tex,1,this,game));
		addEntity(new Basket(x-55,y,tex,0,this,game));
		
	}
	
	//============================
	
	public void createCat(int Cat_count) {
		int level = 1;
		if (game.getlevel() == 1)
			level = 1;
		if (game.getlevel() == 2)
			level = 2;
		if (game.getlevel() == 3)
			level = 3;
		for(int i = 0 ; i<level; i++)				
		addEntity(new Cat(r.nextInt(x-130),y+150,tex,game,this));	
				
	}
	
	//============================
	
	public void tick() {
		//A CLASS
		for(int i = 0; i<ea.size();i++) {
			enta = ea.get(i);
			
			enta.tick();
			}
		
		//B CLASS
				for(int i = 0; i<eb.size();i++) {
					entb = eb.get(i);
					
					entb.tick();
					}
		//C CLASS
				for(int i = 0; i<ec.size();i++) {
					entc = ec.get(i);
					
					entc.tick();
					}
				
	}
	
	//===============================
	
	public void render(Graphics g) {
		//A CLASS
		for(int i = 0; i<ea.size();i++) {
			enta = ea.get(i);
			
			enta.render(g);
	   }
		
		//B CLASS
				for(int i = 0; i<eb.size();i++) {
					entb = eb.get(i);
					
					entb.render(g);
			   }
				
		//C CLASS				
				for(int i = 0; i<ec.size();i++) {
					entc = ec.get(i);
					
					entc.render(g);
			   }
				
			
	}
	
	
	//A CLASS
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	
	//B CLASS
		public void addEntity(EntityB block) {
			eb.add(block);
		}
		
		public void removeEntity(EntityB block) {
			eb.remove(block);
		}
		
		//C CLASS
				public void addEntity(EntityC block) {
					ec.add(block);
				}
				
				public void removeEntity(EntityC block) {
					ec.remove(block);
				}		
				
	
		
		//======================
		
		public LinkedList<EntityA> getEntityA() {
			return ea;
		}
		
		public LinkedList<EntityB> getEntityB() {
			return eb;
		}
		
		public LinkedList<EntityC> getEntityC() {
			return ec;
		}
	
	
	
	
	
	
}
