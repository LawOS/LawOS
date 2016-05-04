package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.loginPage;

public class ReceptionistViewpoint {

	private JFrame frame;
	private JTextField clientsearch_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistViewpoint window = new ReceptionistViewpoint();
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
	public ReceptionistViewpoint() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New Appointment\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				AppointmentForm f2 = new AppointmentForm();
				f2.main(null);
			}
		});
		
		JLabel lblReceptionist = new JLabel("Receptionist");
		lblReceptionist.setFont(new Font("Arial", Font.BOLD, 17));
		
		JButton btnViewAppointments = new JButton("View Completed Appointments");
		btnViewAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				CompletedAppointments f = new CompletedAppointments();
				f.main(null);
			}
		});
		
		clientsearch_txt = new JTextField();
		clientsearch_txt.setText("Search For Client");
		clientsearch_txt.setColumns(10);
		
		JButton btnViewUncompletedAppointments = new JButton("View Uncompleted Appointments");
		btnViewUncompletedAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				UncompletedAppointments f = new UncompletedAppointments();
				f.main(null);
			}
		});
		
		JButton btn_Search = new JButton("");
		btn_Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String clientID = clientsearch_txt.getText();
				
				//request
				
				frame.setVisible(false);
				ViewClient f = new ViewClient();
				f.main(clientID);
			}
		});
		btn_Search.setIcon(new ImageIcon("C:\\Users\\Andreas\\Documents\\EPL362\\EPL362_lawOSDB\\images\\Search.png"));
		btn_Search.setSelectedIcon(null);
		
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(158)
							.addComponent(lblReceptionist))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(clientsearch_txt, 194, 194, 194)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnViewAppointments, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
									.addComponent(btnViewUncompletedAppointments, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))))
					.addGap(157))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblReceptionist)
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(btnViewAppointments)
					.addGap(18)
					.addComponent(btnViewUncompletedAppointments)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(clientsearch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
