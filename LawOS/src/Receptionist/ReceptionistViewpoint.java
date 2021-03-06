package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New Appointment\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				AppointmentForm f2 = new AppointmentForm(null);
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
		clientsearch_txt.setToolTipText("");
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
				if(clientID.equals("")){
					JOptionPane.showMessageDialog(frame,"Enter a client id");
					return;
				}
				frame.setVisible(false);
				ViewClient f = new ViewClient(clientID);
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
		
		JButton btnDeleteAppointment = new JButton("Delete Appointment");
		btnDeleteAppointment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				DeleteAppointment f = new DeleteAppointment();
				f.main(null);
			}
		});
		
		JButton btnViewFaildAppointments = new JButton("View Faild Appointments");
		btnViewFaildAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				FaildAppointments f = new FaildAppointments();
				f.main(null);
			}
		});
		
		JLabel lblSearchForClient = new JLabel("Search for client");
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
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnViewAppointments, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(btnViewUncompletedAppointments, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnViewFaildAppointments, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDeleteAppointment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLogout, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblSearchForClient)
									.addGap(18)
									.addComponent(clientsearch_txt, 194, 194, 194)))))
					.addGap(116))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(367, Short.MAX_VALUE)
					.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(55))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(lblReceptionist)
					.addGap(18)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDeleteAppointment)
					.addGap(14)
					.addComponent(btnViewAppointments)
					.addGap(18)
					.addComponent(btnViewUncompletedAppointments)
					.addGap(18)
					.addComponent(btnViewFaildAppointments)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSearchForClient)
							.addComponent(clientsearch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_Search, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
