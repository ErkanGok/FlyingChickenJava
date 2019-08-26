/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;


/**
 *
 * @author PC
 */
public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;
    public static final int WÝDTH =320;
    public static final int HEÝGHT = WÝDTH /12*9;
    public static final int SCALE = 3;
    public final String TITLE = "Flying Chicken";
    private int direction;
    private int mdirection = 3;
    private int godirection = 3;
    private int level = 1;
    int süre = 3000;
    
   int a = 0;
   int b=900; 
    
    
    
    private boolean running = false;
    private Thread thread;
    
    
    //==========================================
    
    
    private BufferedImage image = new BufferedImage(WÝDTH,HEÝGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage background1 = null;
	private BufferedImage background2 = null;
	private BufferedImage gameover1 = null;
	private BufferedImage gameover2 = null;
	private BufferedImage sonekran = null;
	private BufferedImage panel = null;
	private int sepet_point = 0;
	 
    
    private boolean is_shooting = false;
    
    private int basket_count = 10;
    private int basket_killed = 0;
    
    private int cat_count = 10;
    private int cat_killed = 0;
    
    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu; 
    private Gameover gameover;
    private SonEkran sonEkran;
    
    
    
    
    
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
    public LinkedList<EntityC> ec;
    
    public static int HEALTH = 100;    
    
    private enum STATE {
    	MENU,
    	GAME,
    	Gameover,
    	Sonekran
    };
    
    private STATE State = STATE.MENU; 
    
    
    
    public void init() {    	
    	requestFocus();
    	BufferedImageLoader loader = new BufferedImageLoader();    	
    	try {    		
    		spriteSheet = loader.loadImage("/davuk2.png");
    		panel =  loader.loadImage("/panel.png");
    		background =  loader.loadImage("/arka.png");
    		background1 =  loader.loadImage("/giris1.png");
    		background2 =  loader.loadImage("/giris2.png");
    		gameover1 =  loader.loadImage("/gameover1.png");
    		gameover2 =  loader.loadImage("/gameover2.png");
    		sonekran =  loader.loadImage("/sonekran.png");
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	tex = new Textures(this);   	    	
    	c = new Controller(tex,this); 
    	p = new Player(200,200,this,tex,c);
    	menu = new Menu(this);
    	gameover = new Gameover(this);
    	sonEkran = new SonEkran(this);
    	
    	ea = c.getEntityA();
    	eb = c.getEntityB();
    	ec = c.getEntityC();
    	
    	
    	this.addKeyListener(new KeyInput(this));     	 
    	
    	Timer myTimer = new Timer();
    	TimerTask gorev = new TimerTask() {
			
			@Override
			public void run() {
				if (State == STATE.GAME) {
				c.createBasket(basket_count);
				c.createCat(cat_count);	
				}			
			}
		};    	
		
    	myTimer.schedule(gorev,0,1800); 
    	
    	
    	
		
    }  
    
   
    
    //==========================================
    
    private synchronized void start() {
        
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    //============================================
    
     private synchronized void stop() {
        
        if(!running)
            return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(1);
    }
     
     //======================================  
    
   public void run() {
       init();
       long lastTime = System.nanoTime();
       final double amountOfTicks = 60.0;
       double ns = 1000000000/amountOfTicks;
       double delta = 0;
       int updates = 0;
       int frames = 0;
       long timer = System.currentTimeMillis();
       
       while(running){     
          long now = System.nanoTime();
          delta += (now - lastTime) /ns;
          lastTime = now;
          
          if(delta >= 1) {
              tick();
              updates++;
              delta--;
          }
          
           render();    
           frames++;
           
           if(System.currentTimeMillis() - timer > 1000) {
               timer+=1000;
               System.out.println(updates + " Ticks , Fps " + frames);
               updates = 0;
               frames = 0;
           
           }
               
       }
      
       
       stop();
   
   }
   
   //======================================
  
   private void tick() {	 
	   if (State == STATE.GAME) {
		   p.tick();
		   c.tick();
		   
	   }
	   
	   if (HEALTH <= 0) {
			 State =STATE.Gameover;
			 Sound.clipc.close();
			 
		 }
	   
	   
	 
	   
	   if(sepet_point>=5000) {
		   State = STATE.Sonekran;
		   Sound.clipc.close();}
       
   }
   
   //======================================
   
   public void levelsound() {
	   if(sepet_point == 1000 )
 		   Sound.levelup();				
	   if(sepet_point == 2500) 
 		  Sound.levelup();	
	   if(sepet_point >= 5000) 
	 		  Sound.tebrikler();	   
   }
   
   public void healthsound() {
	   if(HEALTH==0) {
		   Sound.gameover();
		   Sound.clipc.close();}
	   if(HEALTH==40)
		   Sound.alert();
	   
   }
   
   
   
private void render() {
       
       BufferStrategy bs = this.getBufferStrategy(); 
       if (bs == null) {
           
           createBufferStrategy(3);
           return;
       }
       
       Graphics g = bs.getDrawGraphics();
       
       ///////////////////////////////////
       
       g.drawImage(image, 0 , 0 , getWidth(), getHeight(), this);      
       
       g.drawImage(background,0,a-=2,null);
       
       g.drawImage(background,0,b-=2,null);
       
     
     
      
     
       
      if(b==0)
    	  b=900;
      if(a==-900)
    	  a=0;
       
       if(State == STATE.GAME) {
    	   p.render(g);
    	   c.render(g);
    	   
    	   if(sepet_point >= 1000 && sepet_point < 2500) 
	     		   level = 2;	     		   
	       if(sepet_point >= 2500 && sepet_point < 5000) 
	     		   level = 3;  	   
    	   
    	 Font fnt1 = new Font("arial",Font.BOLD,30);
   			g.setFont(fnt1);   	
   			g.setColor(Color.black);
   			g.drawString("Health:", 10, 40);
   		
   			
   		 Font fnt2 = new Font("arial",Font.BOLD,30);
			g.setFont(fnt2);
			g.drawString("Level: "+String.valueOf(level), 750, 40);
			
			
			
			
		 Font fnt3 = new Font("arial",Font.BOLD,30);
	   		g.setFont(fnt3);
	   		g.drawString("Score:", 750, 80);
	   		
	   		Font fnt31 = new Font("arial",Font.BOLD,30);
	   		g.setFont(fnt31);
	   		g.drawString(String.valueOf(sepet_point) ,850, 80);
	   		
   		
    	   g.setColor(Color.black);
    	   g.fillRect(115,15,200,30);
    	   
    	   if (HEALTH>40)
    	   g.setColor(Color.red);
    	   else if(HEALTH<=40)
    		   g.setColor(Color.yellow);
    	   g.fillRect(115,15,HEALTH*2,30);     	   
    	   
    	   g.setColor(Color.black);
    	   g.drawRect(115,15,200,30);
    	   
       } else if(State == STATE.MENU) {    	   
    	   menu.render(g);    	   
       } else if (State ==STATE.Gameover) {
    	   gameover.render(g);    	   
       }else if (State ==STATE.Sonekran) {
    	   sonEkran.render(g);  
    	   
       }
      
       
       
       
       ///////////////////////////////////      
       g.dispose();
       bs.show();
   
   }
   
   //======================================

public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();	
	 
		if(State == STATE.GAME) {
			
			if (key == KeyEvent.VK_RIGHT ) {
				p.setVelX(5);
				direction = 1;				
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
				direction = 0;					
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
				mdirection = 2;
			}else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
				mdirection = 3;
			}else if (key == KeyEvent.VK_ENTER && mdirection == 3) {
				State =STATE.GAME;					
				HEALTH =100;	
				Sound.clipb.close();
				Timer myTimer = new Timer();
			     TimerTask gorev = new TimerTask() {					
						public void run() {						 
							Sound.oyunici();   					        
						}
					};    	
					
			    	myTimer.schedule(gorev,0,48000);
			} else if (key == KeyEvent.VK_ENTER && mdirection == 2) {
				System.exit(1);			
			}			
		if (key == KeyEvent.VK_SPACE && !is_shooting) {
			is_shooting = true;
			c.addEntity(new Bullet(p.getX(),p.getY(),this,direction,tex));
			
		}
		}
		
		
	}
	
	public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
			
		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);			
			direction = 1;			
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
			direction = 0;			
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
			mdirection = 2;
		}else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
			mdirection = 3;
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;			
		}else if (key == KeyEvent.VK_ENTER && mdirection == 3) {
			State =STATE.GAME;				
			HEALTH =100;
			Sound.clipb.close();//giris ekraný			
			 Timer myTimer = new Timer();
		     TimerTask gorev = new TimerTask() {					
					public void run() {						 
						Sound.oyunici();   					        
					}
				};    	
				
		    	myTimer.schedule(gorev,0,48000); 
			
		} else if (key == KeyEvent.VK_ENTER && mdirection == 2) {
			System.exit(1);			
		}		
	}
   
   //=======================================
   
    public static void main(String[] args) {
    	
        Game game = new Game();
        
       
        
   	 Timer myTimer = new Timer();
 	TimerTask gorev = new TimerTask() {
			
			@Override
			public void run() {	
				if(game.State == STATE.MENU) 
			        	Sound.giris();
						
		
						
				 		
			}
		};    	
		
 	myTimer.schedule(gorev,0,9000); 
    		
    	
    	
    		
    	 
	        	
	        
        
        
       
        
        
        game.setPreferredSize(new Dimension(WÝDTH * SCALE, HEÝGHT * SCALE));
        game.setMaximumSize(new Dimension(WÝDTH * SCALE, HEÝGHT * SCALE));
        game.setMinimumSize(new Dimension(WÝDTH * SCALE, HEÝGHT * SCALE));
        
        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);       
        
        game.start();
         
    }
    
    public BufferedImage getSpriteSheet() {
    	return spriteSheet;
    }
    
    public int getdirection() {
    	return direction;
    }  
    public int getmdirection() {
    	return mdirection;
    } 
    
    public int getgodirection() {
    	return godirection;
    }
   
    
    public int getBasket_count() {
		return basket_count;
	}



	public void setBasket_count(int basket_count) {
		this.basket_count = basket_count;
	}



	public int getBasket_killed() {
		return basket_killed;
	}



	public void setBasket_killed(int basket_killed) {
		this.basket_killed = basket_killed;
	}
	
	
	  
	   public int getCat_count() {
		return cat_count;
	}



	public void setCat_count(int cat_count) {
		this.cat_count = cat_count;
	}



	public int getCat_killed() {
		return cat_killed;
	}



	public void setCat_killed(int cat_killed) {
		this.cat_killed = cat_killed;
	}
	
	 public BufferedImage getbackround1() {
	    	return background1;
	    } 
	 public BufferedImage getbackround2() {
	    	return background2;
	    } 
	 
	 public BufferedImage getgameover1() {
	    	return gameover1;
	    }
	 
	 public BufferedImage getgameover2() {
	    	return gameover2;
	    }
	 
	 public BufferedImage getsonekran() {
	    	return sonekran;
	    }
	
	 public int getsepet_point() {
		 return sepet_point;
	 }
	 public void setsepet_point(int sepet_point) {
		this.sepet_point = sepet_point;
	 }
	 public int getlevel() {
		 return level;
	 }



	public BufferedImage getPanel() {
		return panel;
	}



	
	 
	 
	
	 
	 
	
   
    
}

