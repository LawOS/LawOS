package HOM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
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

import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JScrollPane;

public class WeeklyReportsClient {

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
					WeeklyReportsClient window = new WeeklyReportsClient();
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
	public WeeklyReportsClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWeeklyReportsPer = new JLabel("Weekly Reports Per Client");
		lblWeeklyReportsPer.setFont(new Font("Arial", Font.BOLD, 16));
		
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
				form.param("ClientID", clientID);

					
				String res = target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perclient").request().accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				JSONObject json = null;
				try {
					json = new JSONObject(res);
					if(json.get("size").equals("0")){
						JOptionPane.showMessageDialog(frame, "No results.");
					}
					else{
						for(int i=0;i<Integer.parseInt(json.get("size").toString());i++){
							JSONArray arr = json.getJSONArray("results_array");
							model.addRow(new Object[]{arr.getJSONObject(i).getString("DAYOFWEEK(`Date`)"), arr.getJSONObject(i).getString("COUNT(DISTINCT Recommendation)"),
									arr.getJSONObject(i).getString("COUNT(DISTINCT LegalOpinion)")});
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
							.addGap(201)
							.addComponent(lblWeeklyReportsPer))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnterClientId, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(57)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(86)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(421)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(75)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWeeklyReportsPer)
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(lblEnterClientId))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addComponent(client_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(button))
						.addComponent(button_1))
					.addComponent(button_2)
					.addGap(51))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Day of Week", "Num Of Recommendations", "Num of Legal Opinions"
			}
		));
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
