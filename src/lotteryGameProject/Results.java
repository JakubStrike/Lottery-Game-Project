package lotteryGameProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * @author Jakub Stachura
 *@version 1.0
 */
public class Results {
	/**
	 * The main dialog frame
	 */
	protected JFrame frmResults;
	/**
	 * making object of a class MainMenu
	 */
	private MainMenu mainMenu = null;
	/**
	 * holding value of balance
	 */
	private int balance = 0;
	/**
	 * holding value of tickets purchased
	 */
	private int numberOfTicketsPurchased = 0;
	/**
	 * used for reseting number of tickets purchased
	 */
	private int numberOfTicketsPurchasedReset = 0;
	/**
	 * holding value for generated winning numbers
	 */
	private int[] winningNumbers = new int[6];
	/**
	 * first dimension hold (6 numbers picked) second dimension hold value in witch
	 * ticket has those 6 numbers been saved
	 */
	private int[][] numbersInTickets = new int[6][999];
	/**
	 * used for reseting number of numbersInTickets
	 */
	private int[][] numbersInTicketsReset = new int[6][999];
	/**
	 * holding value of how much user does win money
	 */
	private int wonAmmount = 0;
	/**
	 * holding value for money depend of how many numbers has been guessed
	 */
	private int[] winningPrice = { 5, 10, 100, 1000 };
	/**
	 * counting how many correct numbers have user guessed
	 */
	private int[] counterCorrectNumbers = new int[999];
	/**
	 * the text field displaying massage to user of how much he won money
	 */
	private JTextField txtYouHaveWon;
		
	JTextArea textArea = new JTextArea();

	/**
	 * Create the application.
	 */	
	public Results() {
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * 
	 * @param balance                  holding value of updated balance
	 * @param numberOfTicketsPurchased holding value of updated tickets purchased
	 * @param numbersInTickets         holding value of updated numbersInTickets
	 */
	public Results(int balance, int numberOfTicketsPurchased, int[][] numbersInTickets) {
		this.balance = balance;
		this.numberOfTicketsPurchased = numberOfTicketsPurchased;
		this.numbersInTickets = numbersInTickets;
		gui();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void gui() {
		frmResults = new JFrame();
		frmResults.setResizable(false);
		frmResults.setTitle("Results");
		frmResults.setSize(531, 344);
		frmResults.setLocationRelativeTo(null);
		frmResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResults.getContentPane().setLayout(null);

		String winningNumbersText = "Winning numbers are!\n";
		String numbersInTickets = "\nYour numbers are:\n";

		generateWinningNumbers();
		sortNumbersInOrder();
		sortNumbersInOrder2dArray();
		countWinningNumbers();
		checkWinningPrice();

		winningNumbersText = toStringWinningNumbers(winningNumbersText);
		numbersInTickets = toStringNumbersInTickets(numbersInTickets);

		@SuppressWarnings("rawtypes")
		JList list_1 = new JList();
		list_1.setBounds(317, 113, 95, -74);
		frmResults.getContentPane().add(list_1);

		textArea = new JTextArea();
		textArea.setBounds(172, 1, 512, 259);
		frmResults.getContentPane().add(textArea);

		JScrollPane scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(0, 0, 512, 259);
		frmResults.getContentPane().add(scrollPane_1);
		
		textArea.setText(winningNumbersText + numbersInTickets);

		txtYouHaveWon = new JTextField();
		txtYouHaveWon.setText("You have won: £" + wonAmmount);
		txtYouHaveWon.setHorizontalAlignment(SwingConstants.LEFT);
		txtYouHaveWon.setEditable(false);
		txtYouHaveWon.setBounds(0, 260, 510, 20);
		frmResults.getContentPane().add(txtYouHaveWon);
		txtYouHaveWon.setColumns(10);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(this::mainMenuCall);
		btnMainMenu.setBounds(0, 282, 510, 32);
		frmResults.getContentPane().add(btnMainMenu);
	}

	/**
	 * Call MainMenu class and updating number of tickets, numbers in tickets and
	 * balance in that class
	 * 
	 * @param ae passed actionListner to know which button has been pressed
	 */
	private void mainMenuCall(ActionEvent ae) {
		mainMenu = new MainMenu(numbersInTicketsReset, balance, numberOfTicketsPurchasedReset);
		mainMenu.frmLotterygame.setVisible(true);
		frmResults.setVisible(false);
	}

	/**
	 * adding values from numbersInTickets integer to string numbersInTickets and
	 * converting them to string as well as adding a tab between each value in
	 * string. Adding new line after 6 values has been added to string
	 * 
	 * @param numbersInTickets holding message "Your numbers are:"
	 * @return string message with all values entered and each ticket have it's own
	 *         line
	 */
	private String toStringNumbersInTickets(String numbersInTickets) {
		for (int i = 0; i < numberOfTicketsPurchased; i++) {
			for (int j = 0; j < 6; j++) {
				if (winningNumbers[j] == this.numbersInTickets[j][i]) {
					textArea.setForeground(Color.RED);
					//textArea.setHighlighter(numbersInTickets += Integer.toString(this.numbersInTickets[j][i]) + "\t");
					//textArea.setHighlighter(numbersInTickets);
					numbersInTickets += Integer.toString(this.numbersInTickets[j][i]) + "\t";
				}
				else {
					textArea.setForeground(Color.BLACK);
					numbersInTickets += Integer.toString(this.numbersInTickets[j][i]) + "\t";
				}
				if (j == 5) {
					numbersInTickets += "\n";
				}
			}
		}
		return numbersInTickets;
	}

	/**
	 * adding values from winningNumbers integer to string winningNumbersText and
	 * converting them to string as well as adding a tab between each value in
	 * string. Adding new line after 6 values has been added to string
	 * 
	 * @param winningNumbersText holding message "Winning numbers are!"
	 * @return string message with all values generated
	 */
	private String toStringWinningNumbers(String winningNumbersText) {
		for (int i = 0; i < 6; i++) {
			winningNumbersText += Integer.toString(winningNumbers[i]) + "\t";
			if (i == 5) {
				winningNumbersText += "\n";
			}
		}
		return winningNumbersText;
	}

	/**
	 * adding money to balance if ticket has more than 3 correct guesses with
	 * numbers
	 */
	private void checkWinningPrice() {
		for (int i = 0; i < numberOfTicketsPurchased; i++) {
			if (counterCorrectNumbers[i] == 3) {
				balance += winningPrice[0]; // +£5
				wonAmmount += winningPrice[0];
			} else if (counterCorrectNumbers[i] == 4) {
				balance += winningPrice[1];// +£10
				wonAmmount += winningPrice[1];
			} else if (counterCorrectNumbers[i] == 5) {
				balance += winningPrice[2];// +£100
				wonAmmount += winningPrice[2];
			} else if (counterCorrectNumbers[i] == 6) {
				balance += winningPrice[3];// +£1000
				wonAmmount += winningPrice[3];
			}
		}
	}

	/**
	 * checking of how much does the each ticket guessed the generated number
	 */
	private void countWinningNumbers() {

		for (int i = 0; i < numberOfTicketsPurchased; i++) {
			for (int j = 0; j < 6; j++) {
				for(int k = 0; k < 6; k++) {
					if (winningNumbers[k] == numbersInTickets[j][i]) {
						counterCorrectNumbers[i]++;
					}	
				}	
			}

		}
	}

	/**
	 * sorting winningNumbers from smallest to biggest
	 */
	private void sortNumbersInOrder() {
		int temp = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6 - 1; j++) {
				if (winningNumbers[j] > winningNumbers[j + 1]) {
					temp = winningNumbers[j];
					winningNumbers[j] = winningNumbers[j + 1];
					winningNumbers[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * sorting numbersInTickets from smallest to biggest in each ticket
	 */
	private void sortNumbersInOrder2dArray() {
		int temp = 0;
		int ammountOfValuesInArray = 0;
		ammountOfValuesInArray = numberOfTicketsPurchased * 6;
		for (int i = 0; i < ammountOfValuesInArray; i++) {
			for (int j = 0; j < numberOfTicketsPurchased; j++) {
				for (int k = 0; k < 6 - 1; k++) {
					if (numbersInTickets[k][j] > numbersInTickets[k + 1][j]) {
						temp = numbersInTickets[k][j];
						numbersInTickets[k][j] = numbersInTickets[k + 1][j];
						numbersInTickets[k + 1][j] = temp;
					}
				}

			}
		}
	}

	/**
	 * generating random number from 1 to 32, making sure there wont be any repeated
	 * numbers and saving it in winningNumbers
	 */
	private void generateWinningNumbers() {
		Random rng = new Random();
		for (int i = 0; i < 6; i++) {
			winningNumbers[i] = rng.nextInt(32) + 1;
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (i == j) {
					continue;
				} else {
					if (winningNumbers[j] == winningNumbers[i]) {
						do {
							winningNumbers[i] = rng.nextInt(32) + 1;
							j = 0;
							if (i == j) {
								j++;
							}
						} while (winningNumbers[j] == winningNumbers[i]);
					}
				}
			}
		}
	}
}
