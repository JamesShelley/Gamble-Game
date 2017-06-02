import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * Displays all of the interfaces for the game to function properly. This
 * includes all the buttons, labels etc.
 * 
 * @author James Shelley - s4923268
 * @version 1
 */

public class HigherOrLowerGUI {
	
	/**
	 * Creates an object of the HigherLowerGame class called game
	 */
	HigherLowerGame game = new HigherLowerGame();
	
	/**
	 * Variables
	 */
	private JPanel gamePanel;
	private JLabel lblTitle, lblCard1, lblCard2, lblOutcome, lblPrevious, lblOutcomeResult, lblWon, lblWonValue,
			lblLost, lblLostValue, lblTotal, lblTotalValue, lblStreak, lblStreakValue, lblHighestStreak,
			lblHighestStreakValue, lblBet, lblMoney, lblMoneyValue;
	private JFrame gameFrame;
	private JButton btnBack, btnExit, btnLower, btnHigher;
	private JTextField betAmount;
	
	/**
	 * Class constructor - calls the methods that create the window, fields and
	 * buttons.
	 * 
	 */
	public HigherOrLowerGUI() {

		createPlayForm();
		addPlayFields();
		addPlayButtons();

		gameFrame.add(gamePanel);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);

	}

	/**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
	public void createPlayForm() {

		gameFrame = new JFrame();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setTitle("Higher or Lower - Lets Play!");
		gameFrame.setResizable(false);
		gameFrame.setSize(550, 550);
		
		/**
		 * Creates an object of the Image class called image
		 * The paintComponent method of the JPanel class is override and a Graphics object is created
		 * The graphics object is used to access the drawImage method of the Graphics class, which allows us to set a background image.
		 * 
		 */
		Image image = requestImage();
		gamePanel = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		gamePanel.setLayout(null);

	}
	/**
	 * 
	 * @return image - the image location is returned.
	 */
	private Image requestImage() {
		Image image = null;

		try {
			image = ImageIO.read(new File("poker.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	/**
	 * Creates the buttons(Register, Back) and adds them to the JPanel
	 * 
	 */
	public void addPlayButtons() {
		
		/**
		 * Lower Button
		 */
		btnLower = new JButton("Lower");
		btnLower.setBounds(15, 120, 150, 50);
		btnLower.addActionListener(new LowerHandler());
		gamePanel.add(btnLower);
		
		/**
		 * Higher Button
		 */
		btnHigher = new JButton("Higher");
		btnHigher.setBounds(380, 120, 150, 50);
		btnHigher.addActionListener(new HigherHandler());
		gamePanel.add(btnHigher);
		
		/**
		 * Back Button
		 */
		btnBack = new JButton("Back");
		btnBack.setBounds(100, 450, 150, 50);
		btnBack.addActionListener(new BackHandler());
		gamePanel.add(btnBack);
		
		/**
		 * Exit Button
		 */
		btnExit = new JButton("Exit");
		btnExit.setBounds(300, 450, 150, 50);
		btnExit.addActionListener(new ExitHandler());
		gamePanel.add(btnExit);

	}

	/**
	 * Creates the fields and adds them to the JPanel
	 * 
	 */
	public void addPlayFields() {
		
		/**
		 * Title label
		 */
		lblTitle = new JLabel("Let's Play Higher or Lower!");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("Seriff", Font.BOLD, 30));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(0, 25, 550, 50);
		gamePanel.add(lblTitle);
		
		/**
		 * Card1 label - sets the value of this card to a random number between 1 and 21
		 */
		lblCard1 = new JLabel("" + game.Roll());
		lblCard1.setHorizontalAlignment(JLabel.CENTER);
		lblCard1.setFont(new Font("Seriff", Font.BOLD, 50));
		lblCard1.setForeground(Color.YELLOW);
		lblCard1.setBounds(200, 120, 150, 50);
		gamePanel.add(lblCard1);
		
		/**
		 * Bet Label
		 */
		lblBet = new JLabel("Bet: ");
		lblBet.setHorizontalAlignment(JLabel.CENTER);
		lblBet.setFont(new Font("Seriff", Font.BOLD, 20));
		lblBet.setForeground(Color.YELLOW);
		lblBet.setBounds(75, 190, 150, 50);
		gamePanel.add(lblBet);
		
		/**
		 * Bet JTextField
		 */
		betAmount = new JTextField();
		betAmount.setBounds(175, 202, 60, 25);
		gamePanel.add(betAmount);
		
		/**
		 * Money JLabel
		 */
		lblMoney = new JLabel("Money: ");
		lblMoney.setHorizontalAlignment(JLabel.CENTER);
		lblMoney.setFont(new Font("Seriff", Font.BOLD, 20));
		lblMoney.setForeground(Color.YELLOW);
		lblMoney.setBounds(280, 190, 150, 50);
		gamePanel.add(lblMoney);
		
		/**
		 * How much money the user has
		 */
		lblMoneyValue = new JLabel(Integer.toString(game.setMoney()));
		lblMoneyValue.setHorizontalAlignment(JLabel.CENTER);
		lblMoneyValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblMoneyValue.setForeground(Color.YELLOW);
		lblMoneyValue.setBounds(350, 190, 150, 50);
		gamePanel.add(lblMoneyValue);
		
		/**
		 * Only when the user has played once will the first card become the previous card, and a new card be displayed
		 */
		lblCard2 = new JLabel("N/A");
		lblCard2.setHorizontalAlignment(JLabel.CENTER);
		lblCard2.setFont(new Font("Seriff", Font.BOLD, 20));
		lblCard2.setForeground(Color.WHITE);
		lblCard2.setBounds(80, 250, 150, 25);
		gamePanel.add(lblCard2);
		
		/**
		 * Previous JLabel
		 */
		lblPrevious = new JLabel("Previous: ");
		lblPrevious.setHorizontalAlignment(JLabel.CENTER);
		lblPrevious.setFont(new Font("Seriff", Font.BOLD, 20));
		lblPrevious.setForeground(Color.WHITE);
		lblPrevious.setBounds(11, 250, 150, 25);
		gamePanel.add(lblPrevious);
		
		/**
		 * Total Won JLabel
		 */
		lblWon = new JLabel("Total Won: ");
		lblWon.setHorizontalAlignment(JLabel.CENTER);
		lblWon.setFont(new Font("Seriff", Font.BOLD, 20));
		lblWon.setForeground(Color.WHITE);
		lblWon.setBounds(15, 300, 150, 25);
		gamePanel.add(lblWon);
		
		/**
		 * Total Won value - set at 0 at start of game
		 */
		lblWonValue = new JLabel("0");
		lblWonValue.setHorizontalAlignment(JLabel.CENTER);
		lblWonValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblWonValue.setForeground(Color.WHITE);
		lblWonValue.setBounds(90, 300, 150, 25);
		gamePanel.add(lblWonValue);
		
		/**
		 * Total Lost JLabel
		 */
		lblLost = new JLabel("Total Lost : ");
		lblLost.setHorizontalAlignment(JLabel.CENTER);
		lblLost.setFont(new Font("Seriff", Font.BOLD, 20));
		lblLost.setForeground(Color.WHITE);
		lblLost.setBounds(295, 300, 150, 25);
		gamePanel.add(lblLost);
		
		/**
		 * Total lost value - set at 0 at start of game
		 */
		lblLostValue = new JLabel("0");
		lblLostValue.setHorizontalAlignment(JLabel.CENTER);
		lblLostValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblLostValue.setForeground(Color.WHITE);
		lblLostValue.setBounds(390, 300, 150, 25);
		gamePanel.add(lblLostValue);
		
		/**
		 * Total played JLabel
		 */
		lblTotal = new JLabel("Total Played: ");
		lblTotal.setHorizontalAlignment(JLabel.CENTER);
		lblTotal.setFont(new Font("Seriff", Font.BOLD, 20));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBounds(25, 350, 150, 25);
		gamePanel.add(lblTotal);
		
		/**
		 * How many games played
		 */
		lblTotalValue = new JLabel("0");
		lblTotalValue.setHorizontalAlignment(JLabel.CENTER);
		lblTotalValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblTotalValue.setForeground(Color.WHITE);
		lblTotalValue.setBounds(95, 350, 150, 25);
		gamePanel.add(lblTotalValue);
		
		/**
		 * Win streak JLabel
		 */
		lblStreak = new JLabel("Win Streak : ");
		lblStreak.setHorizontalAlignment(JLabel.CENTER);
		lblStreak.setFont(new Font("Seriff", Font.BOLD, 20));
		lblStreak.setForeground(Color.WHITE);
		lblStreak.setBounds(300, 350, 150, 25);
		gamePanel.add(lblStreak);
		
		/**
		 * Value of win streak, set at 0 at beginning
		 */
		lblStreakValue = new JLabel("0 ");
		lblStreakValue.setHorizontalAlignment(JLabel.CENTER);
		lblStreakValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblStreakValue.setForeground(Color.WHITE);
		lblStreakValue.setBounds(390, 350, 150, 25);
		gamePanel.add(lblStreakValue);
		
		/**
		 * Outcome JLabel
		 */
		lblOutcome = new JLabel("Result: ");
		lblOutcome.setHorizontalAlignment(JLabel.CENTER);
		lblOutcome.setFont(new Font("Seriff", Font.BOLD, 20));
		lblOutcome.setForeground(Color.WHITE);
		lblOutcome.setBounds(300, 250, 100, 25);
		gamePanel.add(lblOutcome);
		
		/**
		 * Outcome set as N/A at start, won't know until at least 1 game is played
		 */
		lblOutcomeResult = new JLabel("N/A");
		lblOutcomeResult.setHorizontalAlignment(JLabel.CENTER);
		lblOutcomeResult.setFont(new Font("Seriff", Font.BOLD, 20));
		lblOutcomeResult.setForeground(Color.WHITE);
		lblOutcomeResult.setBounds(410, 250, 100, 25);
		gamePanel.add(lblOutcomeResult);
		
		/**
		 * Max streak JLabel;
		 */
		lblHighestStreak = new JLabel("MAX STREAK: ");
		lblHighestStreak.setFont(new Font("Seriff", Font.BOLD, 20));
		lblHighestStreak.setForeground(Color.PINK);
		lblHighestStreak.setBounds(190, 370, 200, 75);
		gamePanel.add(lblHighestStreak);
		
		/**
		 * Max streak set to 0 at start
		 */
		lblHighestStreakValue = new JLabel("0");
		lblHighestStreakValue.setFont(new Font("Seriff", Font.BOLD, 20));
		lblHighestStreakValue.setForeground(Color.PINK);
		lblHighestStreakValue.setBounds(340, 370, 200, 75);
		gamePanel.add(lblHighestStreakValue);

	}
	/**
	 * Returns to the MenuGUI
	 * @author s4923268
	 *
	 */
	class BackHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			gameFrame.dispose();
			new MenuGUI();
			game.infoBox("Thanks for playing! Your highest streak is: " + game.setMaxStreak()
					+ " and your total money is " + game.setMoney());
		}

	}
	/**
	 * Exits the program
	 * @author s4923268
	 *
	 */
	class ExitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			game.infoBox("Thanks for playing! Your highest streak is: " + game.setMaxStreak()
			+ " and your total money is " + game.setMoney());
			System.exit(1);
		}

	}
	/**
	 * Handles what happens when the Higher button is clicked. 
	 * 
	 * @author s4923268
	 *
	 */
	class HigherHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			/**
			 * sets the value of the JTextField to a string
			 */
			String bet = betAmount.getText();
			try {
				/**
				 * Handles what happens if the value is empty, 0 or is greater than how much money they have
				 */
				if (bet.isEmpty() || Integer.parseInt(bet) == 0) {
					JOptionPane.showMessageDialog(gameFrame, "Please input a valid bet amount", null,
							JOptionPane.ERROR_MESSAGE);
				} else if (Integer.parseInt(bet) > game.setMoney()) {
					JOptionPane.showMessageDialog(gameFrame, "You can't bet more than you have", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					/**
					 * Updates counters and values depending on result of game
					 */
					game.betValue(Integer.parseInt(bet));
					lblCard2.setText(String.valueOf(game.getRoll()));
					game.Roll();
					lblCard1.setText(String.valueOf(game.Roll()));
					lblOutcomeResult.setText(String.valueOf(game.higherResult()));
					lblTotalValue.setText(Integer.toString(game.setTotalCounter()));
					lblWonValue.setText(Integer.toString(game.setWinCounter()));
					lblLostValue.setText(Integer.toString(game.setLossCounter()));
					lblStreakValue.setText(Integer.toString(game.setStreakCounter()));
					lblHighestStreakValue.setText(Integer.toString(game.setMaxStreak()));
					game.setMoney();
					lblMoneyValue.setText(String.valueOf(game.setMoney()));
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(gameFrame, "You can only enter a valid number!", null,
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	class LowerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			/**
			 * sets the value of the JTextField to a string
			 */
			String bet = betAmount.getText();
			try {
				/**
				 * Handles what happens if the value is empty, 0 or is greater than how much money they have
				 */
				if (bet.isEmpty() || Integer.parseInt(bet) == 0) {
					JOptionPane.showMessageDialog(gameFrame, "Please input a valid bet amount", null,
							JOptionPane.ERROR_MESSAGE);
				} else if (Integer.parseInt(bet) > game.setMoney()) {
					JOptionPane.showMessageDialog(gameFrame, "You can't bet more than you have", null,
							JOptionPane.ERROR_MESSAGE);
				} else {
					/**
					 * Updates counters and values depending on result of game
					 */
					game.betValue(Integer.parseInt(bet));
					lblCard2.setText(String.valueOf(game.getRoll()));
					game.Roll();
					lblCard1.setText(String.valueOf(game.Roll()));
					lblOutcomeResult.setText(String.valueOf(game.lowerResult()));
					lblTotalValue.setText(Integer.toString(game.setTotalCounter()));
					lblWonValue.setText(Integer.toString(game.setWinCounter()));
					lblLostValue.setText(Integer.toString(game.setLossCounter()));
					lblStreakValue.setText(Integer.toString(game.setStreakCounter()));
					lblHighestStreakValue.setText(Integer.toString(game.setMaxStreak()));
					game.setMoney();
					lblMoneyValue.setText(String.valueOf(game.setMoney()));
				}
			} catch (NumberFormatException e) {
				/**
				 * If the user doesnt input a number into the bet JTextField, an error is thrown and they are informed via a JOptionPane.
				 */
				JOptionPane.showMessageDialog(gameFrame, "You can only enter a valid number!", null,
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
