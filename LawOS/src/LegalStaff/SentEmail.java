package LegalStaff;

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

public class SentEmail {

	private JFrame frame;
	private JTextField to_txt;
	private JTextField title_txt;
	private JTextField body_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentEmail window = new SentEmail();
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
	public SentEmail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSendEmail = new JLabel("Send Email");
		lblSendEmail.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel lblTo = new JLabel("To:");

		to_txt = new JTextField();
		to_txt.setColumns(10);

		JLabel lblTitle = new JLabel("Title:");

		title_txt = new JTextField();
		title_txt.setColumns(10);

		JLabel lblBody = new JLabel("Body:");

		body_txt = new JTextField();
		body_txt.setColumns(10);

		JButton btnSent = new JButton("Send");
		btnSent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String to = to_txt.getText();
				String title = title_txt.getText();
				String body = body_txt.getText();
				loginPage lp = new loginPage();

				ClientConfig config = new ClientConfig();
				Client client = ClientBuilder.newClient(config);
				WebTarget target = client.target(getBaseURI());


				if(to.equals("") || title.equals("") || body.equals("")){
					JOptionPane.showMessageDialog(frame, "Fill all fields");
					return;
				}



				Form form = new Form();
				form.param("From", lp.UserID);
				form.param("To", to);
				form.param("Title", title);
				form.param("Body", body);

				String res2 = target.path("rest").path("lawos").path("emails").path("send").request()
						.accept(MediaType.APPLICATION_JSON)
						.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);

				if (res2.equals("1"))
					JOptionPane.showMessageDialog(frame, "Email send Successfully");
				else
					JOptionPane.showMessageDialog(frame, "Email didn't sent!");


			}
		});

		JButton btnGoback = new JButton("GoBack");
		btnGoback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				MailBox f = new MailBox();
				f.main(null);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(171)
										.addComponent(lblSendEmail))
								.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblBody, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblTo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
										.addGap(45)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(btnSent, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
														.addGap(40)
														.addComponent(btnGoback))
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(body_txt)
														.addComponent(title_txt, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
														.addComponent(to_txt, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(51, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblSendEmail)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTo)
								.addComponent(to_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTitle)
								.addComponent(title_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBody)
								.addComponent(body_txt, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGoback)
								.addComponent(btnSent))
						.addGap(49))
				);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/LawOSREST/").build();
	}
}
