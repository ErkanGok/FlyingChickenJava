package com.game.src.main;

import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound  { 
	
	
	//sepet point
	public static void point() {	
		try {
			File sound = new File("res//sfx_point.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			System.out.println("Sound file loading error!");
		}

	}
	
	//kedi_hit
	public static void hit() {
		try {
			File sound = new File("res//sfx_hit.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			System.out.println("Sound file loading error!");
		}

	}
	
	//levelup
	
	public static void levelup() {
		try {
			File sound = new File("res//levelup.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (Exception e) {
			System.out.println("Sound file loading error!");
		}

	}
	
	//tebrikler
	
		public static void tebrikler() {
			try {
				File sound = new File("res//congrulation.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				
				
			} catch (Exception e) {
				System.out.println("Sound file loading error!");
			}

		}
		
		//gameover
		public static Clip clipd;
		public static void gameover() {
			try {
				File sound = new File("res//gameover.wav");
				AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
				clipd = AudioSystem.getClip();
				clipd.open(ais);
				clipd.start();
			} catch (Exception e) {
				System.out.println("Sound file loading error!");
			}

		}
		
		//alert
		
				public static void alert() {
					try {
						File sound = new File("res//alert.wav");
						AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
						Clip clip = AudioSystem.getClip();
						clip.open(ais);
						clip.start();
					} catch (Exception e) {
						System.out.println("Sound file loading error!");
					}

				}				
				
				//giris
				public static Clip clipb;
				
				public static void giris() {
					
					try {							
						File sound = new File("res//giris2.wav");
						AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
						clipb = AudioSystem.getClip();
						clipb.open(ais);
						clipb.start();			
						
					} catch (Exception e) {
						System.out.println("Sound file loading error!");
						
					}	
					
					
				}
				
				
					
					
					
					
				
				//oyunici
				public static Clip clipc;
				public static void oyunici() {											
					try {
						File sound = new File("res//oyunici.wav");
						AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
						clipc = AudioSystem.getClip();
						clipc.open(ais);
						clipc.start();	
					} catch (Exception e) {
						System.out.println("Sound file loading error!");
					}						
					
				}

				
				
				
				

				
			
				
				
				
				
				

}
