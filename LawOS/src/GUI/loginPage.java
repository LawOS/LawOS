package GUI;

import javax.swing.JOptionPane;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONException;
import org.json.JSONObject;

import HOM.HOMViewpoint;
import LRS.LRSViewpoint;
import Receptionist.ReceptionistViewpoint;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class loginPage {

	private JFrame frame;
	private JTextField userName_txt;
	private JPasswordField password_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage window = new loginPage();
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
	public loginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 382, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblUsername = new JLabel("Username:");
		JComboBox cb_type = new JComboBox();
		userName_txt = new JTextField();
		userName_txt.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		JButton btnLogin = new JButton("LogIn");
	
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//initialize variables
				String username = userName_txt.getText();
				String password = password_txt.getText();
				String type = cb_type.getSelectedItem().toString();
				
				//check for empty fields
				if(username.equals("") || password.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill the fields");
					return;
				}
				
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				
				Form form = new Form();
				form.param("user", username);
				form.param("pass", password);
				
				if(type.equals("Legal Staff"))
					form.param("type", "legalstaff");
				else if(type.equals("Receptionist"))
					form.param("type", "receptionist");
				else if(type.equals("Legal Records Staff"))
					form.param("type", "legalrecordsstaff");
				else if(type.equals("Head Office Management"))
					form.param("type", "headoffice");
					
				String res = target.path("rest").path("lawos").request().accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				JSONObject json = null;
				JSONObject json2 = null;
				try {
					json = new JSONObject(res);
					if(json.get("size").equals("1")){
						/*json2 = new JSONObject(json.get("results_array").toString().substring(1,
							json.get("results_array").toString().length() - 1));*/
						frame.setVisible(false);
						if(type.equals("Legal Staff")){
							/*call viewpoint*/
						}
						else if(type.equals("Receptionist")){
							ReceptionistViewpoint f = new ReceptionistViewpoint();
							f.main(null);
						}
						else if(type.equals("Legal Records Staff")){
							LRSViewpoint f = new LRSViewpoint();
							f.main(null);
						}
						else if(type.equals("Head Office Management")){
							HOMViewpoint f = new HOMViewpoint();
							f.main(null);
						}
					}
					else{
						JOptionPane.showMessageDialog(frame, "Wrong username or password");
					}
					
					
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}



			}
		});

		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.BLACK);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		JLabel lblWelcomeToLawos = new JLabel("Welcome To LawOS");
		lblWelcomeToLawos.setFont(new Font("Arial", Font.BOLD, 18));

		password_txt = new JPasswordField();
		
		
		cb_type.setModel(new DefaultComboBoxModel(new String[] {"Legal Staff", "Receptionist", "Legal Records Staff", "Head Office Management"}));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(password_txt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(userName_txt, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(cb_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(151))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(92)
					.addComponent(lblWelcomeToLawos)
					.addContainerGap(166, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblWelcomeToLawos)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(userName_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(password_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cb_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit)
						.addComponent(btnLogin))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
