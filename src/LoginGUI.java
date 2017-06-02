import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * This class contains the login form so the user can login in and play the
 * game. They must use the details they inputted on the RegisterGUI.
 * 
 * @author James Shelley - s4923268
 * @version 1
 */
public class LoginGUI {

	private JLabel lblTitle, lblLogin, lblPassword;
	private JTextField userLogin;
	private JPasswordField userPassword;
	private JButton btnLoginSubmit, btnBack;
	private JFrame loginFrame;
	private JPanel loginPanel;

	/**
	 * Class constructor - calls the methods that create the window, fields and
	 * buttons.
	 * 
	 */
	public LoginGUI() {
		//calls the createForm, addFields and addButtons methods.
		createForm();
		addFields();
		addButtons();

		loginFrame.add(loginPanel);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);

	}

	/**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
	public void createForm() {
		loginFrame = new JFrame();
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setResizable(false);
		loginFrame.setSize(400, 350);
		loginFrame.setTitle("Higher or Lower - Login");

		loginPanel = new JPanel();
		loginPanel.setLayout(null);

	}

	/**
	 * Creates the fields and adds them to the JPanel
	 * 
	 */

	public void addFields() {

		lblTitle = new JLabel("Login Form");
		lblTitle.setFont(new Font("Seriff", Font.BOLD, 20));
		lblTitle.setBounds(150, 25, 300, 50);
		loginPanel.add(lblTitle);

		lblLogin = new JLabel("Username: ");
		lblLogin.setBounds(55, 100, 100, 50);
		loginPanel.add(lblLogin);

		userLogin = new JTextField("Enter your username");
		userLogin.setToolTipText("This is case sensitive!");
		userLogin.setBounds(130, 110, 150, 25);
		loginPanel.add(userLogin);

		lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(55, 150, 100, 50);
		loginPanel.add(lblPassword);

		userPassword = new JPasswordField();
		userPassword.setToolTipText("This is case sensitive!");
		userPassword.setBounds(130, 160, 150, 25);
		loginPanel.add(userPassword);
	}

	/**
	 * Creates the buttons(Register, Back) and adds them to the JPanel
	 * 
	 */
	public void addButtons() {
		btnLoginSubmit = new JButton("Submit");
		btnLoginSubmit.setBounds(230, 230, 150, 50);
		btnLoginSubmit.addActionListener(new LoginHandler());
		loginPanel.add(btnLoginSubmit);

		btnBack = new JButton("Back");
		btnBack.setBounds(50, 230, 150, 50);
		btnBack.addActionListener(new BackHandler());
		loginPanel.add(btnBack);

	}

	/**
	 * Displays a JOptionPane with a message. This message is called when the
	 * user tries it login.
	 * 
	 * @param infoMessage
	 *            The message that is displayed
	 */
	public void accountLogin(String infoMessage) {
		JOptionPane.showMessageDialog(null, infoMessage, "System Message - Login Attempt",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 
	 * 
	 * @param filename
	 *            The path to the filename that contains the user information
	 * @param username
	 *            The value the user types in the username login JTextField
	 * @param password
	 *            The value the user types in the password login JTextField
	 */
	public void validateUser(String filename, String username, String password) {
		/**
		 * Creates an object of the file reader and Buffered Reader
		 * 
		 */
		FileReader reader;
		BufferedReader breader;
		String account_info;

		try {
			/**
			 * Pointing the file reader at the filename Inputting the reader
			 * into the bufferedreader
			 */
			reader = new FileReader(filename);
			breader = new BufferedReader(reader);
			/**
			 * Loops through the file reading every line until it reaches an
			 * empty line. (!=null)
			 */
			while ((account_info = breader.readLine()) != null) {
				if (check(account_info, username, password)) {
					accountLogin("Account successfully logged in!");
					loginFrame.dispose();
					new MenuGUI();
					break;
				} else {
					// accountLogin("Invalid Details");
					// break;
				}
			}
			/**
			 * Closes the reader and bufferedreader
			 */
			reader.close();
			breader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the username and password the user inputted is in the
	 * user_information text file. The array info[] is used to validate logins.
	 * The variable uname is set equal to the first index of the array(the
	 * username) and the variable pass is set equal to the second index of the
	 * array (the password). If these match then the user can login.
	 *
	 * 
	 * @param accinfo
	 *            - what is on the current line (read by the BufferedReader)
	 * @param name
	 *            - what the user typed into the JTextField
	 * @param userpass
	 *            - what the user typed into the JPasswordField
	 * @return
	 */
	public boolean check(String accinfo, String name, String userpass) {
		String[] info = accinfo.split("-"); // Means that the "-" in the
											// filename is ignored when the
											// array assigns the username an
											// index of 0 and the password an
											// index of 1.
		String user = info[0];
		String pass = info[1];
		if (user.equals(name) && pass.equals(userpass)) {
			return true;
		} else {
			accountLogin("Invalid Details");
			return false;
		}

	}

	/**
	 * Handles what happens when the user clicks the login button. If any of the
	 * textfields are empty, the user can't login and is prompted to fill out
	 * the username and password form. If the user has inputted values into the
	 * text fields, these values are then checked with the validateUser method.
	 * 
	 * @author James Shelley - s4923268
	 *
	 */

	class LoginHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String username = userLogin.getText();
			String password = new String(userPassword.getPassword());
			if (username.isEmpty() && !password.isEmpty()) {
				accountLogin("Username field is empty");
			} else if (password.isEmpty() && !username.isEmpty()) {
				accountLogin("Password field is empty");
			} else if (username.isEmpty() && password.isEmpty()) {
				accountLogin("Username and Password field are empty");
			} else {
				validateUser("user_information.txt", username, password);
			}

		}

	}

	class BackHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			loginFrame.dispose();
			new WelcomeGUI();
		}

	}

}
