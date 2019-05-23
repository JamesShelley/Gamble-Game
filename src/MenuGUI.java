import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This class contains the menu system for the game. From here they can choose to load the actual game, load the instructions for the game or exit the game.
 * 
 * @author James Shelley - s4923268
 * @version 1
 */
public class MenuGUI {

	/**
	 * Creates an object that can access the LoginGUI class
	 * 
	 */
	LoginGUI login;

	/**
	 * JLabels, Buttons, JFrame and JPanel are created
	 * 
	 */
	private JLabel lblTitle, lblWelcome, lblUser;
	private JButton btnPlay, btnExit, btnInstructions;
	private JPanel menuPanel;
	private JFrame menuFrame;
	
	/**
	 * Class constructor - calls the methods that create the window, fields and
	 * buttons.
	 * 
	 */
	public MenuGUI() {
		// CREATES THE FORM AND ADDS THE FIELDS AND BUTTONS
		createForm();
		addFields();
		addButtons();

		// THE FRAME IS ADDED TO THE PANEL AND CENTERED TO THE SCREEN
		menuFrame.add(menuPanel);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setVisible(true);

	}

	/**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
	public void createForm() {

		menuFrame = new JFrame();
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setResizable(false);
		menuFrame.setSize(550, 550);
		menuFrame.setTitle("Higher or Lower - Menu");
		
		/**
		 * Creates an object of the Image class called image
		 * The paintComponent method of the JPanel class is override and a Graphics object is created
		 * The graphics object is used to access the drawImage method of the Graphics class, which allows us to set a background image.
		 * 
		 */
		
		Image image = requestImage();
		menuPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		menuPanel.setLayout(null);

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
	 * Creates the fields and adds them to the JPanel
	 * 
	 */
	public void addFields() {
		
		/**
		 * The Menu Label
		 */
		lblTitle = new JLabel("Menu");
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("Seriff", Font.BOLD, 20));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(0, 25, 550, 50);
		menuPanel.add(lblTitle);
		
		/**
		 * The welcome label
		 */
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Seriff", Font.BOLD, 20));
		lblWelcome.setForeground(Color.YELLOW);
		lblWelcome.setBounds(250, 40, 100, 50);
		menuPanel.add(lblTitle);


	}

	/**
	 * Creates the buttons(Register, Back) and adds them to the JPanel
	 * 
	 */
	public void addButtons() {
		
		/**
		 * Play Button		
		 */
		btnPlay = new JButton("Play Now");
		btnPlay.setBounds(195, 100, 150, 50);
		btnPlay.setBorderPainted(false);
		btnPlay.setFocusPainted(false);
		btnPlay.addActionListener(new PlayHandler());
		menuPanel.add(btnPlay);
		
		/**
		 * Instructions Button
		 */
		btnInstructions = new JButton("Instructions");
		btnInstructions.setBounds(195, 210, 150, 50);
		btnInstructions.addActionListener(new InstructionsHandler());
		menuPanel.add(btnInstructions);
		
		/**
		 * Exit Button
		 */
		btnExit = new JButton("Exit");
		btnExit.setBounds(195, 310, 150, 50);
		btnExit.addActionListener(new ExitHandler());
		menuPanel.add(btnExit);
	}

	// BUTTON HANDLERS
	
	/**
	 * When the play button is clicked, the MenuGui frame is disposed of and the HigherOrLowerGUI opens.
	 * @author James Shelley - s4923268
	 *
	 */

	class PlayHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			menuFrame.dispose();
			new HigherOrLowerGUI();

		}

	}
	
	/**
	 * When the Instructions button is clicked, the MenuGui frame is disposed of and the InstructionsGUI opens.
	 * @author James Shelley - s4923268
	 *
	 */
	
	class InstructionsHandler implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent event) {
			menuFrame.dispose();
			new InstructionsGUI();
		}

	}
	
	/**
	 * When the Exit button is clicked the program exits
	 * @author James Shelley - s4923268
	 *
	 */
	
	class ExitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(1);
		}

	}

}