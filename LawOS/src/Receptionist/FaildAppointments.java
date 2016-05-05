package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GUI.loginPage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FaildAppointments {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaildAppointments window = new FaildAppointments();
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
	public FaildAppointments() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 824, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblFaildAppointments = new JLabel("Faild Appointments");
		lblFaildAppointments.setFont(new Font("Arial", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton button = new JButton("Refresh");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0);
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				String res2 = target.path("rest").path("lawos").path("receptionist").path("app").path("passed").request()
						.accept(MediaType.APPLICATION_JSON).get(String.class);
				
				JSONObject json = null;
				try {
					json = new JSONObject(res2);
					if(json.get("size").equals("0")){
						JOptionPane.showMessageDialog(frame, "There is no completed appointments available.");
					}
					else{
						for(int i=0;i<Integer.parseInt(json.get("size").toString());i++){
							JSONArray arr = json.getJSONArray("results_array");
							model.addRow(new Object[]{arr.getJSONObject(i).getString("AppointmendID"), arr.getJSONObject(i).getString("Date"), arr.getJSONObject(i).getString("Time"),
									arr.getJSONObject(i).getString("clientID"),arr.getJSONObject(i).getString("legalStaffID"),arr.getJSONObject(i).getString("Case"),
									arr.getJSONObject(i).getString("Consultation"),arr.getJSONObject(i).getString("LegalOpinion")});
						}
					}
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		JButton button_1 = new JButton("GoBack");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});
		
		JButton button_2 = new JButton("LogOut");
		button_2.addMouseListener(new MouseAdapter() {
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
						.addComponent(lblFaildAppointments)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(103)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addGap(144)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(175)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFaildAppointments)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1)
						.addComponent(button_2)
						.addComponent(button))
					.addGap(75))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Appointment ID", "Date", "Time", "Client ID", "Legal Staff ID", "Case", "Consultation", "Leagal Opinion"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(93);
		table.getColumnModel().getColumn(1).setPreferredWidth(64);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(51);
		table.getColumnModel().getColumn(4).setPreferredWidth(79);
		table.getColumnModel().getColumn(5).setPreferredWidth(37);
		table.getColumnModel().getColumn(7).setPreferredWidth(92);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
