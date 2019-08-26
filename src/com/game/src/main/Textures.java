package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage playerL,playerR,egg,cat,basket1,basket2,basket3,cloud,enter;
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
	 ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
		
		
	} 
	
	private void getTextures() {		
		playerL = ss.grabImage(1,1,64,64);
		playerR = ss.grabImage(1,3,64,64);
		egg = ss.grabImage(3, 1, 64,64);
		basket1 = ss.grabImage(7,1,64,64);
		basket2 = ss.grabImage(3,3,64,64);
		basket3 = ss.grabImage(5,3,64,64);
		cat = ss.grabImage(5, 1, 64, 64);		
		
	}
	

}
