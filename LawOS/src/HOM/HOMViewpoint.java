package HOM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import GUI.loginPage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HOMViewpoint {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HOMViewpoint window = new HOMViewpoint();
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
	public HOMViewpoint() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 508, 317);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblHeadOfficeManagement = new JLabel("Head Office Management");
		lblHeadOfficeManagement.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnClientsPerBranch = new JButton("Clients Per Branch Each Month");
		btnClientsPerBranch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ClientsMonth f = new ClientsMonth();
				f.main(null);
			}
		});
		
		JButton btnRecomendationsPerMonth = new JButton("Recomendations Per Month");
		btnRecomendationsPerMonth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				RecomendationsMonth f = new RecomendationsMonth();
				f.main(null);
			}
		});
		
		JButton btnLegalOpinionsPer = new JButton("Legal Opinions Per Month");
		btnLegalOpinionsPer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				OpinionsMonth f = new OpinionsMonth();
				f.main(null);
			}
		});
		
		JButton btnClientTimesWaited = new JButton("Client Times Waited");
		btnClientTimesWaited.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AppTimeWait f = new AppTimeWait();
				f.main(null);
			}
		});
		
		JButton btnWeeklyReportsPer = new JButton("Weekly Reports Per Brunch In Week");
		btnWeeklyReportsPer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				WeeklyReportsBranch f = new WeeklyReportsBranch();
				f.main(null);
			}
		});
		
		JButton btnWeeklyReportsPer_1 = new JButton("Weekly Reports Per Client");
		btnWeeklyReportsPer_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				WeeklyReportsClient f = new WeeklyReportsClient();
				f.main(null);
			}
		});
		
		JButton btnNewButton = new JButton("Weekly Reports Per Branch All Days");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				WeeklyReportsBranchAll f = new WeeklyReportsBranchAll();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(118)
							.addComponent(lblHeadOfficeManagement))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnRecomendationsPerMonth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnLegalOpinionsPer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnClientTimesWaited, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnClientsPerBranch, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnWeeklyReportsPer_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnWeeklyReportsPer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(29, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(187)
					.addComponent(btnLogout)
					.addContainerGap(209, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHeadOfficeManagement)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClientsPerBranch)
						.addComponent(btnWeeklyReportsPer))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRecomendationsPerMonth)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLegalOpinionsPer)
						.addComponent(btnWeeklyReportsPer_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClientTimesWaited)
					.addGap(43)
					.addComponent(btnLogout)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
