import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class contains instructions for the user to read on how to play the game.
 * 
 * 
 *@author James Shelley - s4923268
 *@version 1
 */
public class InstructionsGUI {
	/**
	 * Declares the labels,buttons panel and frame variables.
	 */
    private JLabel lblInstructionsTitle, lblInstructionsInfo;
    private JButton btnBack, btnPlay, btnExit;
    private JPanel instructionsPanel;
    private JFrame instructionsFrame;
    
    /**
	 * Class constructor - calls the methods that create the window, fields and
	 * buttons.
	 * 
	 */
    public InstructionsGUI() {

        createForm();
        addFields();
        addButtons();

        instructionsFrame.add(instructionsPanel);
        instructionsFrame.setLocationRelativeTo(null);
        instructionsFrame.setVisible(true);

    }
    /**
	 * Creates the buttons(Register, Back) and adds them to the JPanel
	 * 
	 */
    private void addButtons() {
    	
    	/**
		 * Exit Button
		 */
        btnExit = new JButton("Exit");
        btnExit.setBounds(100, 400, 100, 50);
        btnExit.addActionListener(new ExitHandler());
        instructionsPanel.add(btnExit);
        
        /**
		 * Back Button
		 */
        btnBack = new JButton("Back");
        btnBack.setBounds(225, 400, 100, 50);
        btnBack.addActionListener(new BackHandler());
        instructionsPanel.add(btnBack);

        /**
		 * Play Button
		 */
        btnPlay = new JButton("Lets Play!");
        btnPlay.setBounds(350, 400, 100, 50);
        btnPlay.addActionListener(new PlayHandler());
        instructionsPanel.add(btnPlay);

    }
	/**
	 * Creates the fields and adds them to the JPanel
	 * 
	 */
    private void addFields() {
    	
    	/**
		 * Title lable
		 */
        lblInstructionsTitle = new JLabel("Instructions");
        lblInstructionsTitle.setHorizontalAlignment(JLabel.CENTER);
        lblInstructionsTitle.setFont(new Font("Seriff", Font.BOLD, 20));
        lblInstructionsTitle.setForeground(Color.YELLOW);
        lblInstructionsTitle.setBounds(0, 25, 550, 50);
        instructionsPanel.add(lblInstructionsTitle);
        
        /**
		 * Label information
		 */
        lblInstructionsInfo = new JLabel();
        lblInstructionsInfo.setText("<html><li>The aim of the game is to get the highest win streak and most money as possible!<br></li> <li>You start off with 1000 coins.  <br></li> <li>Choose the amount you would like to bet! <br></li> <li> Click the higher or lower button (Result between 1 - 21).<br><li>Have fun - make as much money as you can!</li> </html>");
        lblInstructionsInfo.setHorizontalAlignment(JLabel.CENTER);
        lblInstructionsInfo.setBorder(BorderFactory.createLoweredBevelBorder());
        lblInstructionsInfo.setFont(new Font("Seriff", Font.BOLD, 20));
        lblInstructionsInfo.setForeground(Color.WHITE);
        lblInstructionsInfo.setBounds(0, 70, 550, 300);
        instructionsPanel.add(lblInstructionsInfo);

    }
    
    /**
	 * Creates the window that the user sees - a JFrame is created and a JPanel
	 * is added to it. Any fields and forms will be added to the window later.
	 * 
	 */
    public void createForm() {

        instructionsFrame = new JFrame();
        instructionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        instructionsFrame.setResizable(false);
        instructionsFrame.setTitle("Higher or Lower - Instructions");
        instructionsFrame.setSize(550, 550);
        
        /**
		 * Creates an object of the Image class called image
		 * The paintComponent method of the JPanel class is override and a Graphics object is created
		 * The graphics object is used to access the drawImage method of the Graphics class, which allows us to set a background image.
		 * 
		 */
        Image image = requestImage();
        instructionsPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
        instructionsPanel.setLayout(null);
        instructionsPanel.setBackground(Color.DARK_GRAY);

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
	 * When the back button is clicked, the InstructionsGui frame is disposed of and the MenuGUI opens.
	 * @author James Shelley - s4923268
	 *
	 */
    class BackHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            instructionsFrame.dispose();
            new MenuGUI();
        }

    }
    
    /**
	 * When the play button is clicked, the InstructionsGui frame is disposed of and the HigherOrLowerGUI opens.
	 * @author James Shelley - s4923268
	 *
	 */
    class PlayHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            instructionsFrame.dispose();
            new HigherOrLowerGUI();

        }

    }
    /**
	 * When the exit button is clicked, the program exits.
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
