package LRS;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditClient {

	private JFrame frame;
	private JTextField name_txt;
	private JTextField surname_txt;
	private JTextField id_txt;
	private JTextField unwillingness_txt;
	private JTextField comments_txt;
	private JButton btnSubmit;
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditClient window = new EditClient(args);
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
	public EditClient(String args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String args) {
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblClientForm = new JLabel("Client Form");
		lblClientForm.setFont(new Font("Arial", Font.BOLD, 16));

		name_txt = new JTextField();
		name_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				btnSubmit.setVisible(true);
			}
		});
		name_txt.setColumns(10);

		JLabel label = new JLabel("Name");

		JLabel label_1 = new JLabel("Surname");

		surname_txt = new JTextField();
		surname_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		surname_txt.setColumns(10);

		JLabel label_2 = new JLabel("ID");

		id_txt = new JTextField();

		id_txt.setColumns(10);

		JLabel label_3 = new JLabel("Unwillingnes");

		unwillingness_txt = new JTextField();
		unwillingness_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		unwillingness_txt.setColumns(10);

		JLabel label_4 = new JLabel("Comments");

		comments_txt = new JTextField();
		comments_txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				btnSubmit.setVisible(true);
			}
		});
		comments_txt.setColumns(10);

		//request with clientID and fill form
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget target = client.target(getBaseURI());
		Form form = new Form();
		form.param("ID", args);

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
				surname_txt.setText(arr.getJSONObject(0).getString("Surname"));
				id_txt.setText(arr.getJSONObject(0).getString("ID"));
				id_txt.setEditable(false);
				unwillingness_txt.setText(arr.getJSONObject(0).getString("Unwillingness"));
				comments_txt.setText(arr.getJSONObject(0).getString("Comments"));
			}

		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = name_txt.getText();
				String surname = surname_txt.getText();
				String id = id_txt.getText();
				String unwillingness = unwillingness_txt.getText();
				String comments = comments_txt.getText();
				
				//check for empty text boxes
				if(name.equals("") || surname.equals("") || id.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}

				
				//request
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				Form form = new Form();
				form.param("ID", id);
				form.param("Name", name);
				form.param("Surname", surname);
				form.param("Unwillingness", unwillingness);
				form.param("Comments", comments);

				
				String res2 = target.path("rest").path("lawos").path("lrs").path("edit").path("client").request()
						.accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				if (res2.equals("1")){
					JOptionPane.showMessageDialog(frame, "Client Edited Successfully");
				}
				else{
					JOptionPane.showMessageDialog(frame, "Wrong Edit");
				}
			}
		});
		btnSubmit.setVisible(false);

		JButton button = new JButton("Go Back");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LRSViewpoint f = new LRSViewpoint();
				f.main(null);
			}
		});

		JButton button_1 = new JButton("LogOut");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				loginPage f = new loginPage();
				f.main(null);
			}
		});
		
		JButton btnNewButton = new JButton("<html>View Clients<br>\r\nTrasactions</html>");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());
				Form form = new Form();
				form.param("ID", args);

				String res2 = target.path("rest").path("lawos").path("lrs").path("transactions").path("client").request()
						.accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
				
				JSONObject json = null;
				String str = "";
				try {
					json = new JSONObject(res2);
					if(json.get("size").equals("0")){
						JOptionPane.showMessageDialog(frame, "There is no trasactions available.");
					}
					else{
						for(int i=0;i<Integer.parseInt(json.get("size").toString());i++){
							JSONArray arr = json.getJSONArray("results_array");
							//str+=arr.getJSONObject(i).getString("ClientID") + "    ";
							str+=arr.getJSONObject(i).getString("Date") + "   ";
							str+=arr.getJSONObject(i).getString("Time") + "    ";
							str+=arr.getJSONObject(i).getString("Description") + "   ";
							str+="\n";
							//System.out.println(arr.getJSONObject(i).getString("Name"));

						}
						JOptionPane.showMessageDialog(frame, str);
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
							.addGap(155)
							.addComponent(lblClientForm))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(45)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(43)
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
											.addGap(97)
											.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(25, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientForm)
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(24)
							.addComponent(label_1)
							.addGap(24)
							.addComponent(label_2)
							.addGap(24)
							.addComponent(label_3)
							.addGap(24)
							.addComponent(label_4))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(name_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(surname_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(id_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(unwillingness_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addComponent(comments_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(btnSubmit)
						.addComponent(button))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}

}
