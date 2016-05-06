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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class WeeklyReportsBranch {

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
					WeeklyReportsBranch window = new WeeklyReportsBranch();
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
	public WeeklyReportsBranch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWeeklyReportsPer = new JLabel("Weekly reports Per Branch");
		lblWeeklyReportsPer.setFont(new Font("Arial", Font.BOLD, 16));
		
		JLabel label = new JLabel("Enter Branch Id");
		
		branch_txt = new JTextField();
		branch_txt.setColumns(10);
		
		JButton button = new JButton("Refresh");
		button.addMouseListener(new MouseAdapter() {
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

					
				String res = target.path("rest").path("lawos").path("hom").path("reports").path("weekly").path("perbranch").path("attended").request().accept(MediaType.APPLICATION_JSON)
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
							model.addRow(new Object[]{arr.getJSONObject(i).getString("DAYOFWEEK(`Date`)"), arr.getJSONObject(i).getString("COUNT(DISTINCT ClientID)")});
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
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWeeklyReportsPer)
							.addGap(173))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
							.addGap(29))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(106)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWeeklyReportsPer)
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(label))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(branch_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button_2)))
					.addGap(47))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"WeekDay", "Num of Clients"
			}
		));
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
