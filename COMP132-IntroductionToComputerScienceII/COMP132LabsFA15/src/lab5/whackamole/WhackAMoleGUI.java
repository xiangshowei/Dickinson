package lab05.whackamole;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
/**
 * A GUI for the Whack-a-Mole game.
 * 
 * @author Grant Braught
 * 
 * @author Xiang Wei
 * @version 10/13/15
 */
public class WhackAMoleGUI extends JFrame implements Observer {

	private WhackAMoleModel moleModel;
	private int preWhackMoleXPos;
	private int preWhackMoleYPos;
	private JLabel scoreLabel;
	private JButton[][] board;
	private static Timer timer;
	private JRadioButton levelButton;
	private static final ImageIcon holeIcon = new ImageIcon(WhackAMoleGUI.class.getResource("icons/hole.jpg"));
	private static final ImageIcon moleIcon = new ImageIcon(WhackAMoleGUI.class.getResource("icons/gopher.jpg"));

	/**
	 * Construct a new WhackAMoleGUI for the specified model.
	 * 
	 * @param myModel the model for this GUI.
	 */
	public WhackAMoleGUI(WhackAMoleModel myModel) {
		super("Whack-A-Model");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		moleModel = myModel;
		moleModel.addObserver(this);
		
		preWhackMoleXPos = moleModel.getMoleRow();
		preWhackMoleYPos = moleModel.getMoleCol();

		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(createButtonForLevel(1));
		mainPanel.add(createButtonForLevel(2));
		mainPanel.add(createButtonForLevel(3));
		
		//making sure that the score label stays near the center of the GUI
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(getScoreLabel());
		mainPanel.add(Box.createHorizontalGlue());
		mainPanel.add(getBoard());

		this.pack();
	}

	/**
	 * This helper method builds the GUI representation for displaying 
	 * the player's score when playing the game. This is called in the constructor.
	 * 
	 * @return scorePanel the JPanel containing the JLabel for displaying
	 * the player's score on the GUI
	 */
	private JPanel getScoreLabel() {
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));

		scoreLabel = new JLabel("Score: 0");
		scorePanel.add(scoreLabel);

		return scorePanel;
	}

	private JPanel getBoard() {
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));

		board = new JButton[WhackAMoleModel.BOARD_WIDTH][WhackAMoleModel.BOARD_LENGTH];

		//adding 4 rows to the board
		boardPanel.add(createRow(0));
		boardPanel.add(createRow(1));
		boardPanel.add(createRow(2));
		boardPanel.add(createRow(3));
		boardPanel.add(createRow(4));

		//putting the mole on the board
		board[preWhackMoleXPos][preWhackMoleYPos].setIcon(moleIcon); 

		return boardPanel;
	}
	
	private JPanel createRow(int rowNum) {
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
		for (int i = 0; i < WhackAMoleModel.BOARD_WIDTH; i++) {
			board[rowNum][i] = new JButton(holeIcon);
			board[rowNum][i].addActionListener(new ButtonListener(rowNum, i));
			row.add(board[rowNum][i]);
			if(i >= 0 && i < WhackAMoleModel.BOARD_WIDTH - 1) {
				//separate each row vertically with some space
				row.add(Box.createRigidArea(new Dimension(10, 0)));
			}
		}
		
		return row;
	}
	
	private JRadioButton createButtonForLevel(int level) {
		levelButton = new JRadioButton("Level " + level);
		levelButton.addActionListener(new TimerListener());
		
		if(level == 3) {
			timer.setDelay(1000);
		}
		
		else if(level == 2) {
			timer.setDelay(3000);
		}
		
		else {
			timer.setDelay(5000);
		}
		
		timer.start();
		
		return levelButton;
	}

	/**
	 * Update the GUI to reflect the state of the model. This method repaints
	 * all of the buttons with a hole and then repaints the button with the mole
	 * on it.
	 */
	public void update(Observable o, Object arg) {
		scoreLabel.setText("Score: " + moleModel.getScore());
		
		//Setting the the location where the mole is clicked to a hole icon
		board[preWhackMoleXPos][preWhackMoleYPos].setIcon(holeIcon);
		
		//making the mole appear at the new random location
		board[moleModel.getMoleRow()][moleModel.getMoleCol()].setIcon(moleIcon);
		
		/* After a location on the board has been whacked, the mole's pre-whack coordinates 
		 * need to be updated so the mole's previous location is changed to a hole icon when
		 * the player whacks a location on the board again.
		 */
		preWhackMoleXPos = moleModel.getMoleRow();
		preWhackMoleYPos = moleModel.getMoleCol();
		
		timer.restart();
	}

	/**
	 * Run the WhackAMole game.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		WhackAMoleModel wamm = new WhackAMoleModel();
		WhackAMoleGUI gui = new WhackAMoleGUI(wamm);
		gui.setVisible(true);
	}


	private class ButtonListener implements ActionListener {

		private int buttonXPosition;
		private int buttonYPosition;

		/**
		 * This listener listens for clicks on all of the buttons on the board
		 * and makes the appropriate call(s) to update the model. 
		 * 
		 * @param row the row on the board
		 * @param col the column on the board
		 */
		public ButtonListener(int row, int col){
			buttonXPosition = row;
			buttonYPosition = col;			
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			moleModel.whack(buttonXPosition, buttonYPosition);
		}
	}
	
	private class TimerListener implements ActionListener {
		
//		private int level;
//
//		public TimerListener(int levelNum) {
//			level = levelNum;
//		}

		@Override
		public void actionPerformed(ActionEvent ae) {
//			if(level == 3) {
//				timer.);
//			}
//			
//			else if(level == 2) {
//				timer = new Timer(3000, new TimerListener(level));
//			}
//			
//			else {
//				timer = new Timer(5000, new TimerListener(level));
//			}
			
			timer.stop();
			moleModel.moveMoleToNewLocation();
		}
	}
}
