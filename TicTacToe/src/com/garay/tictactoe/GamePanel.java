package com.garay.tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	Player player;
	Computer computer;
	Timer gameTimer;
	Point pointer;
	JLabel resultLabel;
	
	ArrayList<Rectangle> gridHitbox;
	int[] grid;
	
	int outcome = 0;
	int moves = 1;
	boolean turn = true;
	
	int[][] checks = { 
	                  {0,1,2}, {3,4,5}, {6,7,8}, // horizontal checks
	                  {0,3,6}, {1,4,7}, {2,5,8}, // vertical checks
	                  {0,4,8}, {2,4,6}}; // diagonal checks
	
	public GamePanel() {
		addMouseListener(new KeyChecker(this));
		
		grid = new int[9];
		
		gridHitbox = new ArrayList<Rectangle>();
		makeGrid();
		
		player = new Player(this);
		computer = new Computer(this, player);
		
		resultLabel = new JLabel();
		add(resultLabel);
		
		gameTimer = new Timer();
		gameTimer.schedule(new GameTimerTask(), 0, 17);
	}// end constructor
	
	class GameTimerTask extends TimerTask {
		
		public void run() {
			// Main game loop
			
			if (moves <= 9) {
				player.set();
				computer.set();
				repaint();
			} else {
				winner();
			}
		}// end run()
	}// end GameTimerTask inner class
	
	public void reset() {
		// Resets all the important variable for a new game
		
		turn = true;
		outcome = 0;
		moves = 1;
		resultLabel.setText("");
		
		grid = new int[9];
		player = new Player(this);
		computer = new Computer(this, player);
	}// end reset()
	
	public void makeGrid() {
		// Saves the grid locations for the board
		
		int y = 50;
		for (int i=0; i<3; i++) {
			int x = 50;
			
			for (int j=0; j<3; j++) {
				gridHitbox.add(new Rectangle(x, y, 100, 100));
				x += 100;
			}// end for
			
			y += 100;
		}// end for
	}// end makeGrid()
	
	public void paint(Graphics g) {
		// Draws all the elements required, including the player and computer elements
		
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g;
		
		gtd.setColor(Color.BLACK);
		for (Rectangle rect: gridHitbox) {
			gtd.drawRect((int)rect.getX(), (int)rect.getY(), 
					(int)rect.getWidth(), (int)rect.getHeight());
		}// end for
		
		player.draw(gtd);
		computer.draw(gtd);
	}// end paint()
	
	public void checkBoard() {
		// Checks if there is a winning pattern
		
		for (int[] pattern: checks) {
			int x = grid[pattern[0]];
			int y = grid[pattern[1]];
			int z = grid[pattern[2]];
			
			if ((x != 0) && (y != 0) && (z != 0)) {
				if ((x == y) && (x == z) && (y == z)) {
					moves = 10;
					outcome = x;
					winner();
					break;
				}// end if
			}// end if
		}// end for
	}// end checkBoard()
	
	public void winner() {
		// Displays the appropriate winner
		
		if (moves == 10) {
			if (outcome == 1) {
				resultLabel.setText("Computer Wins");
			} else if (outcome == -1) {
				resultLabel.setText("You Win");
			} else {
				resultLabel.setText("Draw");
			}// inner if-else
		}// if 
	}// end gameOver()
}// end GamePanel class
