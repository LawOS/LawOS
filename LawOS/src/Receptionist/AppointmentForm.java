package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.loginPage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppointmentForm {

	private JFrame frame;
	private JTextField year_txt;
	private JTextField client_txt;
	private JTextField lawyer_txt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentForm window = new AppointmentForm();
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
	public AppointmentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblAppointmentForm = new JLabel("Appointment Form");
		
		JLabel lblDate = new JLabel("Date:");
		
		JComboBox cb_day = new JComboBox();
		cb_day.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cb_day.setToolTipText("");
		
		JComboBox cb_month = new JComboBox();
		cb_month.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		cb_month.setToolTipText("");
		
		year_txt = new JTextField();
		year_txt.setColumns(10);
		
		JLabel lblTime = new JLabel("Time");
		
		JComboBox cb_hour = new JComboBox();
		cb_hour.setModel(new DefaultComboBoxModel(new String[] {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		cb_hour.setToolTipText("");
		
		JComboBox cb_minutes = new JComboBox();
		cb_minutes.setModel(new DefaultComboBoxModel(new String[] {"00", "10", "20", "30", "40", "50"}));
		cb_minutes.setToolTipText("");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String date = year_txt.getText() + "-" + (cb_month.getSelectedIndex()+1) + "-" + cb_day.getSelectedItem().toString();
				String hour = cb_hour.getSelectedItem().toString() + ":" + cb_minutes.getSelectedItem().toString();
				String clientID = client_txt.getText();
				String lawyer = lawyer_txt.getText();
				
				//check for empty text boxes
				if(date.equals("") || hour.equals("") || clientID.equals("") || lawyer.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}
				
				System.out.println(date + " " + hour + " " + clientID + " " + lawyer);
				
				//request
			}
		});
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Client");
		
		client_txt = new JTextField();
		client_txt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lawyer");
		
		lawyer_txt = new JTextField();
		lawyer_txt.setColumns(10);
		
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
							.addComponent(lblAppointmentForm))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(cb_hour, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(cb_minutes, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(cb_day, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(cb_month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addComponent(year_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSubmit)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnGoBack)
									.addGap(18)
									.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAppointmentForm)
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(year_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_hour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cb_minutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lawyer_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnGoBack)
						.addComponent(btnLogout))
					.addGap(26))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
