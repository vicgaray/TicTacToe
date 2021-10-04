package com.garay.tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		
		GamePanel panel = new GamePanel();
		panel.setLocation(0, 0);
		panel.setVisible(true);
		
		this.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem reset = new JMenuItem("reset");
		reset.addActionListener(new ResetAction(panel));
		
		menu.add(reset);
		menuBar.add(menu);
		
		this.setJMenuBar(menuBar);
	}// end constructor
	
	class ResetAction implements ActionListener {
		
		GamePanel gamePanel;
		
		public ResetAction(GamePanel g) {
			
			gamePanel = g;
		}// end constructor

		public void actionPerformed(ActionEvent e) {
			// Calls onto the GamePanel to reset the current board
			
			gamePanel.reset();
		}// end actionPerformed()
	}// end ResetAction inner class
}// end MainFrame class
