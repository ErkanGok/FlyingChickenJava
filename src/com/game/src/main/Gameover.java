package com.game.src.main;

import java.awt.Graphics;


public class Gameover {
	
	Game game;
	
	public Gameover(Game game) {
		this.game = game;
	}
	

	
public void render (Graphics g) {
		
	if(game.getmdirection() == 3 ) 
		g.drawImage(game.getgameover1(),0,0,null);		 
	if(game.getmdirection() == 2 )
		g.drawImage(game.getgameover2(),0,0,null);

}

}
