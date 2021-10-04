package com.garay.tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KeyChecker extends MouseAdapter {
 	
	GamePanel panel;
	
	public KeyChecker(GamePanel panel) {
		
		this.panel = panel;
	}// end constructor
	
	public void mousePressed(MouseEvent ev) {
		// Tracks players pointer location 
		
		if (panel.turn) {
			panel.pointer = ev.getPoint();
			panel.player.mouseClicked = true;
		}// end if
	}// end mousePressed()
	
}// end KeyChecker class
