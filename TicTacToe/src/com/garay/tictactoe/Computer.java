package com.garay.tictactoe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Computer {

	GamePanel panel;
	Player player;
	
	
	ArrayList<Rectangle> drawMoves;
	
	public Computer(GamePanel panel, Player player) {
		
		this.panel = panel;
		this.player = player;
		
		drawMoves = new ArrayList<Rectangle>();
	}// end constructor
	
	public void set() {
		// Tracks the moves being played
		
		if (!panel.turn && (panel.moves <= 9)) {
			
			// get a playable location
			int i = findNextMove();
			
			Rectangle grid = panel.gridHitbox.get(i);
			
			drawMoves.add(grid);
			panel.grid[i] = 1;
			
			panel.turn = true;
			panel.moves++;
			panel.checkBoard();
		}// end if
	}// end set()
	
	public void draw(Graphics2D gtd) {
		// Draws an O in the locations that have been played by the computer
		gtd.setColor(Color.RED);
		
		for (Rectangle rect: drawMoves) {
			gtd.drawOval((int)rect.getX()+1, (int)rect.getY()+1, 
					(int)rect.getWidth()-1, (int)rect.getHeight()-1);
		}// end for
	}// end draw()
	
	public synchronized int findNextMove() {
		int[] newBoard = panel.grid;
		int depth = panel.moves;
		
		int index = 0;
		int bestValue = -1000;
		
		for (int i=0; i<9; i++) {
			if (newBoard[i] == 0) {
				
				// Make move
				newBoard[i] = 1;
				
				// Recurse to find out the value of the move
				int moveValue = minimax(newBoard, true, depth+1);
				
				// Undo move
				newBoard[i] = 0;
				
				// Track the best move's value and index
				if (moveValue > bestValue) {
					
					index = i;
					bestValue = moveValue;
				}// end if
			}// end if
		}// end for
		
		return index;
	}
	
	public int minimax(int[] newBoard, boolean player, int depth) {
		
		// Base Case
		// if score is other than 0, then someone won
		// return 0 if depth is 0, hence meaning game is over no one has won
		int score = evaluate(newBoard);
		
		if (score !=  0) {
			return score;
		}// end if
		
		if ((newBoard.length+1) == depth) {
			return 0;
		}// end if
		
		
		// Recursion
		// Check if it's the computer's(maximizer) or the player's(minimizer) turn
		if (!player) {
			int best = -1000;
			
			// Check empty spots to play
			for (int i=0; i<9; i++) {
				if (newBoard[i] == 0) {
					
					// Make the move
					newBoard[i] = 1;
					
					// Recursive call, and choose the max value
					best = Math.max(best, minimax(newBoard, true, depth+1));
					
					// Undo move
					newBoard[i] = 0;
				}// end if
			}// end for
			
			return best;
			
		} else {
			int best = 1000;
			
			// Check empty spots to play
			for (int i=0; i<9; i++) {
				if (newBoard[i] == 0) {
					
					// Make the move
					newBoard[i] = -1;
					
					// Recursive call, and choose the min value
					best = Math.min(best, minimax(newBoard, false, depth+1));
					
					// Undo move 
					newBoard[i] = 0;
				} // end if
			}// end for
			
			return best;
			
		}// end if-else
	} // end minimax()
	
	public int evaluate(int[] currentBoard) {
		
		// State: is the 3 possible game states (player won, comp won, none)
		// 0 represents draw
		// Each move represents a move of a player:
		// -1 (x) player
		// 1 (o) computer
		int state = 0;
		int spacesLeft = 0;
		
		for (int i=0; i<9; i++) {
			if (currentBoard[i] == 0) {
				spacesLeft++;
			}// end if
		}// end for
		
		for (int[] patterns: panel.checks) {
			int x = panel.grid[patterns[0]];
			int y = panel.grid[patterns[1]];
			int z = panel.grid[patterns[2]];
			
			if ((x != 0)&&(y != 0)&&(z != 0)) {
				if ((x == y)&&(x == z)&&(y == z)) {
					state = x;
					break;
				}// end if
			}// end if
		}// end for
		
		// Depth represents how far into the game the players are
		// the higher the depth the sooner a player can win
		// Player won example: -1 * 2 = -2
		// Computer example: 1 * 2 = 2
		// none example: 0 * 2 = 0
		return state * (spacesLeft + 1); 
	}// end evaluate()
}// end Computer class
