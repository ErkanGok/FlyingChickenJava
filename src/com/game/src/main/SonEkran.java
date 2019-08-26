package com.game.src.main;

import java.awt.Graphics;

public class SonEkran {
	
	Game game;
	
	public SonEkran(Game game) {
		this.game = game;
	}
	
	

	public void render(Graphics g) {
		g.drawImage(game.getsonekran(),0,0,null);
	}
}
