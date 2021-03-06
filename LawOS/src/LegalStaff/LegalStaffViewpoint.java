package LegalStaff;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.loginPage;
import Receptionist.UncompletedAppointments;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LegalStaffViewpoint {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LegalStaffViewpoint window = new LegalStaffViewpoint();
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
	public LegalStaffViewpoint() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblLegalStaffViewpoint = new JLabel("Legal Staff Viewpoint");
		lblLegalStaffViewpoint.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnEditAppointment = new JButton("Edit Appointment");
		btnEditAppointment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				editApp f = new editApp();
				f.main(null);
			}
		});
		
		JButton btnViewClient = new JButton("View Client");
		btnViewClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ViewClient2 f = new ViewClient2();
				f.main(null);
			}
		});
		
		JButton btnViewAllAppointments = new JButton("View All Appointments");
		btnViewAllAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				UncompletedAppointments f = new UncompletedAppointments();
				f.main(null);
			}
		});
		
		JButton btnEditCase = new JButton("Edit Case");
		btnEditCase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				EditCase f = new EditCase();
				f.main(null);
			}
		});
		
		JButton btnAddCase = new JButton("Add Case");
		btnAddCase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AddCase f = new AddCase();
				f.main(null);
			}
		});
		
		JButton btnViewAllCases = new JButton("View All Cases Of A Client");
		btnViewAllCases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ViewAllCases f = new ViewAllCases();
				f.main(null);
			}
		});
		
		JButton btnViewStrategySide = new JButton("View Strategy Side Effects");
		btnViewStrategySide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				SideEffects f = new SideEffects();
				f.main(null);
			}
		});
		
		JButton btnNewButton = new JButton("<html>Check If A Client Is Money<br> Laudring Involved</html>");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				IsClientML f = new IsClientML();
				f.main(null);
			}
		});
		
		JButton btnNewButton_1 = new JButton("MailBox");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				MailBox f = new MailBox();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(131)
							.addComponent(lblLegalStaffViewpoint))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnAddCase, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnViewAllAppointments, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEditAppointment, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
									.addComponent(btnEditCase, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
							.addGap(75)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE)
								.addComponent(btnViewStrategySide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewAllCases, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewClient, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(191, Short.MAX_VALUE)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(169))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegalStaffViewpoint)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditAppointment)
						.addComponent(btnViewClient))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnViewAllAppointments)
						.addComponent(btnViewAllCases))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddCase)
						.addComponent(btnViewStrategySide))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnLogout))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnEditCase)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
