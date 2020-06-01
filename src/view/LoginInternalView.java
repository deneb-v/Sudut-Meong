package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import controller.EmployeeHandler;
import model.EmployeeModel;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginInternalView extends JInternalFrame implements ActionListener{
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JButton btn_login;

	public LoginInternalView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Login");
		setBounds(0, 0, 406, 254);
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 398, 225);
		getContentPane().add(container);
		container.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(76, 70, 68, 16);
		container.add(lblNewLabel);
		
		txt_username = new JTextField();
		txt_username.setBounds(169, 65, 136, 26);
		container.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(76, 115, 68, 16);
		container.add(lblPassword);
		
		txt_password = new JPasswordField();
		txt_password.setColumns(10);
		txt_password.setBounds(169, 110, 136, 26);
		container.add(txt_password);
		
		btn_login = new JButton("Login");
		btn_login.setBounds(140, 161, 117, 29);
		container.add(btn_login);
		
		JLabel lblNewLabel_1 = new JLabel("SUDUT MEONG");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(124, 21, 150, 25);
		container.add(lblNewLabel_1);
		
		btn_login.addActionListener(this);
	}
	
	private Boolean validateLogin(String username, String password) {
		if(!EmployeeHandler.getInstance().checkUsernamePassword(username, password)) {
			JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_login) {
			String username = txt_username.getText();
			String password = new String(txt_password.getPassword());
			
			if(validateLogin(username, password)) {
				EmployeeModel onLog = EmployeeHandler.getInstance().login(username, password);
				if(onLog!=null) {
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Incorrect username or password!");
				}
			}
		}
	}
}
