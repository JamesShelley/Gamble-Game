import java.util.Random;
import javax.swing.JOptionPane;
/**
 * This class contains the code logic for generating new cards, changing the counter values, betting etc.
 * 
 * @author James Shelley - s4923268
 * @version 1
 */
public class HigherLowerGame {
	/**
	 * Creates an object of the rand class so a random card can be generated whenever the user clicks higher or lower
	 */
	Random rand = new Random();
	
	/**
	 * The variables that are used
	 */
	
	private int originalCard;
	private int nextCard;
	private int winCounter = 0;
	private int lossCounter = 0;
	private int totalCounter = 0;
	private int streakCounter = 0;
	private int maxStreak = 0;
	private int money = 1000;
	private int userBet;
	
	/**
	 * This method generates a random value for the card
	 * 
	 * @return A random card value
	 */
	public int Roll() {
		originalCard = rand.nextInt(21) + 1;
		return originalCard;

	}
	
	/**
	 * Sets the value of the card to the previous card and a new card is then generated
	 * 
	 * @return the previous card
	 */
	public int getRoll() {
		nextCard = originalCard;
		return originalCard;
	}
	
	/**
	 * Checks if the current streak is bigger than the current max streak, if it is the maxStreak value is updated.
	 */
	public void maxStreakResult() {
		if (streakCounter > maxStreak) {
			maxStreak = streakCounter;
		}
	}
	
	/**
	 * 
	 * @param betValue
	 */
	public void betValue(int betValue) {
		userBet = betValue;
	}
	
	/**
	 * Checks if the user wins or loses when the higher button is pressed.
	 * If the user guessed correctly -
	 * Win counter, total counter and win streak increase by 1
	 * 
	 * If the user guessed incorrectly -
	 * Loss counter and total counter increase by 1, and the win streak resets.
	 * 
	 * @return "You win" if the user wins, "You lose" if the user loses.
	 */
	public String higherResult() {
		if (nextCard < originalCard) {
			money = money + userBet;
			winCounter++;
			totalCounter++;
			streakCounter++;
			maxStreakResult();
			return "You win!";
		} else {
			money = money - userBet;
			lossCounter++;
			totalCounter++;
			streakCounter = 0;
			return "You lose!";
		}

	}
	
	/**
	 * Checks if the user wins or loses when the higher button is pressed.
	 * If the user guessed correctly -
	 * Win counter, total counter and win streak increase by 1
	 * 
	 * If the user guessed incorrectly -
	 * Loss counter and total counter increase by 1, and the win streak resets.
	 * 
	 * @return "You win" if the user wins, "You lose" if the user loses.
	 */
	public String lowerResult() {
		if (nextCard > originalCard) {
			money = money + userBet;
			winCounter++;
			totalCounter++;
			streakCounter++;
			maxStreakResult();
			return "You win!";
		} else {
			money = money - userBet;
			lossCounter++;
			totalCounter++;
			streakCounter = 0;
			return "You lose!";
		}

	}
	/**
	 * When the user exits the program, they are informed of their highest win streak and how much money they had.
	 * @param infoMessage
	 */
	
	public void infoBox(String infoMessage) {

		JOptionPane.showMessageDialog(null, infoMessage, "Highscore Notification", JOptionPane.INFORMATION_MESSAGE);

	}
	/**
	 * 
	 * @return The value of how much money the user has
	 */
	public int setMoney() {
		return money;
	}
	
	/**
	 * 
	 * @return The value of the win counter
	 */
	public int setWinCounter() {
		return winCounter;
	}
	
	/**
	 * 
	 * @return The value of the loss counter
	 */
	public int setLossCounter() {
		return lossCounter;
	}
	
	/**
	 * 
	 * @return The value of the total counter
	 */
	public int setTotalCounter() {
		return totalCounter;
	}
	/**
	 * 
	 * @return The value of the streak counter
	 */
	public int setStreakCounter() {
		return streakCounter;
	}
	
	/**
	 * 
	 * @return The value of the max streak counter
	 */
	public int setMaxStreak() {
		return maxStreak;
	}

}