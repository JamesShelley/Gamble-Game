import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * When the user clicks register at the WelcomeGUI class, this class
 * is loaded. This class contains the registration form for the user to fill in
 * so they can log in and play the game
 *
 * 
 * @author James Shelley s4923268
 * @version 1
 * 
 * 
 */

public class RegisterGUI extends PasswordHash {

	/**
	 * VARIABLES Creates the JLabels, JButtons, JPanel and JFrame
	 * 
	 */

	private JLabel lblTitle, lblLogin, lblPassword;
	private JTextField registerLogin;
	private JPasswordField registerPassword;
	private JButton btnRegisterSubmit, btnBack;
	private JFrame registerFrame;
	private JPanel registerPanel;

	/**
	 * Class constructor - calls the methods that create the window, fields and
	 * buttons.
	 * 
	 */

	public RegisterGUI() {
		// CREATES THE FORM AND ADDS THE FIELDS AND BUTTONS
		createForm();
		addFields();
		addButtons();

		// THE FRAME IS ADDED TO THE PANEL AND CENTERED TO THE SCREEN
		registerFrame.add(registerPanel);
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setVisible(true);

	}

	/**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
	public void createForm() {
		registerFrame = new JFrame();
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.setResizable(false);
		registerFrame.setSize(400, 350);
		registerFrame.setTitle("Higher or Lower - Registration");

		registerPanel = new JPanel();
		registerPanel.setLayout(null);

	}

	/**
	 * Creates the fields and adds them to the JPanel
	 * 
	 */

	public void addFields() {
		
		/**
		 * Title Label
		 */
		lblTitle = new JLabel("Registration Form");
		lblTitle.setFont(new Font("Seriff", Font.BOLD, 20));
		lblTitle.setBounds(110, 25, 300, 50);
		registerPanel.add(lblTitle);
		
		/**
		 * Login label
		 */
		lblLogin = new JLabel("Username: ");
		lblLogin.setBounds(55, 100, 100, 50);
		registerPanel.add(lblLogin);
		
		/**
		 * User input - Login JTextField
		 */
		registerLogin = new JTextField("Please enter a username");
		registerLogin.setToolTipText("This is case sensitive!");
		registerLogin.setBounds(130, 110, 150, 25);
		registerPanel.add(registerLogin);
		
		/**
		 * Password JLabel
		 */
		lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(55, 150, 100, 50);
		registerPanel.add(lblPassword);
		
		/**
		 * User input: Password JPasswordField
		 */
		registerPassword = new JPasswordField();
		registerPassword.setToolTipText("This is case sensitive!");
		registerPassword.setBounds(130, 160, 150, 25);
		registerPanel.add(registerPassword);
	}

	/**
	 * Creates the buttons(Register, Back) and adds them to the JPanel
	 * 
	 */

	public void addButtons() {
		/**
		 * Register Button
		 */
		btnRegisterSubmit = new JButton("Submit");
		btnRegisterSubmit.setBounds(230, 230, 150, 50);
		btnRegisterSubmit.addActionListener(new RegisterHandler());
		registerPanel.add(btnRegisterSubmit);
		/**
		 * Back Button
		 */
		btnBack = new JButton("Back");
		btnBack.setBounds(50, 230, 150, 50);
		btnBack.addActionListener(new BackHandler());
		registerPanel.add(btnBack);

	}

	/**
	 * Creates a JOptionPane that outputs a message about account details when
	 * the method is called.
	 * 
	 * @param infoMessage
	 *            Message that is outputted in the JOptionPane
	 */

	public void accountRegistered(String infoMessage) {
		JOptionPane.showMessageDialog(null, infoMessage, "System Message - Account created",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 * @param filename
	 *            The name of the file
	 * @param account_info
	 *            The contents of each line in the filename
	 */

	public void saveUser(String filename, String account_info) {
		try {
			/**
			 * Creates the write and buffered writer
			 * 
			 */
			FileWriter writer = new FileWriter(filename, true);
			BufferedWriter bwriter = new BufferedWriter(writer);
			bwriter.write(account_info);
			bwriter.newLine();
			bwriter.flush();
			bwriter.close();
			accountRegistered("Account successfully created!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles what happens when the Register button is clicked
	 * 
	 * @author James Shelley - s4923268
	 *
	 */
	class RegisterHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			/**
			 * String username is set equal to what is typed in the username JTextField
			 * String password is set equal to what is typed in the password JPasswordField
			 * String user_login concatenates the username and password with a "-" inbetween (to improve readability)
			 * A series of checks is performed to check if the fields contain valid values
			 * If all is correct, the user_login string is written to user_information.txt
			 */
			
			String username = registerLogin.getText();
			String password = new String(registerPassword.getPassword());
			password = getSha256(password);	
			String user_login = username + "-" + password;
			if (username.isEmpty() && !password.isEmpty()) {
				accountRegistered("Username field is empty");
			} else if (password.isEmpty() && !username.isEmpty()) {
				accountRegistered("Password field is empty");
			} else if (username.isEmpty() && password.isEmpty()) {
				accountRegistered("Username and Password field are empty");
			} else {
				saveUser("user_information.txt", user_login);
				registerFrame.dispose();
				new LoginGUI();
			}

		}

	}

	class BackHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			registerFrame.dispose();
			new WelcomeGUI();
		}

	}
}
