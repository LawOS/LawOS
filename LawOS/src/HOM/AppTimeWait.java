package HOM;

import java.awt.EventQueue;
import java.net.URI;

import javax.swing.JFrame;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GUI.loginPage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class AppTimeWait {

	private JFrame frame;
	private JTextField client_txt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppTimeWait window = new AppTimeWait();
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
	public AppTimeWait() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblClientTimesWaited = new JLabel("Client Times Waited");
		lblClientTimesWaited.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel lblEnterClientId = new JLabel("Enter Client Id");
		
		client_txt = new JTextField();
		client_txt.setColumns(10);
		
		JButton button = new JButton("Refresh");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0);
				String clientID = client_txt.getText();
				if(clientID.equals("")){
					JOptionPane.showMessageDialog(frame, "Enter a client id");
					return;
				}
				
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				
				Form form = new Form();
				form.param("ID", clientID);

					
				String res = target.path("rest").path("lawos").path("hom").path("reports").path("client").path("timeswait").request().accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				JSONObject json = null;
				try {
					json = new JSONObject(res);
					if(json.get("size").equals("0")){
						JOptionPane.showMessageDialog(frame, "There is no completed appointments available.");
					}
					else{
						for(int i=0;i<Integer.parseInt(json.get("size").toString());i++){
							JSONArray arr = json.getJSONArray("results_array");
							model.addRow(new Object[]{arr.getJSONObject(i).getString("AppointmendID"), arr.getJSONObject(i).getString("Date"), arr.getJSONObject(i).getString("Time"),
									arr.getJSONObject(i).getString("clientID"),arr.getJSONObject(i).getString("legalStaffID"),arr.getJSONObject(i).getString("Case"),
									arr.getJSONObject(i).getString("BranchID"),arr.getJSONObject(i).getString("Recommendation"),arr.getJSONObject(i).getString("LegalOpinion")});
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
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
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
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(467)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterClientId, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(57)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(132)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(207)
							.addComponent(lblClientTimesWaited))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientTimesWaited)
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblEnterClientId))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(button_2)))
					.addGap(48))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Appointment ID", "Date", "Time", "Client ID", "Legal Staff ID", "Case ID", "Branch ID", "Recommendation", "Legal Opinion"
			}
		));
		frame.getContentPane().setLayout(groupLayout);
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
