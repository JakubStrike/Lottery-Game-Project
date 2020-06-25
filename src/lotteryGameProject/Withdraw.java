package lotteryGameProject;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * @author Jakub Stachura
 *@version 1.0
 */
public class Withdraw {
	/**
	 * The main dialog frame
	 */
	protected JFrame frame;
	/**
	 * The text field displaying question for user of how much does he want to withdraw.
	 */
	private JTextField txtPleaseEnterAmmount;
	/**
	 * The text field providing the user input
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
	 * Create the application. and assign parameters to instance fields
	 */
	public Withdraw() {
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * @param balance holding value of updated balance
	 */
	public Withdraw(int balance) {
		this.balance = balance;
		gui();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void gui() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setSize(344, 171);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtPleaseEnterAmmount = new JTextField();
		txtPleaseEnterAmmount.setEditable(false);
		txtPleaseEnterAmmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPleaseEnterAmmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtPleaseEnterAmmount.setText("Please enter ammount you want to withdraw");
		txtPleaseEnterAmmount.setBounds(0, 0, 337, 35);
		frame.getContentPane().add(txtPleaseEnterAmmount);
		txtPleaseEnterAmmount.setColumns(10);

		textInput = new JTextField();
		textInput.setHorizontalAlignment(SwingConstants.CENTER);
		textInput.setBounds(0, 35, 337, 35);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(this::mainMenu);
		btnMainMenu.setBounds(0, 105, 337, 35);
		frame.getContentPane().add(btnMainMenu);

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(this::withdraw);
		btnWithdraw.setBounds(0, 70, 337, 35);
		frame.getContentPane().add(btnWithdraw);
	}
	/**
	 * Call MainMenu class and updating number of tickets, numbers in tickets and balance in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void mainMenu(ActionEvent ae) {
		mainMenu = new MainMenu(balance);
		mainMenu.frmLotterygame.setVisible(true);
		frame.setVisible(false);
	}

	/**
	 * validate user input and if correct taking amount of user want to withdraw from current balance
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void withdraw(ActionEvent ae) {
		int withdraw = 0;

		try {
			withdraw = Integer.parseInt(textInput.getText());
		} catch (Exception exx) {
			System.out.println(exx);
			JOptionPane.showMessageDialog(frame, "enter valid number! only whole value allowed");
		}
		if (withdraw <= 0) {
			JOptionPane.showMessageDialog(frame, "You cant withdraw nothing or negative number!");
		} else if (withdraw > balance) {
			JOptionPane.showMessageDialog(frame, "You cant withdraw more than your current balance");
		} else {
			balance -= withdraw;
			JOptionPane.showMessageDialog(frame, "Transaction has been complete");
		}

	}

}
