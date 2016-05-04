package GUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mysql.jdbc.Statement;



import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class loginPage {

	private JFrame frame;
	private JTextField userName_txt;
	private JPasswordField password_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage window = new loginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblUsername = new JLabel("Username:");

		userName_txt = new JTextField();
		userName_txt.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		JButton btnLogin = new JButton("LogIn");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = userName_txt.getText();
				String password = password_txt.getText();
				if(username.equals("") || password.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill the fields");
					return;
				}
				Connection conn = null;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://phpmyadmin.in.cs.ucy.ac.cy/cs363db?" + "user=cs363db&password=NjFU2pKz");
				} catch (SQLException ex) {
					// handle any errors0
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
				Statement stmt = null;
				ResultSet rs = null;
				try {
					stmt = (Statement) conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM legalstaff WHERE username = '" + username + "' AND password = " + password);
					if (!rs.isBeforeFirst()){
						rs = stmt.executeQuery("SELECT * FROM receptionist WHERE username = '" + username + "' AND password = " + password);
						if (!rs.isBeforeFirst()){
							JOptionPane.showMessageDialog(frame, "Wrong username or password");
						}
					}
					while(rs.next()){
						System.out.println(rs.getString("Name"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}





			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.BLACK);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		JLabel lblWelcomeToLawos = new JLabel("Welcome To LawOS");
		lblWelcomeToLawos.setFont(new Font("Arial", Font.BOLD, 18));

		password_txt = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(42)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWelcomeToLawos, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
										.addGap(57)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(password_txt, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
												.addComponent(userName_txt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
						.addGap(151))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(24)
						.addComponent(lblWelcomeToLawos)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(userName_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(password_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin)
								.addComponent(btnExit))
						.addContainerGap(78, Short.MAX_VALUE))
				);
		frame.getContentPane().setLayout(groupLayout);
	}
}
