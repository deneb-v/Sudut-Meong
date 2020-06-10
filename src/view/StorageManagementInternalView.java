package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeHandler;
import controller.ProductHandler;
import controller.RoleHandler;
import controller.VoucherHandler;
import model.EmployeeModel;
import model.Model;
import model.ProductModel;
import model.RoleModel;

public class StorageManagementInternalView extends JInternalFrame implements ActionListener {
	
	private JTable tbl_data;
	private JTextField txt_price;
	private JTextField txt_name;
	private JTextField txt_stock;
	private JTextField txt_updPrice;
	private JTextField txt_updName;
	private JTextField txt_delPrice;
	private JTextField txt_delName;
	private JTextField txt_delStock;
	private JTextArea txt_description;
	private JButton btn_insert;
	private JComboBox cb_updID;
	private JTextArea txt_updDesc;
	private JButton btn_update;
	private JComboBox cb_delID;
	private JTextArea txt_delDesc;
	private JButton btn_delete;
	private JTextField txt_stkStock;
	private JTextField txt_stkName;
	private JTextField txt_stkNewStock;
	private JButton btn_addStock;
	private JComboBox cb_stkID;
	private JTextArea txt_stkDesc;

	public StorageManagementInternalView() {
		setBounds(0, 0, 759, 735);
		setTitle("Storage Management");
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setBounds(0, 0, 751, 706);
		getContentPane().add(container);
		container.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 735, 304);
		container.add(scrollPane);
		
		tbl_data = new JTable();
		scrollPane.setViewportView(tbl_data);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 322, 735, 366);
		container.add(tabbedPane);
		
		JPanel insertTab = new JPanel();
		insertTab.setLayout(null);
		tabbedPane.addTab("Add New Product", null, insertTab, null);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName.setBounds(199, 46, 97, 16);
		insertTab.add(lblProductName);
		
		JLabel lblNewLabel_2 = new JLabel("Product Price");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(150, 179, 146, 16);
		insertTab.add(lblNewLabel_2);
		
		txt_price = new JTextField();
		txt_price.setColumns(10);
		txt_price.setBounds(325, 174, 194, 26);
		insertTab.add(txt_price);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(325, 41, 194, 26);
		insertTab.add(txt_name);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product Stock");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(188, 220, 108, 16);
		insertTab.add(lblNewLabel_2_1);
		
		txt_stock = new JTextField();
		txt_stock.setColumns(10);
		txt_stock.setBounds(325, 215, 194, 26);
		insertTab.add(txt_stock);
		
		JLabel lblDescription = new JLabel("Product Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(171, 84, 125, 16);
		insertTab.add(lblDescription);
		
		btn_insert = new JButton("Add New Product");
		btn_insert.setBounds(272, 264, 175, 29);
		insertTab.add(btn_insert);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(329, 84, 187, 72);
		insertTab.add(scrollPane_1);
		
		txt_description = new JTextArea();
		txt_description.setLineWrap(true);
		scrollPane_1.setViewportView(txt_description);
		
		JPanel addStockPanel = new JPanel();
		tabbedPane.addTab("Add Stock", null, addStockPanel, null);
		addStockPanel.setLayout(null);
		
		JLabel lblProductName_1_2 = new JLabel("Product Name");
		lblProductName_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName_1_2.setBounds(196, 71, 97, 16);
		addStockPanel.add(lblProductName_1_2);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Current Stock");
		lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_2.setBounds(147, 204, 146, 16);
		addStockPanel.add(lblNewLabel_2_2_2);
		
		txt_stkStock = new JTextField();
		txt_stkStock.setEditable(false);
		txt_stkStock.setColumns(10);
		txt_stkStock.setBounds(322, 199, 194, 26);
		addStockPanel.add(txt_stkStock);
		
		txt_stkName = new JTextField();
		txt_stkName.setEditable(false);
		txt_stkName.setColumns(10);
		txt_stkName.setBounds(322, 66, 194, 26);
		addStockPanel.add(txt_stkName);
		
		JLabel lblDescription_1_2 = new JLabel("Product Description");
		lblDescription_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription_1_2.setBounds(168, 109, 125, 16);
		addStockPanel.add(lblDescription_1_2);
		
		btn_addStock = new JButton("Add Stock");
		btn_addStock.setBounds(275, 274, 175, 29);
		addStockPanel.add(btn_addStock);
		
		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Product ID");
		lblNewLabel_2_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_2.setBounds(212, 29, 81, 16);
		addStockPanel.add(lblNewLabel_2_1_1_1_2);
		
		cb_stkID = new JComboBox();
		cb_stkID.setBounds(321, 24, 197, 26);
		addStockPanel.add(cb_stkID);
		
		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setEnabled(false);
		scrollPane_2_1.setBounds(326, 109, 187, 72);
		addStockPanel.add(scrollPane_2_1);
		
		txt_stkDesc = new JTextArea();
		txt_stkDesc.setLineWrap(true);
		txt_stkDesc.setEditable(false);
		scrollPane_2_1.setViewportView(txt_stkDesc);
		
		JLabel lblNewLabel_2_2_2_1 = new JLabel("Stock to add");
		lblNewLabel_2_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_2_1.setBounds(147, 242, 146, 16);
		addStockPanel.add(lblNewLabel_2_2_2_1);
		
		txt_stkNewStock = new JTextField();
		txt_stkNewStock.setColumns(10);
		txt_stkNewStock.setBounds(322, 237, 194, 26);
		addStockPanel.add(txt_stkNewStock);
		
		JPanel updateTab = new JPanel();
		tabbedPane.addTab("Update Product", null, updateTab, null);
		updateTab.setLayout(null);
		
		JLabel lblProductName_1 = new JLabel("Product Name");
		lblProductName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName_1.setBounds(191, 90, 97, 16);
		updateTab.add(lblProductName_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Product Price");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(142, 223, 146, 16);
		updateTab.add(lblNewLabel_2_2);
		
		txt_updPrice = new JTextField();
		txt_updPrice.setColumns(10);
		txt_updPrice.setBounds(317, 218, 194, 26);
		updateTab.add(txt_updPrice);
		
		txt_updName = new JTextField();
		txt_updName.setColumns(10);
		txt_updName.setBounds(317, 85, 194, 26);
		updateTab.add(txt_updName);
		
		JLabel lblDescription_1 = new JLabel("Product Description");
		lblDescription_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription_1.setBounds(163, 128, 125, 16);
		updateTab.add(lblDescription_1);
		
		btn_update = new JButton("Update product");
		btn_update.setBounds(268, 265, 175, 29);
		updateTab.add(btn_update);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Product ID");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(207, 48, 81, 16);
		updateTab.add(lblNewLabel_2_1_1_1);
		
		cb_updID = new JComboBox();
		cb_updID.setBounds(316, 43, 197, 26);
		updateTab.add(cb_updID);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(321, 128, 187, 72);
		updateTab.add(scrollPane_2);
		
		txt_updDesc = new JTextArea();
		txt_updDesc.setLineWrap(true);
		scrollPane_2.setViewportView(txt_updDesc);
		
		JPanel deleteTab = new JPanel();
		deleteTab.setLayout(null);
		tabbedPane.addTab("Delete Product", null, deleteTab, null);
		
		JLabel lblProductName_1_1 = new JLabel("Product Name");
		lblProductName_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProductName_1_1.setBounds(192, 68, 97, 16);
		deleteTab.add(lblProductName_1_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Product Price");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2_1.setBounds(143, 202, 146, 16);
		deleteTab.add(lblNewLabel_2_2_1);
		
		txt_delPrice = new JTextField();
		txt_delPrice.setEditable(false);
		txt_delPrice.setBounds(318, 197, 194, 26);
		deleteTab.add(txt_delPrice);
		
		txt_delName = new JTextField();
		txt_delName.setEditable(false);
		txt_delName.setBounds(318, 63, 194, 26);
		deleteTab.add(txt_delName);
		
		JLabel lblNewLabel_2_1_1_2 = new JLabel("Product Stock");
		lblNewLabel_2_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_2.setBounds(181, 243, 108, 16);
		deleteTab.add(lblNewLabel_2_1_1_2);
		
		txt_delStock = new JTextField();
		txt_delStock.setEditable(false);
		txt_delStock.setBounds(318, 238, 194, 26);
		deleteTab.add(txt_delStock);
		
		JLabel lblDescription_1_1 = new JLabel("Product Description");
		lblDescription_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription_1_1.setBounds(164, 106, 125, 16);
		deleteTab.add(lblDescription_1_1);
		
		btn_delete = new JButton("Delete Product");
		btn_delete.setBounds(269, 279, 175, 29);
		deleteTab.add(btn_delete);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Product ID");
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_1.setBounds(208, 26, 81, 16);
		deleteTab.add(lblNewLabel_2_1_1_1_1);
		
		cb_delID = new JComboBox();
		cb_delID.setBounds(317, 21, 197, 26);
		deleteTab.add(cb_delID);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(322, 107, 187, 72);
		deleteTab.add(scrollPane_3);
		
		txt_delDesc = new JTextArea();
		txt_delDesc.setLineWrap(true);
		txt_delDesc.setEditable(false);
		scrollPane_3.setViewportView(txt_delDesc);
		
		btn_delete.addActionListener(this);
		btn_insert.addActionListener(this);
		btn_update.addActionListener(this);
		btn_addStock.addActionListener(this);
		cb_delID.addActionListener(this);
		cb_updID.addActionListener(this);
		cb_stkID.addActionListener(this);
		
		fillTable(tbl_data);
		fillIdComboBox(cb_delID);
		fillIdComboBox(cb_updID);
		fillIdComboBox(cb_stkID);
		refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
		refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
		refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
	}
	
	//method untuk mengambil data dari database dan mengisi JTable dengan data tersebut
	private void fillTable(JTable table) {
		List<Model> data = ProductHandler.getInstance().getAllData();
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("ID");
		tableColumn.add("Product Name");
		tableColumn.add("Description");
		tableColumn.add("Price");
		tableColumn.add("Stock");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};
		for (Model d : data) {
			Vector<Object> e = new Vector<Object>();
			e.add( ((ProductModel)d).getId() );
			e.add(((ProductModel)d).getName());
			e.add(((ProductModel)d).getDescription());
			e.add(((ProductModel)d).getPrice());
			e.add(((ProductModel)d).getStock());
			model.addRow(e);
		}
		table.setModel(model);
	}
	
	//method untuk mengisi comboBox dengan id product yang ada
	private void fillIdComboBox(JComboBox cb) {
		List<Model> data = ProductHandler.getInstance().getAllData();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (Model x : data) {
			model.addElement(Integer.toString(x.getId()));
		}
		cb.setModel(model);
	}

	//method untuk melakukan update isi pada textfield yang ada pada menu restock jika ada perubahan pada comboBox id pada menu restock
	private void refreshStkCombo(JComboBox cb, JTextField txt_name, JTextArea txt_desc, JTextField txt_currStock ) {
		if(cb.getSelectedItem()!=null) {			
			int id = Integer.parseInt(cb.getSelectedItem().toString());
			Model data = ProductHandler.getInstance().find(id);
			txt_name.setText(((ProductModel)data).getName());
			txt_desc.setText(((ProductModel)data).getDescription());
			txt_currStock.setText(Integer.toString(((ProductModel)data).getStock()));
		}
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu update jika ada perubahan pada comboBox id pada menu update
	private void refreshUpdateCombo(JComboBox cb, JTextField txt_name, JTextArea txt_desc, JTextField txt_price ) {
		if(cb.getSelectedItem()!=null) {			
			int id = Integer.parseInt(cb.getSelectedItem().toString());
			Model data = ProductHandler.getInstance().find(id);
			txt_name.setText(((ProductModel)data).getName());
			txt_desc.setText(((ProductModel)data).getDescription());
			txt_price.setText(Integer.toString(((ProductModel)data).getPrice()));
		}
	}
	
	//method untuk melakukan update isi pada textfield yang ada pada menu delete jika ada perubahan pada comboBox id pada menu delete
	private void refreshDeleteCombo(JComboBox cb, JTextField txt_name, JTextArea txt_desc, JTextField txt_price, JTextField txt_stock ) {
		if(cb.getSelectedItem()!=null) {			
			int id = Integer.parseInt(cb.getSelectedItem().toString());
			Model data = ProductHandler.getInstance().find(id);
			txt_name.setText(((ProductModel)data).getName());
			txt_desc.setText(((ProductModel)data).getDescription());
			txt_price.setText(Integer.toString(((ProductModel)data).getPrice()));
			txt_stock.setText(Integer.toString(((ProductModel)data).getStock()));
		}
	}
	
	//method untuk validasi data product baru pada saat insert data
	private Boolean validateInsert(String name, String desc, String price, String stock) {
		ProductHandler controller = ProductHandler.getInstance();
		if(!controller.checkName(name)) {
			JOptionPane.showMessageDialog(this, "Name cannot be empty!");
			return false;
		}
		if(!controller.checkDesc(desc)) {
			JOptionPane.showMessageDialog(this, "Description cannot be empty");
			return false;
		}
		if(!controller.checkPrice(price)) {
			JOptionPane.showMessageDialog(this, "Price must be numeric and above zero!");
			return false;
		}
		if(!controller.checkStock(stock)) {
			JOptionPane.showMessageDialog(this, "Stock must be numeric and above zero!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data product baru pada saat update data
	private Boolean validateUpdate(String id, String name, String desc, String price) {
		ProductHandler controller = ProductHandler.getInstance();
		if(!controller.checkID(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(this, "ID cannot be null and must be exist!");
		}
		if(!controller.checkName(name)) {
			JOptionPane.showMessageDialog(this, "Name cannot be empty!");
			return false;
		}
		if(!controller.checkDesc(desc)) {
			JOptionPane.showMessageDialog(this, "Description cannot be empty");
			return false;
		}
		if(!controller.checkPrice(price)) {
			JOptionPane.showMessageDialog(this, "Price must be numeric and above zero!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data product pada saat add stock
	private Boolean validateAddStock(String id, String stock) {
		ProductHandler controller = ProductHandler.getInstance();
		if(!controller.checkID(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(this, "ID cannot be null and must be exist!");
		}
		if(!controller.checkStock(stock)) {
			JOptionPane.showMessageDialog(this, "Stock must be numeric and above zero!");
			return false;
		}
		return true;
	}
	
	//method untuk validasi data product id pada saat delete data 
	private boolean validateDelete(String id) {
		ProductHandler controller = ProductHandler.getInstance();
		if(!controller.checkID(Integer.parseInt(id))) {
			JOptionPane.showMessageDialog(this, "ID cannot be null and must be exist!");
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_insert) {
			//jika tombol insert ditekan
			String name = txt_name.getText();
			String desc = txt_description.getText();
			String price = txt_price.getText();
			String stock = txt_stock.getText();
			if(validateInsert(name, desc, price, stock)) {
				ProductModel res = ProductHandler.getInstance().insertData(name, desc, Integer.parseInt(price), Integer.parseInt(stock));
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Add new product success!");
					fillTable(tbl_data);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					fillIdComboBox(cb_stkID);
					refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
					refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
					refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
				}
				else {
					JOptionPane.showMessageDialog(this, "Add new product failed!");
				}
			}
		}
		
		if(e.getSource() == btn_update) {
			//jika tombol update ditekan
			String id = cb_updID.getSelectedItem().toString();
			String name = txt_updName.getText();
			String desc = txt_updDesc.getText();
			String price = txt_updPrice.getText();
			if(validateUpdate(id, name, desc, price)) {
				ProductModel item = (ProductModel) ProductHandler.getInstance().find(Integer.parseInt(id));
				ProductModel res = ProductHandler.getInstance().updateData(Integer.parseInt(id), name, desc, Integer.parseInt(price), item.getStock());
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Update product success!");
					fillTable(tbl_data);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					fillIdComboBox(cb_stkID);
					refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
					refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
					refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
				}
				else {
					JOptionPane.showMessageDialog(this, "Update product failed!");
				}
			}
		}
		
		if(e.getSource() == btn_addStock) {
			//jika tombol add stock ditekan
			String id  = cb_updID.getSelectedItem().toString();
			String addStock = txt_stkNewStock.getText();
			if(validateAddStock(id, addStock)) {
				ProductModel res = ProductHandler.getInstance().reStock(Integer.parseInt(id), Integer.parseInt(addStock));
				if(res!=null) {
					JOptionPane.showMessageDialog(this, "Add stock success!");
					fillTable(tbl_data);
					fillIdComboBox(cb_delID);
					fillIdComboBox(cb_updID);
					fillIdComboBox(cb_stkID);
					refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
					refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
					refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
				}
				else {
					JOptionPane.showMessageDialog(this, "Add stock failed!");
				}
			}
		}
		
		if(e.getSource() == btn_delete) {
			//jika tombol delete ditekan
			ProductHandler controller = ProductHandler.getInstance();
			String id = cb_delID.getSelectedItem().toString();
			int confirm = JOptionPane.showConfirmDialog(this, "Delete this product?");
			if(confirm==0) {				
				if (validateDelete(id)) {
					if(controller.deleteData(Integer.parseInt(id))) {
						JOptionPane.showMessageDialog(this, "Delete success!");
						fillTable(tbl_data);
						fillIdComboBox(cb_delID);
						fillIdComboBox(cb_updID);
						fillIdComboBox(cb_stkID);
						refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
						refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
						refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
					}
					else {
						JOptionPane.showMessageDialog(this, "Delete fail!");
					}
				}
			}
		}
		
		if(e.getSource() == cb_stkID) {
			//jika terjadi perubahan pilihan pada comboBox id pada menu add stock
			refreshStkCombo(cb_stkID, txt_stkName, txt_stkDesc, txt_stkStock);
		}
		
		if(e.getSource() == cb_updID) {
			//jika terjadi perubahan pilihan pada comboBox id pada menu update
			refreshUpdateCombo(cb_updID, txt_updName, txt_updDesc, txt_updPrice);
		}
		
		if(e.getSource() == cb_delID) {
			//jika terjadi perubahan pilihan pada comboBox id pada menu delete
			refreshDeleteCombo(cb_delID, txt_delName, txt_delDesc, txt_delPrice, txt_delStock);
		}
	}

	
}
