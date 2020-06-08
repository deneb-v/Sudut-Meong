package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeHandler;
import controller.RoleHandler;
import model.EmployeeModel;
import model.Model;
import model.RoleModel;

public class HumanResourceInternalView extends JInternalFrame implements ActionListener{
	
	private JTextField txt_username;
	private JTextField txt_name;
	private JTextField txt_updUsername;
	private JTextField txt_updSalary;
	private JButton btn_insert;
	private DefaultTableModel table = new DefaultTableModel();
	private JTable tbl_data;
	private JComboBox cb_updID;
	private JTextField txt_updName;
	private JComboBox cb_updDate;
	private JComboBox cb_updMonth;
	private JComboBox cb_updYear;
	private JComboBox cb_updRole;
	private JComboBox cb_delID;
	private JTextField txt_delName;
	private JTextField txt_delDOB;
	private JTextField txt_delUsername;
	private JTextField txt_delRole;
	private JTextField txt_delSalary;
	private JComboBox cb_resID;
	private JTextField txt_resUsername;
	private JComboBox cb_role;
	private JComboBox cb_date;
	private JComboBox cb_month;
	private JComboBox cb_year;
	private JTextField txt_salary;
	private JButton btn_update;
	private JButton btn_delete;
	private JButton btn_resetPass;
	private JTextField txt_resPassword;

	public HumanResourceInternalView() {
		setBounds(0, 0, 759, 735);
		setTitle("Human Resource");
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBounds(6, 6, 723, 741);
		getContentPane().add(container);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 711, 304);
		container.add(scrollPane);
		
		tbl_data = new JTable();
		scrollPane.setViewportView(tbl_data);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 322, 711, 354);
		container.add(tabbedPane);
		
		JPanel insertTab = new JPanel();
		insertTab.setLayout(null);
		tabbedPane.addTab("Add New Employee", null, insertTab, null);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(187, 45, 81, 16);
		insertTab.add(lblNewLabel);
		
		JLabel lblRole = new JLabel("Employee Role");
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole.setBounds(171, 128, 97, 16);
		insertTab.add(lblRole);
		
		JLabel lblNewLabel_2 = new JLabel("Employee Username");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(122, 170, 146, 16);
		insertTab.add(lblNewLabel_2);
		
		txt_username = new JTextField();
		txt_username.setColumns(10);
		txt_username.setBounds(297, 165, 194, 26);
		insertTab.add(txt_username);
		
		cb_role = new JComboBox();
		cb_role.setBounds(296, 123, 196, 27);
		insertTab.add(cb_role);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(297, 40, 194, 26);
		insertTab.add(txt_name);
		
		JLabel lblNewLabel_2_1 = new JLabel("Salary");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(187, 211, 81, 16);
		insertTab.add(lblNewLabel_2_1);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(297, 206, 194, 26);
		insertTab.add(txt_salary);
		
		cb_date = new JComboBox();
		cb_date.setBounds(296, 81, 79, 27);
		cb_date.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cb_date.setSelectedIndex(0);
		insertTab.add(cb_date);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setBounds(187, 86, 81, 16);
		insertTab.add(lblDateOfBirth);
		
		cb_month = new JComboBox();
		cb_month.setBounds(375, 81, 116, 27);
		cb_month.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		insertTab.add(cb_month);
		
		cb_year = new JComboBox();
		cb_year.setBounds(491, 81, 77, 27);
		cb_year.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		cb_year.setSelectedIndex(0);
		insertTab.add(cb_year);
		
		btn_insert = new JButton("Add New Employee");
		btn_insert.setBounds(255, 247, 175, 29);
		btn_insert.addActionListener(this);
		insertTab.add(btn_insert);
		
		JPanel updateTab = new JPanel();
		updateTab.setLayout(null);
		tabbedPane.addTab("Update Employee Data", null, updateTab, null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(162, 67, 110, 16);
		updateTab.add(lblNewLabel_1);
		
		JLabel lblRole_1 = new JLabel("Employee Role");
		lblRole_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole_1.setBounds(175, 192, 97, 16);
		updateTab.add(lblRole_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("Employee Username");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setBounds(126, 151, 146, 16);
		updateTab.add(lblNewLabel_2_3);
		
		txt_updName = new JTextField();
		txt_updName.setColumns(10);
		txt_updName.setBounds(301, 62, 194, 26);
		updateTab.add(txt_updName);
		
		cb_updRole = new JComboBox();
		cb_updRole.setBounds(300, 187, 196, 27);
		updateTab.add(cb_updRole);
		
		txt_updUsername = new JTextField();
		txt_updUsername.setColumns(10);
		txt_updUsername.setBounds(301, 146, 194, 26);
		updateTab.add(txt_updUsername);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Employee ID");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(191, 26, 81, 16);
		updateTab.add(lblNewLabel_2_1_1);
		
		cb_updID = new JComboBox();
		cb_updID.setBounds(301, 21, 194, 26);
		fillIdComboBox(cb_updID);
		updateTab.add(cb_updID);
		
		cb_updDate = new JComboBox();
		cb_updDate.setBounds(300, 104, 79, 27);
		cb_updDate.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		updateTab.add(cb_updDate);
		
		JLabel lblDateOfBirth_1 = new JLabel("Date Of Birth");
		lblDateOfBirth_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth_1.setBounds(175, 109, 97, 16);
		updateTab.add(lblDateOfBirth_1);
		
		cb_updMonth = new JComboBox();
		cb_updMonth.setBounds(379, 104, 116, 27);
		cb_updMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		updateTab.add(cb_updMonth);
		
		cb_updYear = new JComboBox();
		cb_updYear.setBounds(495, 104, 77, 27);
		cb_updYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		updateTab.add(cb_updYear);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Salary");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1.setBounds(191, 233, 81, 16);
		updateTab.add(lblNewLabel_2_2_1);
		
		txt_updSalary = new JTextField();
		txt_updSalary.setColumns(10);
		txt_updSalary.setBounds(301, 228, 194, 26);
		updateTab.add(txt_updSalary);
		
		btn_update = new JButton("Update Employee Data");
		btn_update.setBounds(252, 273, 175, 29);
		updateTab.add(btn_update);
		
		JPanel deleteTab = new JPanel();
		deleteTab.setLayout(null);
		tabbedPane.addTab("Fire Employee", null, deleteTab, null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Employee Name");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(174, 67, 110, 16);
		deleteTab.add(lblNewLabel_1_1);
		
		JLabel lblRole_1_1 = new JLabel("Employee Role");
		lblRole_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole_1_1.setBounds(187, 192, 97, 16);
		deleteTab.add(lblRole_1_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Employee Username");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1.setBounds(138, 151, 146, 16);
		deleteTab.add(lblNewLabel_2_3_1);
		
		txt_delName = new JTextField();
		txt_delName.setEditable(false);
		txt_delName.setBounds(313, 62, 194, 26);
		deleteTab.add(txt_delName);
		
		txt_delRole = new JTextField();
		txt_delRole.setEditable(false);
		txt_delRole.setBounds(312, 187, 196, 27);
		deleteTab.add(txt_delRole);
		
		txt_delUsername = new JTextField();
		txt_delUsername.setEditable(false);
		txt_delUsername.setBounds(313, 146, 194, 26);
		deleteTab.add(txt_delUsername);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Employee ID");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(203, 26, 81, 16);
		deleteTab.add(lblNewLabel_2_1_1_1);
		
		cb_delID = new JComboBox();
		cb_delID.setBounds(313, 21, 194, 26);
		fillIdComboBox(cb_delID);
		deleteTab.add(cb_delID);
		
		txt_delDOB = new JTextField();
		txt_delDOB.setEditable(false);
		txt_delDOB.setBounds(312, 104, 194, 27);
		deleteTab.add(txt_delDOB);
		
		JLabel lblDateOfBirth_1_1 = new JLabel("Date Of Birth");
		lblDateOfBirth_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth_1_1.setBounds(187, 109, 97, 16);
		deleteTab.add(lblDateOfBirth_1_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Salary");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1_1.setBounds(203, 233, 81, 16);
		deleteTab.add(lblNewLabel_2_2_1_1);
		
		txt_delSalary = new JTextField();
		txt_delSalary.setEditable(false);
		txt_delSalary.setBounds(313, 228, 194, 26);
		deleteTab.add(txt_delSalary);
		
		btn_delete = new JButton("Fire Employee");
		btn_delete.setBounds(264, 273, 175, 29);
		deleteTab.add(btn_delete);
		
		JPanel resetTab = new JPanel();
		resetTab.setLayout(null);
		tabbedPane.addTab("Reset Employee Password", null, resetTab, null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Employee Username");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(162, 124, 133, 16);
		resetTab.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("Employee New Password");
		lblNewLabel_2_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1_1.setBounds(131, 166, 164, 16);
		resetTab.add(lblNewLabel_2_3_1_1);
		
		txt_resPassword = new JTextField();
		txt_resPassword.setEditable(false);
		txt_resPassword.setBounds(324, 161, 194, 26);
		resetTab.add(txt_resPassword);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Employee ID");
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_1.setBounds(214, 82, 81, 16);
		resetTab.add(lblNewLabel_2_1_1_1_1);
		
		cb_resID = new JComboBox();
		cb_resID.setBounds(324, 77, 194, 26);
		resetTab.add(cb_resID);
		
		txt_resUsername = new JTextField();
		txt_resUsername.setEditable(false);
		txt_resUsername.setBounds(323, 119, 194, 27);
		resetTab.add(txt_resUsername);
		
		btn_resetPass = new JButton("Reset Employee Password");
		btn_resetPass.setBounds(242, 203, 205, 29);
		resetTab.add(btn_resetPass);
		
		fillTable(tbl_data);
		cb_updID.addActionListener(this);
		cb_delID.addActionListener(this);
		cb_resID.addActionListener(this);
		
		fillRoleComboBox(cb_role);
		fillRoleComboBox(cb_updRole);
		fillIdComboBox(cb_resID);
		
		refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
		refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
		refreshCombo(cb_resID, txt_resUsername);
		
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_resetPass.addActionListener(this);
	}
	
	//method untuk validasi data employee pada saat melakukan insert emplouee
	private Boolean validateInsert(String name, String username, String dob, String salary, int roleID) {
		if(EmployeeHandler.getInstance().checkName(name)==false) {
			JOptionPane.showMessageDialog(this, "Name cannot be empty!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkDOB(dob)==false) {
			JOptionPane.showMessageDialog(this, "Date of Birth cannot be empty, must be in valid format, and must be in the past!");
			return false;
		}
		if (EmployeeHandler.getInstance().checkRole(roleID)==false) {
			JOptionPane.showMessageDialog(this, "Role cannot be empty and must be exist!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkUsername(username)==false) {
			JOptionPane.showMessageDialog(this, "Username cannot be empty and must be unique!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkSalary(salary)==false) {
			JOptionPane.showMessageDialog(this, "Salary cannot be empty, must be numeric, and must be above zero!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data employee baru pada saat melakukan update employee
	private Boolean validateUpdate(int employeeId,String name, String username, String dob, String salary, int roleID) {
		if(EmployeeHandler.getInstance().checkEmployeeID(employeeId)==false) {
			JOptionPane.showMessageDialog(this, "Employee ID cannot be empty and must be exist!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkName(name)==false) {
			JOptionPane.showMessageDialog(this, "Name cannot be empty!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkDOB(dob)==false) {
			JOptionPane.showMessageDialog(this, "Date of Birth cannot be empty, must be in valid format, and must be in the past!");
			return false;
		}
		if (EmployeeHandler.getInstance().checkRole(roleID)==false) {
			JOptionPane.showMessageDialog(this, "Role cannot be empty and must be exist!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkUsername(username,employeeId)==false) {
			JOptionPane.showMessageDialog(this, "Username cannot be empty and must be unique!");
			return false;
		}
		if(EmployeeHandler.getInstance().checkSalary(salary)==false) {
			JOptionPane.showMessageDialog(this, "Salary cannot be empty, must be numeric, and must be above zero!");
			return false;
		}
		return true;
	}

	//method untuk validasi data id employee yang akan dihapus atau dipecat
	private Boolean validateDelete(int employeeId) {
		if(EmployeeHandler.getInstance().checkEmployeeID(employeeId)==false) {
			JOptionPane.showMessageDialog(this, "Employee ID cannot be empty and must be exist!");
			return false;
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_insert) {
			//jika tombol insert ditekan
			EmployeeHandler employeeController = EmployeeHandler.getInstance();
			int roleID = cb_role.getSelectedIndex();
			String name = txt_name.getText();
			String username = txt_username.getText();
			String dOB = cb_year.getSelectedItem().toString();
			dOB = dOB.concat("-"+cb_month.getSelectedIndex());
			dOB = dOB.concat("-"+cb_date.getSelectedItem().toString());
			
			if(validateInsert(name, username, dOB, txt_salary.getText(), roleID)) {
				int salary = Integer.parseInt(txt_salary.getText());
				Model res = employeeController.insertData(roleID, name, username, dOB, salary, "Employed");
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Insert success!\n"+"username= "+((EmployeeModel)res).getUsername()+"\npassword= "+((EmployeeModel)res).getPassword());
					fillTable(tbl_data);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_resID);
					fillIdComboBox(cb_updID);
					refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
					refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
					refreshCombo(cb_resID, txt_resUsername);
				}
				else {
					JOptionPane.showMessageDialog(this, "Insert fail!");
				}
			}
		}
		
		if(e.getSource() == btn_update) {
			//jika tombol update ditekan
			EmployeeHandler employeeController = EmployeeHandler.getInstance();
			int employeeID = Integer.parseInt(cb_updID.getSelectedItem().toString());
			int roleID = cb_updRole.getSelectedIndex();
			String name = txt_updName.getText();
			String username = txt_updUsername.getText();
			String dOB = cb_updYear.getSelectedItem().toString();
			dOB = dOB.concat("-"+cb_updMonth.getSelectedIndex());
			dOB = dOB.concat("-"+cb_updDate.getSelectedItem().toString());
			if(validateUpdate(employeeID,name, username, dOB, txt_updSalary.getText(), roleID)) {
				int salary = Integer.parseInt(txt_updSalary.getText());
				Model res = employeeController.updateData(employeeID, roleID, name, username, dOB, salary);
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Update success!");
					fillTable(tbl_data);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_resID);
					fillIdComboBox(cb_updID);
					refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
					refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
					refreshCombo(cb_resID, txt_resUsername);
				}
				else {
					JOptionPane.showMessageDialog(this, "Update fail!");
				}
			}
		}
		
		if(e.getSource() == btn_delete) {
			//jika tombol delete ditekan
			EmployeeHandler employeeController = EmployeeHandler.getInstance();
			int employeeID = Integer.parseInt(cb_delID.getSelectedItem().toString());
			int confirm = JOptionPane.showConfirmDialog(this, "Fire this employee?");
			if(confirm==0) {				
				if (validateDelete(employeeID)) {
					Model res = employeeController.fireEmployee(employeeID);
					if(res!=null) {
						JOptionPane.showMessageDialog(this, "fire success!");
						fillTable(tbl_data);
						fillIdComboBox(cb_delID);
						fillIdComboBox(cb_resID);
						fillIdComboBox(cb_updID);
						refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
						refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
						refreshCombo(cb_resID, txt_resUsername);
					}
					else {
						JOptionPane.showMessageDialog(this, "fire fail!");
					}
				}
			}
		}
		
		if(e.getSource() == btn_resetPass) {
			//jika tombol reset password ditekan
			EmployeeHandler employeeController = EmployeeHandler.getInstance();
			int id = Integer.parseInt(cb_resID.getSelectedItem().toString());
			int confirm = JOptionPane.showConfirmDialog(this, "Reset password?");
			System.out.println(confirm);
			if(confirm==0) {
				Model res = employeeController.resetPassword(id);
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Password reset success!");
					txt_resPassword.setText(((EmployeeModel)res).getPassword());
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Password reset failed!");
				}
			}
		}
		
		if(e.getSource() == cb_delID) {
			//jika ada perubahan pilihan pada combobox id untuk menu delete
			refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
		}
		
		if(e.getSource() == cb_updID) {
			//jika ada perubahan pilihan pada combobox id untuk menu update
			refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
		}
		
		if(e.getSource() == cb_resID) {
			//jika ada perubahan pilihan pada combobox id untuk menu reset password
			refreshCombo(cb_resID, txt_resUsername);
		}
		
	}
	
	//method untuk mengisi comboBox dengan id employee yang ada
	private void fillIdComboBox(JComboBox cb) {
		List<Model> data = EmployeeHandler.getInstance().getAllData();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (Model x : data) {
			model.addElement(Integer.toString(x.getId()));
		}
		cb.setModel(model);
	}
	
	//method untuk mengisi comboBox dengan role yang ada 
	private void fillRoleComboBox(JComboBox cb) {
		List<Model> data = RoleHandler.getInstance().getAllData();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement("Role");
		for (Model x : data) {
			model.addElement(((RoleModel)x).getName());
		}
		cb.setModel(model);
	}
	
	//method untuk mengambil data dari database dan mengisi JTable dengan data tersebut
	private void fillTable(JTable table) {
		List<Model> data = EmployeeHandler.getInstance().getAllData();
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("ID");
		tableColumn.add("Employee Name");
		tableColumn.add("Role");
		tableColumn.add("Username");
		tableColumn.add("Date Of Birth");
		tableColumn.add("Salary");
		tableColumn.add("Status");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0);
		for (Model d : data) {
			Vector<Object> e = new Vector<Object>();
			e.add( ((EmployeeModel)d).getId() );
			e.add(((EmployeeModel)d).getName());
			
			RoleModel role = (RoleModel) RoleHandler.getInstance().find(((EmployeeModel)d).getRoleID());
			e.add(role.getName());
			
			e.add(((EmployeeModel)d).getUsername());
			e.add(((EmployeeModel)d).getDOB().toString());
			e.add(((EmployeeModel)d).getSalary());
			e.add(((EmployeeModel)d).getStatus());
			
			model.addRow(e);
			
		}
		table.setModel(model);
	}

	//method untuk melakukan update isi pada textfield yang ada pada menu update jika ada perubahan pada comboBox id pada menu update
	private void refreshCombo(JComboBox cb, JTextField txt_name, JComboBox cb_Date, JComboBox cb_Month, JComboBox cb_Year, JTextField txt_username, JComboBox cb_role, JTextField txt_salary) {
		int id = Integer.parseInt(cb.getSelectedItem().toString());
		Model data = EmployeeHandler.getInstance().find(id);
		
		txt_name.setText(((EmployeeModel)data).getName());
		txt_username.setText(((EmployeeModel)data).getUsername());
		txt_salary.setText(Integer.toString(((EmployeeModel)data).getSalary()));
		
		cb_role.setSelectedIndex(((EmployeeModel)data).getRoleID());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(((EmployeeModel)data).getDOB());
		
		cb_Date.setSelectedItem(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
		cb_Month.setSelectedIndex(cal.get(Calendar.MONTH)+1);
		cb_Year.setSelectedItem(Integer.toString(cal.get(Calendar.YEAR)));
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu fire/pecat jika ada perubahan pada comboBox id pada menu fire/pecat
	private void refreshCombo(JComboBox cb, JTextField txt_name, JTextField txt_date, JTextField txt_username, JTextField txt_role, JTextField txt_salary) {
		int id = Integer.parseInt(cb.getSelectedItem().toString());
		Model data = EmployeeHandler.getInstance().find(id);
		
		txt_name.setText(((EmployeeModel)data).getName());
		txt_username.setText(((EmployeeModel)data).getUsername());
		txt_salary.setText(Integer.toString(((EmployeeModel)data).getSalary()));
		txt_date.setText(((EmployeeModel)data).getDOB().toString());
		
		RoleModel role = (RoleModel) RoleHandler.getInstance().find(((EmployeeModel)data).getRoleID());
		txt_role.setText(role.getName());
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu reset password jika ada perubahan pada comboBox id pada menu reset password
	private void refreshCombo(JComboBox cb, JTextField txt_username) {
		int id = Integer.parseInt(cb.getSelectedItem().toString());
		Model data = EmployeeHandler.getInstance().find(id);
		txt_username.setText(((EmployeeModel)data).getUsername());

	}
}
