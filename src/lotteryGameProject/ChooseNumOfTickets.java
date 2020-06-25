package lotteryGameProject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.util.Random;
import java.awt.event.ActionEvent;

/**
 * @author Jakub Stachura
 *@version 1.0
 */
public class ChooseNumOfTickets {

	/**
	 * The main dialog frame
	 */
	protected JFrame frame;
	/**
	 * the text field displaying massage to user to enter amount of tickets.
	 */
	private JTextField txtPleaseEnterNumber;
	/**
	 * The text field providing the user input
	 */
	private JTextField textNumberOfTickets;
	/**
	 * making object of a class MainMenu
	 */
	private MainMenu mainMenu = null;
	/**
	 * Setting maximum value fornumbersInTickets in first dimension
	 */
	private final int MAX = 32;
	/**
	 * Setting minimum value fornumbersInTickets in first dimension
	 */
	private final int MIN = 1;
	/**
	 * Setting maximum value fornumbersInTickets in second dimension
	 */
	private final int MAX_TICKETS = 999;
	/**
	 * holding value of balance
	 */
	private int balance = 0;
	/**
	 * holding value of ticket cost
	 */
	private int ticketCost = 0;
	/**
	 * holding value of tickets bought
	 */
	private int numberOfTickets = 0;
	/**
	 * first dimension hold (6 numbers picked) second dimension hold value in witch ticket has those 6 numbers been saved
	 */
	private int[][] numbersInTickets = new int[6][MAX_TICKETS]; 
	/**
	 * Create the application.
	 */
	
	public ChooseNumOfTickets() {
		gui();
	}
	
	/**
	 * Create the application. and assign parameters to instance fields
	 * @param balance holding value of updated balance 
	 * @param ticketCost holding value of ticketCost
	 */
	public ChooseNumOfTickets(int balance, int ticketCost) {
		this.balance = balance;
		this.ticketCost = ticketCost;
		gui();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void gui() {
		frame = new JFrame();
		frame.setSize(360, 153);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtPleaseEnterNumber = new JTextField();
		txtPleaseEnterNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPleaseEnterNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPleaseEnterNumber.setText("Please enter number of ticket's you want to buy");
		txtPleaseEnterNumber.setEditable(false);
		txtPleaseEnterNumber.setBounds(0, 0, 344, 23);
		frame.getContentPane().add(txtPleaseEnterNumber);
		txtPleaseEnterNumber.setColumns(10);
		
		textNumberOfTickets = new JTextField();
		textNumberOfTickets.setHorizontalAlignment(SwingConstants.CENTER);
		textNumberOfTickets.setBounds(0, 22, 344, 32);
		frame.getContentPane().add(textNumberOfTickets);
		textNumberOfTickets.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm Purchase");
		btnConfirm.addActionListener(this::numOfTickets);
		btnConfirm.setBounds(0, 52, 344, 32);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(this::mainMenuCall);
		btnMainMenu.setBounds(0, 82, 344, 32);
		frame.getContentPane().add(btnMainMenu);
		

	}
	/**
	 * Call MainMenu class and updating number of tickets, numbers in tickets and balance in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void mainMenuCall(ActionEvent ae) {
		mainMenu = new MainMenu(numberOfTickets, numbersInTickets, balance);
		mainMenu.frmLotterygame.setVisible(true);
		frame.setVisible(false);
	}
	
	/**
	 * validate user input of how much tickets user wants to buy as well as making sure user wont be able to buy more tickets than his balance allowing him to. Also calling a method chooseNumbersInTickets if all validation are correct
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void numOfTickets(ActionEvent ae){
int tempNumberOfTickets = 0;
int tempBalance = 0;
int payment = 0;
		try{
			tempNumberOfTickets = Integer.parseInt(textNumberOfTickets.getText());
			}catch(Exception exx) {System.out.println(exx);
				JOptionPane.showMessageDialog(frame, "enter valid number! only whole value allowed");
			}
			if(tempNumberOfTickets <= 0 || tempNumberOfTickets >= MAX_TICKETS){
				JOptionPane.showMessageDialog(frame, "You have to buy at least one ticket and you cant buy more than " + MAX_TICKETS);
				return;
			}
			else {
				numberOfTickets = tempNumberOfTickets;
			}

		
		
		if (balance >= ticketCost) {
			payment = ticketCost * numberOfTickets;
			if(balance >= payment) {
				balance -= payment;
				JOptionPane.showMessageDialog(frame, "You have bought " + numberOfTickets + " tickets");
				textNumberOfTickets.setText("");
				
				for(int i = 0; i < numberOfTickets; i++) {
					String[] options = { "Automatically", "Manually", "Automatically finish the rest" };
					int answerOption = 0;
					answerOption = JOptionPane.showOptionDialog(null, "Select if you want to fill ticket numbers manually or randomly for ticket number: " + (i + 1), "Select Ticket type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					chooseNumbersInTickets(i, answerOption);
					if(answerOption == 2) {
						break;
					}
					
				}
			}
			else {
				tempBalance = balance;
				tempBalance -= payment;
				JOptionPane.showMessageDialog(frame, "You need £" + (tempBalance * -1) + " more to buy "+ numberOfTickets + " tickets");
			}	
		}
		else {
			JOptionPane.showMessageDialog(frame, "You dont have enought money to buy lottery ticket");	
		}
	}
	
	
	/**
	 * Asking user to enter 6 values manually or generate them randomly with validations that numbers can't be the same as previous one's in same ticket by calling numbersInTicketsValidation method or smaller than 1 or greater than 32 by calling numbersRangeValidation method. If user want generate number's randomly this method will call generateRngNumbersInTicket. This method will be called as many times as user has bought number of tickets 
	 * @param k passing a value for index that will tell numbersInTickets in witch second dimension should the first dimension be saved
	 */
	private void chooseNumbersInTickets(int k, int answerOption){
	
		if(answerOption == 1) {
			for(int i = 0; i<6; i++) {
				do {
				try{
				numbersInTickets[i][k] = Integer.parseInt(JOptionPane.showInputDialog("Please enter number between 1 and 32"));
					}catch(Exception exx) {System.out.println(exx);
						JOptionPane.showMessageDialog(frame, "enter valid number! only whole value allowed");
						numbersInTickets[i][k] = Integer.parseInt(JOptionPane.showInputDialog("Please re-enter number between 1 and 32"));
					}
			} while (numbersInTickets[i][k] > MAX || numbersInTickets[i][k] < MIN);			
				if(i >= 1) {
					for(int j = 0; j < i; j++) {
						if(numbersInTickets[i][k] == numbersInTickets[j][k]) {
							numbersInTickets[i][k] = numbersInTicketsValidation(i,j,k);	
							j = 0;
						}
					}
				}
				
			}	
			
		}//end if statement for picking option 1 == manually
		else if(answerOption == 0){
			int[] tempRandomNumbers = {0,0,0,0,0,0};
			tempRandomNumbers = generateRngNumbersInTicket(); 
			for(int i=0; i<6; i++){
			numbersInTickets[i][k] = tempRandomNumbers[i];
			}
			
		}//end else for automatic numbers
		else if(answerOption == 2) {
			int[] tempRandomNumbers = new int[6];
			int j = 0;
			for(j = k; j < numberOfTickets; j++) {
				tempRandomNumbers = generateRngNumbersInTicket(); 
				for(int i=0; i<6; i++){
					numbersInTickets[i][j] = tempRandomNumbers[i];
					}
			}
			
		}
		
	}
	/**
	 * Generating random numbers from 1 to 32 and validate that there wont be the same numbers 
	 * @return an array with 6 numbers randomly generated
	 */
	private int[] generateRngNumbersInTicket(){
		Random rng = new Random();
		int[] tempRandomNumbers = {0,0,0,0,0,0};
		for(int i = 0; i<6; i++) {
			tempRandomNumbers[i] = rng.nextInt(32)+1;
			}
		for(int i = 0; i< 6; i++) {
			for(int j = 0; j< 6; j++) {
				if(i == j){
					continue;
				}
				else {
					if(tempRandomNumbers[j] == tempRandomNumbers[i]) {
						do {
							tempRandomNumbers[i] = rng.nextInt(32)+1;
							j = 0;
							if(i == j)
							{
								j++;
							}
						} while (tempRandomNumbers[j] == tempRandomNumbers[i]);
						
					}
				}
			}	
		}
		return tempRandomNumbers;
	}
	
	/**
	 * validate user input to make sure numbers wont be the same in one ticket
	 * @param i value for index in numbersInTickets in first Dimension 
	 * @param j value for index in numbersInTickets in first Dimension 
	 * @param k  value for index in numbersInTickets in second Dimension 
	 * @return correct input of user 
	 */
	private int numbersInTicketsValidation(int i, int j, int k){
		do {
			numbersInTickets[i][k] = Integer.parseInt(JOptionPane.showInputDialog("You have already entered that number! please choose difrent one between 1 and 32"));
		} while (numbersInTickets[i][k] == numbersInTickets[j][k]);
		
		return numbersInTickets[i][k];
	}
}
