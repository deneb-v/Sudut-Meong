package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controller.EmployeeHandler;
import controller.RoleHandler;
import controller.VoucherHandler;
import model.EmployeeModel;
import model.Model;
import model.RoleModel;
import model.VoucherModel;

public class PromoManagementInternalView extends JInternalFrame implements ActionListener{
	
	private JTable tbl_data;
	private JTextField txt_discount;
	private JTextField txt_updDiscount;
	private JTextField txt_delDiscount;
	private JComboBox cb_date;
	private JComboBox cb_month;
	private JComboBox cb_year;
	private JButton btn_insert;
	private JComboBox cb_updID;
	private JComboBox cb_updDate;
	private JComboBox cb_updMonth;
	private JComboBox cb_updYear;
	private JButton btn_update;
	private JComboBox cb_delID;
	private JTextField txt_delDate;
	private JButton btn_delete;

	public PromoManagementInternalView() {
		setBounds(0, 0, 759, 735);
		setTitle("Promo Management");
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 751, 741);
		getContentPane().add(container);
		container.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 735, 304);
		container.add(scrollPane);
		
		tbl_data = new JTable();
		scrollPane.setViewportView(tbl_data);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 322, 735, 385);
		container.add(tabbedPane);
		
		JPanel insertTab = new JPanel();
		insertTab.setLayout(null);
		tabbedPane.addTab("Create New Voucher", null, insertTab, null);
		
		JLabel lblNewLabel_2 = new JLabel("Discount (1-100)%");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(128, 117, 130, 16);
		insertTab.add(lblNewLabel_2);
		
		txt_discount = new JTextField();
		txt_discount.setColumns(10);
		txt_discount.setBounds(287, 112, 194, 26);
		insertTab.add(txt_discount);
		
		JLabel lblNewLabel_2_1 = new JLabel("Valid Date");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(150, 163, 108, 16);
		insertTab.add(lblNewLabel_2_1);
		
		btn_insert = new JButton("Create New Voucher");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_insert.setBounds(266, 204, 175, 29);
		insertTab.add(btn_insert);
		
		cb_date = new JComboBox();
		cb_date.setBounds(287, 159, 79, 27);
		cb_date.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		insertTab.add(cb_date);
		
		cb_month = new JComboBox();
		cb_month.setBounds(366, 159, 116, 27);
		cb_month.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		insertTab.add(cb_month);
		
		cb_year = new JComboBox();
		cb_year.setBounds(482, 159, 77, 27);
		cb_year.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		insertTab.add(cb_year);
		
		JPanel updateTab = new JPanel();
		tabbedPane.addTab("Update Voucher", null, updateTab, null);
		updateTab.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Product ID");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(155, 97, 108, 16);
		updateTab.add(lblNewLabel_2_1_1_1);
		
		cb_updID = new JComboBox();
		cb_updID.setBounds(290, 93, 197, 26);
		updateTab.add(cb_updID);
		
		JLabel lblNewLabel_2_2 = new JLabel("Discount (1-100)%");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(134, 141, 129, 16);
		updateTab.add(lblNewLabel_2_2);
		
		txt_updDiscount = new JTextField();
		txt_updDiscount.setColumns(10);
		txt_updDiscount.setBounds(292, 136, 194, 26);
		updateTab.add(txt_updDiscount);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Valid Date");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(155, 187, 108, 16);
		updateTab.add(lblNewLabel_2_1_1);
		
		btn_update = new JButton("Update Voucher");
		btn_update.setBounds(266, 234, 175, 29);
		updateTab.add(btn_update);
		
		cb_updDate = new JComboBox();
		cb_updDate.setBounds(292, 183, 79, 27);
		cb_updDate.setModel(new DefaultComboBoxModel(new String[] {"Date", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		updateTab.add(cb_updDate);
		
		cb_updMonth = new JComboBox();
		cb_updMonth.setBounds(371, 183, 116, 27);
		cb_updMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		updateTab.add(cb_updMonth);
		
		cb_updYear = new JComboBox();
		cb_updYear.setBounds(487, 183, 77, 27);
		cb_updYear.setModel(new DefaultComboBoxModel(new String[] {"Year", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981"}));
		updateTab.add(cb_updYear);
		
		JPanel deleteTab = new JPanel();
		deleteTab.setLayout(null);
		tabbedPane.addTab("Delete Product", null, deleteTab, null);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Product ID");
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_1.setBounds(168, 97, 108, 16);
		deleteTab.add(lblNewLabel_2_1_1_1_1);
		
		cb_delID = new JComboBox();
		cb_delID.setBounds(303, 93, 197, 26);
		deleteTab.add(cb_delID);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Discount");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1.setBounds(168, 141, 108, 16);
		deleteTab.add(lblNewLabel_2_2_1);
		
		txt_delDiscount = new JTextField();
		txt_delDiscount.setEditable(false);
		txt_delDiscount.setBounds(305, 136, 194, 26);
		deleteTab.add(txt_delDiscount);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Valid Date");
		lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_2.setBounds(168, 187, 108, 16);
		deleteTab.add(lblNewLabel_2_1_1_2);
		
		btn_delete = new JButton("Delete Voucher");
		btn_delete.setBounds(273, 233, 175, 29);
		deleteTab.add(btn_delete);
		
		txt_delDate = new JTextField();
		txt_delDate.setEditable(false);
		txt_delDate.setBounds(305, 183, 194, 27);
		deleteTab.add(txt_delDate);
		
		btn_insert.addActionListener(this);
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);
		cb_updID.addActionListener(this);
		cb_delID.addActionListener(this);
		
		fillIdComboBox(cb_delID);
		fillIdComboBox(cb_updID);
		fillTable(tbl_data);
		
		refreshCombo(cb_updID, txt_updDiscount, cb_updDate, cb_updMonth, cb_updYear);
		refreshCombo(cb_delID, txt_delDiscount, txt_delDate);
	}
	
	//method untuk mengambil data dari database dan mengisi JTable dengan data tersebut
	private void fillTable(JTable table) {
		List<Model> data = VoucherHandler.getInstance().getAllData();
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("ID");
		tableColumn.add("Discount");
		tableColumn.add("Valid Date");
		tableColumn.add("Status");
		
		DefaultTableModel model = new DefaultTableModel(tableColumn,0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};;
		for (Model d : data) {
			Vector<Object> e = new Vector<Object>();
			e.add( ((VoucherModel)d).getId() );
			e.add( ((VoucherModel)d).getDiscount() );
			e.add( ((VoucherModel)d).getValidDate().toString() );
			e.add( ((VoucherModel)d).getStatus() );
			
			
			model.addRow(e);
			
		}
		table.setModel(model);
	}
	
	//method untuk validasi data voucher pada saat insert voucher
	private Boolean validateInsert(String discount, String date) {
		VoucherHandler controller = VoucherHandler.getInstance();
		if(controller.checkDiscount(discount)==false) {
			JOptionPane.showMessageDialog(this, "Discount cannot be empty, must be in numeric, and must be between 1-100(inclusive)!");
			return false;
		}
		if(controller.checkDate(date)==false) {
			JOptionPane.showMessageDialog(this, "Date must be in the future!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data voucher pada saat update voucher
	private Boolean validateUpdate(int voucherID, String discount, String date) {
		VoucherHandler controller = VoucherHandler.getInstance();
		if(controller.checkID(voucherID)==false) {
			JOptionPane.showMessageDialog(this, "Voucher id must be exist!");
			return false;
		}
		if(controller.checkDiscount(discount)==false) {
			JOptionPane.showMessageDialog(this, "Discount cannot be empty, must be in numeric, and must be between 1-100(inclusive)!");
			return false;
		}
		if(controller.checkDate(date)==false) {
			JOptionPane.showMessageDialog(this, "Date must be in the future!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data voucher id pada saat delete voucher
	private Boolean validateDelete(int voucherID) {
		if(!VoucherHandler.getInstance().checkID(voucherID)) {
			JOptionPane.showMessageDialog(this, "Voucher id must be exist!");
			return false;
		}
		return true;
	}
	
	//method untuk mengisi comboBox dengan id voucher yang ada
	private void fillIdComboBox(JComboBox cb) {
		List<Model> data = VoucherHandler.getInstance().getAllData();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (Model x : data) {
			model.addElement(Integer.toString(x.getId()));
		}
		cb.setModel(model);
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu update jika ada perubahan pada comboBox id pada menu update
	private void refreshCombo(JComboBox cb, JTextField txt_discount, JComboBox cb_date, JComboBox cb_month, JComboBox cb_year) {
		if(cb.getSelectedItem()!=null) {			
			int id = Integer.parseInt(cb.getSelectedItem().toString());
			Model data = VoucherHandler.getInstance().find(id);
			
			long discount = ((VoucherModel)data).getDiscount().longValue();
			txt_discount.setText(Long.toString(discount));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(((VoucherModel)data).getValidDate());
			
			cb_date.setSelectedItem(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
			cb_month.setSelectedIndex(cal.get(Calendar.MONTH)+1);
			cb_year.setSelectedItem(Integer.toString(cal.get(Calendar.YEAR)));
		}
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu delete jika ada perubahan pada comboBox id pada menu delete
	private void refreshCombo(JComboBox cb, JTextField txt_discount, JTextField txt_validDate) {
		if(cb.getSelectedItem()!=null) {			
			int id = Integer.parseInt(cb.getSelectedItem().toString());
			Model data = VoucherHandler.getInstance().find(id);
			
			long discount = ((VoucherModel)data).getDiscount().longValue();
			txt_discount.setText(Long.toString(discount)+"%");
			
			txt_validDate.setText(((VoucherModel)data).getValidDate().toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_insert) {
			//jika tombol insert ditekan
			String discount = txt_discount.getText();
			String date = cb_year.getSelectedItem().toString();
			date = date.concat("-"+cb_month.getSelectedIndex());
			date = date.concat("-"+cb_date.getSelectedItem().toString());
			if(validateInsert(discount, date)) {
				VoucherModel res = VoucherHandler.getInstance().insertData(Float.parseFloat(discount), date);
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Create success!\nVoucher ID = "+res.getId()+"\nDiscount = "+res.getDiscount()+"%");
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					fillTable(tbl_data);
					
					refreshCombo(cb_updID, txt_updDiscount, cb_updDate, cb_updMonth, cb_updYear);
					refreshCombo(cb_delID, txt_delDiscount, txt_delDate);
				}
				else {
					JOptionPane.showMessageDialog(this, "Create fail!");
				}
			}
		}
		if(e.getSource()==btn_update) {
			//jika tombol update ditekan
			System.out.println(cb_updID.getSelectedItem().toString());
			int voucherID = Integer.parseInt(cb_updID.getSelectedItem().toString());
			String discount = txt_updDiscount.getText();
			String date = cb_updYear.getSelectedItem().toString();
			date = date.concat("-"+cb_updMonth.getSelectedIndex());
			date = date.concat("-"+cb_updDate.getSelectedItem().toString());
			if(validateUpdate(voucherID, discount, date)) {
				VoucherModel res = VoucherHandler.getInstance().updateData(voucherID, Float.parseFloat(discount), date);
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Update success!");
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					fillTable(tbl_data);
					refreshCombo(cb_updID, txt_updDiscount, cb_updDate, cb_updMonth, cb_updYear);
					refreshCombo(cb_delID, txt_delDiscount, txt_delDate);
				}
				else {
					JOptionPane.showMessageDialog(this, "Update fail!");
				}
			}
		}
		if(e.getSource()==btn_delete) {
			//jika tombol delete ditekan
			int voucherID = Integer.parseInt(cb_delID.getSelectedItem().toString());
			
			int confirm = JOptionPane.showConfirmDialog(this, "Delete this voucher?");
			if(confirm==0) {
				if(validateDelete(voucherID)) {
					
					if(VoucherHandler.getInstance().deleteData(voucherID)) {
						JOptionPane.showMessageDialog(this, "Delete success!");
						fillIdComboBox(cb_delID);
						fillIdComboBox(cb_updID);
						fillTable(tbl_data);
						
						refreshCombo(cb_updID, txt_updDiscount, cb_updDate, cb_updMonth, cb_updYear);
						refreshCombo(cb_delID, txt_delDiscount, txt_delDate);
					}
					else {
						JOptionPane.showMessageDialog(this, "Delete fail!");
					}
				}
			}
		}
		
		if(e.getSource()==cb_updID) {
			//jika terjadi perubahan pilihan pada comboBox id pada menu update
			refreshCombo(cb_updID, txt_updDiscount, cb_updDate, cb_updMonth, cb_updYear);
		}
		if(e.getSource()==cb_delID) {
			//jika terjadi perubahan pilihan pada comboBox id pada menu delete
			refreshCombo(cb_delID, txt_delDiscount, txt_delDate);
		}
	}

}
