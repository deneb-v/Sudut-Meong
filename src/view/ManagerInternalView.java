package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.EmployeeHandler;
import controller.ProductHandler;
import controller.RoleHandler;
import controller.TransactionHandler;
import model.EmployeeModel;
import model.Model;
import model.ProductModel;
import model.RoleModel;
import model.TransactionItemModel;
import model.TransactionModel;

public class ManagerInternalView extends JInternalFrame implements ActionListener, MouseListener{
	
	private JTable tbl_data;
	private JTextField txt_username;
	private JTextField txt_name;
	private JTextField txt_salary;
	private JTextField txt_updName;
	private JTextField txt_updUsername;
	private JTextField txt_updSalary;
	private JTable tbl_dataEmployee;
	private JComboBox cb_repMonth;
	private JComboBox cb_repYear;
	private JButton btn_report;
	private JLabel txt_tableTitle;
	private JComboBox cb_date;
	private JComboBox cb_month;
	private JComboBox cb_year;
	private JComboBox cb_role;
	private JButton btn_insert;
	private JComboBox cb_updID;
	private JComboBox cb_updDate;
	private JComboBox cb_updMonth;
	private JComboBox cb_updYear;
	private JComboBox cb_updRole;
	private JButton btn_update;
	private JComboBox cb_delID;
	private JTextField txt_delName;
	private JTextField txt_delDOB;
	private JTextField txt_delUsername;
	private JTextField txt_delRole;
	private JTextField txt_delSalary;
	private JButton btn_delete;

	public ManagerInternalView() {
		setBounds(0, 0, 759, 735);
		setTitle("Manager");
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 751, 741);
		getContentPane().add(container);
		container.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 751, 735);
		container.add(tabbedPane);
		
		JPanel reportTab = new JPanel();
		tabbedPane.addTab("View Report", null, reportTab, null);
		reportTab.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 212, 730, 465);
		reportTab.add(scrollPane);
		
		tbl_data = new JTable();
		scrollPane.setViewportView(tbl_data);
		
		JLabel Month = new JLabel("Month");
		Month.setHorizontalAlignment(SwingConstants.RIGHT);
		Month.setBounds(191, 34, 97, 16);
		reportTab.add(Month);
		
		cb_repMonth = new JComboBox();
		cb_repMonth.setBounds(316, 29, 196, 27);
		cb_repMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		reportTab.add(cb_repMonth);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setBounds(191, 83, 97, 16);
		reportTab.add(lblYear);
		
		cb_repYear = new JComboBox();
		cb_repYear.setBounds(316, 78, 196, 27);
		cb_repYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		reportTab.add(cb_repYear);
		
		btn_report = new JButton("Generate Report");
		btn_report.setBounds(301, 125, 144, 29);
		reportTab.add(btn_report);
		
		txt_tableTitle = new JLabel("New label");
		txt_tableTitle.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)), new EmptyBorder(10, 0, 5, 0)));
		txt_tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txt_tableTitle.setBounds(6, 177, 730, 30);
		reportTab.add(txt_tableTitle);
		
		JPanel employeeTab = new JPanel();
		tabbedPane.addTab("Employee Management", null, employeeTab, null);
		employeeTab.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 6, 730, 262);
		employeeTab.add(scrollPane_1);
		
		tbl_dataEmployee = new JTable();
		scrollPane_1.setViewportView(tbl_dataEmployee);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(6, 280, 730, 397);
		employeeTab.add(tabbedPane_1);
		
		JPanel insertTab = new JPanel();
		insertTab.setLayout(null);
		tabbedPane_1.addTab("Add New Employee", null, insertTab, null);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(186, 65, 81, 16);
		insertTab.add(lblNewLabel);
		
		JLabel lblRole = new JLabel("Employee Role");
		lblRole.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole.setBounds(170, 148, 97, 16);
		insertTab.add(lblRole);
		
		JLabel lblNewLabel_2 = new JLabel("Employee Username");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(121, 190, 146, 16);
		insertTab.add(lblNewLabel_2);
		
		txt_username = new JTextField();
		txt_username.setColumns(10);
		txt_username.setBounds(296, 185, 194, 26);
		insertTab.add(txt_username);
		
		cb_role = new JComboBox();
		cb_role.setBounds(295, 143, 196, 27);
		insertTab.add(cb_role);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(296, 60, 194, 26);
		insertTab.add(txt_name);
		
		JLabel lblNewLabel_2_1 = new JLabel("Salary");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(186, 231, 81, 16);
		insertTab.add(lblNewLabel_2_1);
		
		txt_salary = new JTextField();
		txt_salary.setColumns(10);
		txt_salary.setBounds(296, 226, 194, 26);
		insertTab.add(txt_salary);
		
		cb_date = new JComboBox();
		cb_date.setBounds(295, 101, 79, 27);
		cb_date.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		insertTab.add(cb_date);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setBounds(186, 106, 81, 16);
		insertTab.add(lblDateOfBirth);
		
		cb_month = new JComboBox();
		cb_month.setBounds(374, 101, 116, 27);
		cb_month.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		insertTab.add(cb_month);
		
		cb_year = new JComboBox();
		cb_year.setBounds(490, 101, 77, 27);
		cb_year.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		insertTab.add(cb_year);
		
		btn_insert = new JButton("Add New Employee");
		btn_insert.setBounds(275, 267, 175, 29);
		insertTab.add(btn_insert);
		
		JPanel updateTab = new JPanel();
		updateTab.setLayout(null);
		tabbedPane_1.addTab("Update Employee Data", null, updateTab, null);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(156, 82, 110, 16);
		updateTab.add(lblNewLabel_1);
		
		JLabel lblRole_1 = new JLabel("Employee Role");
		lblRole_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole_1.setBounds(169, 207, 97, 16);
		updateTab.add(lblRole_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("Employee Username");
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3.setBounds(120, 166, 146, 16);
		updateTab.add(lblNewLabel_2_3);
		
		txt_updName = new JTextField();
		txt_updName.setColumns(10);
		txt_updName.setBounds(295, 77, 194, 26);
		updateTab.add(txt_updName);
		
		cb_updRole = new JComboBox();
		cb_updRole.setBounds(294, 202, 196, 27);
		updateTab.add(cb_updRole);
		
		txt_updUsername = new JTextField();
		txt_updUsername.setColumns(10);
		txt_updUsername.setBounds(295, 161, 194, 26);
		updateTab.add(txt_updUsername);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Employee ID");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(185, 41, 81, 16);
		updateTab.add(lblNewLabel_2_1_1);
		
		cb_updID = new JComboBox();
		cb_updID.setBounds(295, 36, 194, 26);
		updateTab.add(cb_updID);
		
		cb_updDate = new JComboBox();
		cb_updDate.setBounds(294, 119, 79, 27);
		cb_updDate.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		updateTab.add(cb_updDate);
		
		JLabel lblDateOfBirth_1 = new JLabel("Date Of Birth");
		lblDateOfBirth_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth_1.setBounds(169, 124, 97, 16);
		updateTab.add(lblDateOfBirth_1);
		
		cb_updMonth = new JComboBox();
		cb_updMonth.setBounds(373, 119, 116, 27);
		cb_updMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		updateTab.add(cb_updMonth);
		
		cb_updYear = new JComboBox();
		cb_updYear.setBounds(489, 119, 77, 27);
		cb_updYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		updateTab.add(cb_updYear);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Salary");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1.setBounds(185, 248, 81, 16);
		updateTab.add(lblNewLabel_2_2_1);
		
		txt_updSalary = new JTextField();
		txt_updSalary.setColumns(10);
		txt_updSalary.setBounds(295, 243, 194, 26);
		updateTab.add(txt_updSalary);
		
		btn_update = new JButton("Update Employee Data");
		btn_update.setBounds(275, 288, 175, 29);
		updateTab.add(btn_update);
		
		JPanel deleteTab = new JPanel();
		deleteTab.setLayout(null);
		tabbedPane_1.addTab("Fire Employee", null, deleteTab, null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Employee Name");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setBounds(174, 83, 110, 16);
		deleteTab.add(lblNewLabel_1_1);
		
		JLabel lblRole_1_1 = new JLabel("Employee Role");
		lblRole_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRole_1_1.setBounds(187, 208, 97, 16);
		deleteTab.add(lblRole_1_1);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Employee Username");
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_3_1.setBounds(138, 167, 146, 16);
		deleteTab.add(lblNewLabel_2_3_1);
		
		txt_delName = new JTextField();
		txt_delName.setEditable(false);
		txt_delName.setBounds(313, 78, 194, 26);
		deleteTab.add(txt_delName);
		
		txt_delRole = new JTextField();
		txt_delRole.setEditable(false);
		txt_delRole.setBounds(312, 203, 196, 27);
		deleteTab.add(txt_delRole);
		
		txt_delUsername = new JTextField();
		txt_delUsername.setEditable(false);
		txt_delUsername.setBounds(313, 162, 194, 26);
		deleteTab.add(txt_delUsername);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Employee ID");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(203, 42, 81, 16);
		deleteTab.add(lblNewLabel_2_1_1_1);
		
		cb_delID = new JComboBox();
		cb_delID.setBounds(313, 37, 194, 26);
		deleteTab.add(cb_delID);
		
		txt_delDOB = new JTextField();
		txt_delDOB.setEditable(false);
		txt_delDOB.setBounds(312, 120, 194, 27);
		deleteTab.add(txt_delDOB);
		
		JLabel lblDateOfBirth_1_1 = new JLabel("Date Of Birth");
		lblDateOfBirth_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth_1_1.setBounds(187, 125, 97, 16);
		deleteTab.add(lblDateOfBirth_1_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Salary");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1_1.setBounds(203, 249, 81, 16);
		deleteTab.add(lblNewLabel_2_2_1_1);
		
		txt_delSalary = new JTextField();
		txt_delSalary.setEditable(false);
		txt_delSalary.setBounds(313, 244, 194, 26);
		deleteTab.add(txt_delSalary);
		
		btn_delete = new JButton("Fire Employee");
		btn_delete.setBounds(275, 289, 175, 29);
		deleteTab.add(btn_delete);
		
		fillEmployeeTable(tbl_dataEmployee);
		cb_updID.addActionListener(this);
		cb_delID.addActionListener(this);
		
		fillIdComboBox(cb_updID);
		fillIdComboBox(cb_delID);
		fillRoleComboBox(cb_role);
		fillRoleComboBox(cb_updRole);
		
		refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
		refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
		
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);
		btn_insert.addActionListener(this);
		btn_report.addActionListener(this);
		tbl_data.addMouseListener(this);
	}
	
	//method untuk mengambil data dari database dan mengisi table laporan transaksi dengan data tersebut
	private void fillReportTable(JTable table, List<TransactionModel> data) {
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("Transaction ID");
		tableColumn.add("Purchase date");
		tableColumn.add("Employee ID");
		tableColumn.add("Payment type");
		tableColumn.add("Voucher ID");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0) {
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};
		for (TransactionModel d : data) {
			Vector<Object> e = new Vector<Object>();
			e.add( d.getId() );
			e.add(d.getPurchaseDateTime().toString());
			e.add(d.getEmployeeID());
			e.add(d.getPaymentType());
			if(d.getVoucherID()==0) {
				e.add("No Voucher");
			}
			else {				
				e.add(d.getVoucherID());
			}
			
			model.addRow(e);
			
		}
		table.setModel(model);
	}
	
	//method untuk mengambil data dari database dan mengisi table laporan detail transaksi dengan data tersebut
	private void fillReportDetailTable(JTable table, List<TransactionItemModel> data) {
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("Transaction ID");
		tableColumn.add("ProductID");
		tableColumn.add("Product Name");
		tableColumn.add("Price");
		tableColumn.add("Quantity");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0) {
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};
		for (TransactionItemModel d : data) {
			Vector<Object> e = new Vector<Object>();
			e.add(d.getTransactionID());
			e.add(d.getProductID());
			
			ProductModel product = (ProductModel) ProductHandler.getInstance().find(d.getProductID());
			e.add(product.getName());
			e.add(product.getPrice());
			e.add(d.getQuantity());
			
			model.addRow(e);
			
		}
		table.setModel(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Manager report menu action
		if(e.getSource() == btn_report) {
			//jika tombol generate report ditekan
			TransactionHandler transactionController = TransactionHandler.getInstance();
			
			if(cb_repMonth.getSelectedIndex()==0||cb_repYear.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "Month and year must be selected!");
			}
			else {				
				int month = cb_repMonth.getSelectedIndex();
				int year = Integer.parseInt(cb_repYear.getSelectedItem().toString());
				List<TransactionModel> data = transactionController.getTransactionReport(month, year);
				fillReportTable(tbl_data, data);
				txt_tableTitle.setText(cb_repMonth.getSelectedItem().toString()+" "+cb_repYear.getSelectedItem().toString()+" "+"Transaction Report");
			}
		}
		
		
		//Employee management menu action
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
					fillEmployeeTable(tbl_dataEmployee);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
					refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
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
					fillEmployeeTable(tbl_dataEmployee);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
					refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
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
						fillEmployeeTable(tbl_dataEmployee);
						fillIdComboBox(cb_delID);
						fillIdComboBox(cb_updID);
						refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
						refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
					}
					else {
						JOptionPane.showMessageDialog(this, "fire fail!");
					}
				}
			}
		}
		
		if(e.getSource() == cb_delID) {
			//jika ada perubahan pilihan pada comboBox id pada menu delete
			refreshCombo(cb_delID,txt_delName,txt_delDOB,txt_delUsername,txt_delRole,txt_delSalary);
		}
		
		if(e.getSource() == cb_updID) {
			//jika ada perubahan pilihan pada comboBox id pada menu update
			refreshCombo(cb_updID, txt_updName, cb_updDate, cb_updMonth, cb_updYear, txt_updUsername, cb_updRole, txt_updSalary);
		}
		
	}
	
	//method untuk validasi data employee baru pada saat insert employee
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
	
	//method untuk mengambil data dari database dan mengisi table employee dengan data tersebut
	private void fillEmployeeTable(JTable table) {
		List<Model> data = EmployeeHandler.getInstance().getAllData();
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("ID");
		tableColumn.add("Employee Name");
		tableColumn.add("Role");
		tableColumn.add("Username");
		tableColumn.add("Date Of Birth");
		tableColumn.add("Salary");
		tableColumn.add("Status");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};;
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2) {
			//jika ada double click pada table laporan transaksi
			tbl_data.getSelectedRow();
			TableModel model = tbl_data.getModel();
			int id = Integer.parseInt(model.getValueAt(tbl_data.getSelectedRow(), 0).toString());
			fillReportDetailTable(tbl_data, TransactionHandler.getInstance().getTransactionDetail(id));
			txt_tableTitle.setText("Transaction Detail");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
