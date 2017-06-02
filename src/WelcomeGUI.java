import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * This class is the first GUI the user see's when running the program. It
 * prompts the user to either Register or Login.
 * 
 * @author James Shelley s4923268
 * @version 1
 * 
 */

public class WelcomeGUI {

	/**
	 * VARIABLES Creates the JLabels, JButtons, JPanels and JFrames
	 * 
	 */

	private JLabel lblTitle, lblWelcome;
	private JButton btnRegister, btnLogin, btnExit;
	private JPanel welcomePanel;
	private JFrame welcomeFrame;

	/**
	 * Main method - Runs the program by calling the class constructor -
	 * WelcomeGUI
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new WelcomeGUI();
	}

	/**
	 * Class constructor - calls the methods that create the window, fields
	 * and buttons.
	 * 
	 */
	public WelcomeGUI() {
		// CREATES THE FORM AND ADDS THE FIELDS AND BUTTONS
		createForm();
		addFields();
		addButtons();

		// THE FRAME IS ADDED TO THE PANEL AND CENTERED TO THE SCREEN
		welcomeFrame.add(welcomePanel);
		welcomeFrame.setLocationRelativeTo(null);
		welcomeFrame.setVisible(true);

	}

	/**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
	public void createForm() {
		/**
		 * Creates the JFrame
		 */
		welcomeFrame = new JFrame();
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeFrame.setResizable(false);
		welcomeFrame.setSize(400, 400);
		welcomeFrame.setTitle("Higher or Lower - Welcome!");
		
		/**
		 * Creates the JPanel
		 */
		welcomePanel = new JPanel();
		welcomePanel.setLayout(null);
	}

	/**
	 * Creates the fields (Title and Welcome Label) and adds them to the JPanel
	 * 
	 * 
	 */
	public void addFields() {
		/**
		 * Title label
		 */
		lblTitle = new JLabel("Welcome to Higher or Lower! By James Shelley");
		lblTitle.setFont(new Font("Seriff", Font.BOLD, 12));
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setBounds(55, 10, 400, 50);
		welcomePanel.add(lblTitle);
		
		/**
		 * Welcome Label
		 */
		lblWelcome = new JLabel("Please Register if it is your first time playing or login!");
		lblWelcome.setFont(new Font("Seriff", Font.BOLD, 12));
		lblWelcome.setForeground(Color.RED);
		lblWelcome.setBounds(50, 40, 400, 50);
		welcomePanel.add(lblWelcome);

	}

	/**
	 * Creates the buttons(Register, Login, Exit) and adds them to the JPanel
	 * 
	 */
	public void addButtons() {
		
		/**
		 * Register Button
		 */
		btnRegister = new JButton("Register");
		btnRegister.setBounds(130, 100, 150, 50);
		btnRegister.addActionListener(new RegisterHandler());
		welcomePanel.add(btnRegister);
		
		/**
		 * Login Button
		 */
		btnLogin = new JButton("Login");
		btnLogin.setBounds(130, 200, 150, 50);
		btnLogin.addActionListener(new LoginHandler());
		welcomePanel.add(btnLogin);
		
		/**
		 * Button exit
		 */
		btnExit = new JButton("Exit");
		btnExit.setBounds(130, 300, 150, 50);
		btnExit.addActionListener(new ExitHandler());
		welcomePanel.add(btnExit);
	}

	/**
	 * RegisterHandler - handles what happens when the RegisterButton is clicked
	 * (the RegisterGUI class is loaded)
	 * 
	 * @author JamesShelley s4923268
	 *
	 */

	class RegisterHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			welcomeFrame.dispose();
			new RegisterGUI();

		}

	}

	/**
	 * LoginHandler - handles what happens when the Login Button is clicked (the
	 * WelcomeGUI class is disposed and the LoginGUI class is loaded)
	 * 
	 * @author JamesShelley s4923268
	 *
	 */

	class LoginHandler implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent event) {
			welcomeFrame.dispose();
			new LoginGUI();
		}

	}

	/**
	 * ExitHandler - handles what happens when the exit button is clicked (the
	 * program exits)
	 * 
	 * @author JamesShelley s4923268
	 *
	 */

	class ExitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(1);
		}

	}

}