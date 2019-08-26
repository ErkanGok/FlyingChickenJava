package com.game.src.main;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;



public class Menu {	
	
	Game game;
	
	public Menu(Game game) {
		this.game = game;
	}
	
	
	

	
public void render (Graphics g) {
		
	if(game.getmdirection() == 3 )
		g.drawImage(game.getbackround1(),0,0,null);
	if(game.getmdirection() == 2 )
		g.drawImage(game.getbackround2(),0,0,null);								
	

}


}
 