package lotteryGameProject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * The dialog form used to ask user for amount of money want to cash in.
 * @author Jakub Stachura
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CashIn extends JDialog {
	/**
	 * The main dialog frame
	 */
	protected JFrame frame;
	/**
	 * The text field providing the user input.
	 */
	private JTextField textInput;
	/**
	 * making object of a class MainMenu
	 */
	private MainMenu mainMenu = null;
	/**
	 * holding value of balance
	 */
	private int balance = 0;
	/**
	 * holding cost of ticket
	 */
	private int ticketCost = 0;
	/**
	 * holding amount of tickets user are able to buy by calculating ticket cost with current balance
	 */
	private int avalibleTickets = 0;

	/**
	 * Create the application.
	 */
	public CashIn() {
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * @param balance holding value of updated balance 
	 * @param ticketCost holding value of ticket cost
	 */
	public CashIn(int balance, int ticketCost) {
		this.balance = balance;
		this.ticketCost = ticketCost;
		gui();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void gui() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(386, 196);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textInput = new JTextField();
		textInput.setHorizontalAlignment(SwingConstants.CENTER);
		textInput.setBounds(0, 50, 383, 34);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(this::mainMenuCall);
		btnMainMenu.setBounds(0, 125, 383, 41);
		frame.getContentPane().add(btnMainMenu);

		JButton btnOK = new JButton("Comfirm payment");
		btnOK.addActionListener(this::paymentValidation);
		btnOK.setBounds(0, 83, 383, 41);
		frame.getContentPane().add(btnOK);
		
		JTextArea txtrPleaseEnterAmmount = new JTextArea();
		txtrPleaseEnterAmmount.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtrPleaseEnterAmmount.setEditable(false);
		txtrPleaseEnterAmmount.setText("   Please enter ammount of money you want to cash in\r\n\t      Each ticket cost \u00A3" + ticketCost);
		txtrPleaseEnterAmmount.setBounds(0, 5, 383, 43);
		frame.getContentPane().add(txtrPleaseEnterAmmount);
	}

	/**
	 * Call MainMenu class and updating balance in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void mainMenuCall(ActionEvent ae) {
		mainMenu = new MainMenu(balance);
		mainMenu.frmLotterygame.setVisible(true);
		frame.setVisible(false);
	}

	/**
	 * Making sure user input are correct and telling user of how much tickets he can buy for that amount of money
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void paymentValidation(ActionEvent ae) {
		int tempBalance = 0;

		try {
			tempBalance = Integer.parseInt(textInput.getText());
		} catch (Exception exx) {
			System.out.println(exx);
			JOptionPane.showMessageDialog(frame, "enter valid number! only whole value allowed");
		}

		if (tempBalance <= 0) {
			JOptionPane.showMessageDialog(frame, "You cant cash in nothing or negative number!");
		} else {
			balance += tempBalance;
		}

		if (balance >= ticketCost) {
			avalibleTickets = balance / ticketCost;
			if (avalibleTickets > 1) {
				JOptionPane.showMessageDialog(frame, "You are allow to buy " + avalibleTickets + " lottery tickets");
			} else {
				JOptionPane.showMessageDialog(frame, "You are allow to buy one lottery ticket");
			}
		} else {
			JOptionPane.showMessageDialog(frame, "You dont have enought money to buy lottery ticket");
		}
	}
}
