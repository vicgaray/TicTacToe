package com.garay.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player {
	
	GamePanel panel;
	
	ArrayList<Rectangle> drawMoves;
	
	boolean mouseClicked;
	
	public Player(GamePanel panel) {
		
		this.panel = panel;
		
		drawMoves = new ArrayList<Rectangle>();
	}// end constructor
	
	public void set() {
		// Tracks and changes players action based on each turn
		
		if (mouseClicked && (panel.moves <= 9)) {
			
			// Checks if the mouse clicked was within the grid
			// Saves matched position
			for (Rectangle grid: panel.gridHitbox) {
				if (grid.contains(panel.pointer)) {
					int i = panel.gridHitbox.indexOf(grid);
					
					drawMoves.add(grid);
					panel.grid[i] = -1;
					break;
				}// end if
			}// end for
			
			mouseClicked = false;
			panel.pointer = null;
			panel.turn = false;
			panel.moves++;
			panel.checkBoard();
		}// end if
	}// end set()
	
	public void draw(Graphics2D gtd) {
		// Draws an X in the locations that have been played by the player
		
		gtd.setColor(Color.BLUE);
		
		for (Rectangle rect: drawMoves) {
			gtd.drawLine((int)rect.getX()+1, (int)rect.getY()+1,
					(int)(rect.getX() + rect.getWidth())-1, (int)(rect.getY() + rect.getHeight())-1);
			
			gtd.drawLine((int)rect.getX()+1, (int)(rect.getY() + rect.getHeight())-1,
					(int)(rect.getX() + rect.getWidth())-1, (int)rect.getY()+1);
		}
	}// end draw()
}// end Player class
