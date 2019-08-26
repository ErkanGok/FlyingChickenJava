package com.game.src.main;

import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

public class Physics {
	
	public static boolean Collision(EntityA enta, EntityB entb) {		
			
			if(enta.getBounds().intersects(entb.getBounds())) {  
				return true;
					
		}		
		return false;
	}
	
	//=================================================              //yumurta ve sepet iliþkisi
	
public static boolean Collision(EntityB entb, EntityA enta) {		
			
			if(entb.getBounds().intersects(enta.getBounds())) {
				return true;
					
		}		
		return false;
	}

//===================================================

public static boolean Collision(EntityC entc, LinkedList<EntityA> enta) {
	
	for(int i = 0; i<enta.size();i++ ) {
		
		if(entc.getBounds().intersects(enta.get(i).getBounds())) {
			return true;
		}			
	}		
	return false;
}

}
