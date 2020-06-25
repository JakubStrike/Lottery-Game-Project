package lotteryGameProject;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * The main form of LotteryGameProject
 * @author Jakub Stachura
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainMenu extends JDialog {

	/**
	 * making object of a class CashIn
	 */
	private CashIn cashIn = null;
	/**
	 * making object of a class ChooseNumOfTickets
	 */
	private ChooseNumOfTickets chooseNumOfTickets = null;
	/**
	 * making object of a class Results
	 */
	private Results results = null;
	/**
	 * making object of a class Withdraw
	 */
	private Withdraw withdraw = null;
	/**
	 * The main dialog frame
	 */
	protected JFrame frmLotterygame;
	/**
	 * The text field displaying current balance.
	 */
	private JTextField txtMoneyLeft;
	/**
	 * holding value of balance for the project
	 */
	private int balance = 0;
	/**
	 * holding price of ticket cost for the project
	 */
	private int ticketCost = 2;
	/**
	 * holding number of tickets purchased
	 */
	private int numberOfTicketsPurchased = 0;
	/**
	 * first dimension hold (6 numbers picked) second dimension hold value in witch ticket has those 6 numbers been saved
	 */
	private int[][] numbersInTickets = new int[6][999];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MainMenu mainWindow = new MainMenu();
		mainWindow.frmLotterygame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * @param balance holding value of updated balance 
	 */
	public MainMenu(int balance) {
		this.balance = balance;
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * @param numberOfTickets holding number of tickets purchased
	 * @param numbersInTickets holding value in first dimension of 6 number choose in specific ticket and second dimension holding value of each ticket bought to make it easy to story all values
	 * @param balance holding value of updated balance 
	 */
	public MainMenu(int numberOfTickets, int[][] numbersInTickets, int balance) {
		numberOfTicketsPurchased = numberOfTickets;
		this.numbersInTickets = numbersInTickets;
		this.balance = balance;
		gui();
	}

	/**
	 * Create the application. and assign parameters to instance fields
	 * @param numbersInTicketsReset set all values in this two dimension array to 0
	 * @param balance holding value of updated balance 
	 * @param numberOfTicketsPurchasedReset set all values in this variable to 0
	 */
	public MainMenu(int[][] numbersInTicketsReset, int balance, int numberOfTicketsPurchasedReset) {
		numbersInTickets = numbersInTicketsReset;
		this.balance += balance;
		numberOfTicketsPurchased = numberOfTicketsPurchasedReset;
		gui();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void gui() {
		frmLotterygame = new JFrame();
		
		frmLotterygame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                handleWindowClosing();
            }
        });
		
		frmLotterygame.setTitle("LotteryGame");
		frmLotterygame.setType(Type.UTILITY);
		frmLotterygame.setResizable(false);
		frmLotterygame.setSize(200,252);
		frmLotterygame.setLocationRelativeTo(null);
		frmLotterygame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmLotterygame.getContentPane().setLayout(null);

		txtMoneyLeft = new JTextField();
		txtMoneyLeft.setHorizontalAlignment(SwingConstants.CENTER);
		txtMoneyLeft.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtMoneyLeft.setText("Money Left: £" + balance);
		txtMoneyLeft.setEditable(false);
		txtMoneyLeft.setBounds(0, 0, 194, 35);
		frmLotterygame.getContentPane().add(txtMoneyLeft);
		txtMoneyLeft.setColumns(10);

		JButton btnCashIn = new JButton("Cash in money");
		btnCashIn.addActionListener(this::cashInMoney);
		btnCashIn.setBounds(0, 34, 194, 48);
		frmLotterygame.getContentPane().add(btnCashIn);

		JButton btnNumberOfTickets = new JButton("Choose numbers of tickets");
		btnNumberOfTickets.addActionListener(this::ChooseNumberOfTickets);
		btnNumberOfTickets.setBounds(0, 81, 194, 48);
		frmLotterygame.getContentPane().add(btnNumberOfTickets);

		JButton btnResult = new JButton("Check Results");
		btnResult.addActionListener(this::results);
		btnResult.setBounds(0, 128, 194, 48);
		frmLotterygame.getContentPane().add(btnResult);

		JButton btnWithdraw = new JButton("Withdraw money");
		btnWithdraw.addActionListener(this::withdraw);
		btnWithdraw.setBounds(0, 175, 194, 48);
		frmLotterygame.getContentPane().add(btnWithdraw);
	}
	
	  /**
     * Handle the intercepted WindowClosing event and ask the user to confirm
     * that they really want to exit.
     */
    private void handleWindowClosing()
    {
        int response = JOptionPane.showConfirmDialog(frmLotterygame, "Are you sure you want to exit?",
                "Exit Application", JOptionPane.YES_NO_OPTION);
        
        if (response == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
	

	/**
	 * Call Withdraw class and updating balance in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void withdraw(ActionEvent ae) {
		withdraw = new Withdraw(balance);
		withdraw.frame.setVisible(true);
		frmLotterygame.setVisible(false);
	}
	/**
	 * Call Result class and updating balance, numberOfTicketsPurchased and numbersInTickets in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void results(ActionEvent ae) {
		results = new Results(balance, numberOfTicketsPurchased, numbersInTickets);
		results.frmResults.setVisible(true);
		frmLotterygame.setVisible(false);
	}
	/**
	 * Call ChooseNumOfTickets class and updating balance and ticketCost in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void ChooseNumberOfTickets(ActionEvent ae) {
		chooseNumOfTickets = new ChooseNumOfTickets(balance, ticketCost);
		chooseNumOfTickets.frame.setVisible(true);
		frmLotterygame.setVisible(false);
	}
	/**
	 * Call CashIn class and update balance and ticket cost in that class
	 * @param ae passed actionListner to know which button has been pressed 
	 */
	private void cashInMoney(ActionEvent ae) {
		cashIn = new CashIn(balance, ticketCost);
		cashIn.frame.setVisible(true);
		frmLotterygame.setVisible(false);

	}

}
