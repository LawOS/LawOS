package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.loginPage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField textField;
	private JTextField id_txt;
	private JTextField unwillingness_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String clientID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ViewClient window = new ViewClient();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//request with clientID and fill form
		
	}

	/**
	 * Create the application.
	 */
	public ViewClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 422, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblViewClient = new JLabel("View Client");
		lblViewClient.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblNewLabel = new JLabel("Name");
		
		name_txt = new JTextField();
		name_txt.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		
		id_txt = new JTextField();
		id_txt.setColumns(10);
		
		JLabel lblUnwillingnes = new JLabel("Unwillingnes");
		
		unwillingness_txt = new JTextField();
		unwillingness_txt.setColumns(10);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});
		
		JButton btnCreateAppointment = new JButton("<html>Create <br>\r\nAppointment</html>");
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblSurname)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUnwillingnes, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
							.addComponent(btnCreateAppointment)
							.addGap(27))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(208, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addComponent(lblViewClient)
					.addContainerGap(245, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addComponent(btnGoBack)
					.addGap(18)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblViewClient)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSurname))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnwillingnes)
								.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGoBack)
								.addComponent(btnLogout)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(81)
							.addComponent(btnCreateAppointment, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
