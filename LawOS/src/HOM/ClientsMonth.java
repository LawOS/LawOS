package HOM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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
import LRS.LRSViewpoint;
import Receptionist.ReceptionistViewpoint;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JScrollPane;

public class ClientsMonth {

	private JFrame frame;
	private JTextField branch_txt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsMonth window = new ClientsMonth();
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
	public ClientsMonth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblClientsPerBranch = new JLabel("Clients Per Branch Each Month");
		lblClientsPerBranch.setFont(new Font("Arial", Font.BOLD, 16));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
			    model.setRowCount(0);
				String branchID = branch_txt.getText();
				if(branchID.equals("")){
					JOptionPane.showMessageDialog(frame, "Enter a brnch id");
					return;
				}
				
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				
				Form form = new Form();
				form.param("BranchID", branchID);

					
				String res = target.path("rest").path("lawos").path("hom").path("reports").path("branch").path("nclient").request().accept(MediaType.APPLICATION_JSON)
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
							model.addRow(new Object[]{arr.getJSONObject(i).getString("MONTH(`Date`)"), arr.getJSONObject(i).getString("COUNT(DISTINCT ClientID)")});
						}
					}
					
					
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnGoback = new JButton("GoBack");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				HOMViewpoint f = new HOMViewpoint();
				f.main(null);
			}
		});
		
		JButton btnLogout = new JButton("LogOut");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		branch_txt = new JTextField();
		branch_txt.setColumns(10);
		
		JLabel lblEnterBranchId = new JLabel("Enter Branch Id");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(199)
					.addComponent(lblClientsPerBranch)
					.addContainerGap(199, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblEnterBranchId, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(57)
					.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(132)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGoback, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
					.addGap(57))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(103, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientsPerBranch)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnGoback)
							.addGap(18)
							.addComponent(btnLogout)
							.addGap(43))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterBranchId)
								.addComponent(btnRefresh))
							.addGap(60))))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Month", "Clients"
			}
		));
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
