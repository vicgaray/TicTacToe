package com.garay.tictactoe;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PlatformerGame {

	public static void main(String[] args) {
		
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(400, 450);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2),
				(int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
	 	
		frame.setResizable(false);
		frame.setTitle("Tic Tac Toe");
		frame.setVisible(true);
	}// end main()
}// end main class
