package Receptionist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
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

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class ViewClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField surname_txt;
	private JTextField id_txt;
	private JTextField unwillingness_txt;
	private JTextField comments_txt;


	/**
	 * Launch the application.
	 */
	public static void main(String clientID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClient window = new ViewClient(clientID);
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
	public ViewClient(String cID) {
		initialize(cID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String cID) {
		frame = new JFrame();
		frame.setBounds(100, 100, 422, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblViewClient = new JLabel("View Client");
		lblViewClient.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblNewLabel = new JLabel("Name");

		name_txt = new JTextField();
		name_txt.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");

		surname_txt = new JTextField();
		surname_txt.setColumns(10);

		JLabel lblId = new JLabel("ID");

		id_txt = new JTextField();
		id_txt.setColumns(10);

		JLabel lblUnwillingnes = new JLabel("Unwillingnes");

		unwillingness_txt = new JTextField();
		unwillingness_txt.setColumns(10);

		JLabel lblComments = new JLabel("Comments");

		comments_txt = new JTextField();
		comments_txt.setColumns(10);

		//request with clientID and fill form
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		Form form = new Form();
		form.param("ID", cID);

		String res2 = target.path("rest").path("lawos").path("search").path("client").request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

		JSONObject json = null;

		try {
			json = new JSONObject(res2);
			if(json.get("size").equals("0")){
				name_txt.setEditable(false);
				surname_txt.setEditable(false);
				id_txt.setEditable(false);
				unwillingness_txt.setEditable(false);
				comments_txt.setEditable(false);
			}
			else{
				JSONArray arr = json.getJSONArray("results_array");
				name_txt.setText(arr.getJSONObject(0).getString("Name"));
				name_txt.setEditable(false);
				surname_txt.setText(arr.getJSONObject(0).getString("Surname"));
				surname_txt.setEditable(false);
				id_txt.setText(arr.getJSONObject(0).getString("ID"));
				id_txt.setEditable(false);
				unwillingness_txt.setText(arr.getJSONObject(0).getString("Unwillingness"));
				unwillingness_txt.setEditable(false);
				comments_txt.setText(arr.getJSONObject(0).getString("Comments"));
				comments_txt.setEditable(false);
			}

		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				ReceptionistViewpoint f = new ReceptionistViewpoint();
				f.main(null);
			}
		});

		JButton btnCreateAppointment = new JButton("<html>Create <br>\r\nAppointment</html>");
		btnCreateAppointment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String id = id_txt.getText();
				frame.setVisible(false);
				AppointmentForm f = new AppointmentForm(id);
				f.main(id);


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

		JButton btnsearchForStrategies = new JButton("<html>Search For <br>\r\nStrategies</html>");
		btnsearchForStrategies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = id_txt.getText();
				if (id.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill the client id field");
					return;
				}
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());

				Form form = new Form();
				form.param("ID", id);
				String res = target.path("rest").path("lawos").path("recom").path("strategies").request().accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

				JSONObject json = null;
				try {
					json = new JSONObject(res);
					String str="";
					if(!json.get("size").equals("0")){
						JSONArray arr = json.getJSONArray("results_array");
						for(int i=0;i<Integer.parseInt(json.get("size").toString());i++)
							str+=(i+1) + "." + arr.getJSONObject(i).getString("Strategy") + "\n";
						JOptionPane.showMessageDialog(frame, "Available strategies are:\n" + str);
					}
					else{
						JOptionPane.showMessageDialog(frame, "No strategies available");
					}


				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});


		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblSurname)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnwillingnes, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblComments))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCreateAppointment)
										.addComponent(btnsearchForStrategies, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
								.addComponent(comments_txt, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(76)
							.addComponent(lblViewClient))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(btnGoBack)
							.addGap(18)
							.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblViewClient)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSurname))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnwillingnes)
								.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(btnCreateAppointment, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnsearchForStrategies, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblComments)
						.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack)
						.addComponent(btnLogout))
					.addGap(36))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
