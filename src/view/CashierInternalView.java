package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Vector;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controller.CartHandler;
import controller.EmployeeHandler;
import controller.ProductHandler;
import controller.RoleHandler;
import model.CartModel;
import model.EmployeeModel;
import model.Model;
import model.ProductModel;
import model.RoleModel;

public class CashierInternalView extends JInternalFrame implements ActionListener, DocumentListener{
	private JTextField txt_productID;
	private JLabel txt_productName;
	private JLabel txt_totalPrice;
	private JButton btn_checkOut;
	private JButton btn_addCart;
	private JTable tbl_cart;
	private JTextField txt_quantity;
	private JLabel lblNewLabel;
	public static CashierInternalView view;
	
	public static synchronized CashierInternalView getInstance() {
		return (view == null) ? view = new CashierInternalView() : view;
	}

	private CashierInternalView() {
		setBounds(0, 0, 546, 407);
		getContentPane().setLayout(null);
		setTitle("Cashier - Cart");
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBounds(0, 0, 538, 380);
		getContentPane().add(container);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 11, 525, 140);
		container.add(scrollPane);
		
		tbl_cart = new JTable();
		scrollPane.setViewportView(tbl_cart);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(42, 176, 146, 16);
		container.add(lblNewLabel_2);
		
		txt_productID = new JTextField();
		txt_productID.setColumns(10);
		txt_productID.setBounds(217, 171, 194, 26);
		container.add(txt_productID);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product Name");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(88, 217, 100, 16);
		container.add(lblNewLabel_2_1);
		
		txt_productName = new JLabel();
		txt_productName.setBounds(217, 212, 194, 26);
		container.add(txt_productName);
		
		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPrice.setBounds(107, 301, 81, 16);
		container.add(lblTotalPrice);
		
		txt_totalPrice = new JLabel();
		txt_totalPrice.setText("RP.123123");
		txt_totalPrice.setBounds(217, 296, 194, 26);
		container.add(txt_totalPrice);
		
		btn_addCart = new JButton("Add To Cart");
		btn_addCart.setBounds(280, 335, 117, 29);
		container.add(btn_addCart);
		
		btn_checkOut = new JButton("Check Out");
		btn_checkOut.setEnabled(false);
		btn_checkOut.setBounds(141, 335, 117, 29);
		container.add(btn_checkOut);
		
		txt_quantity = new JTextField();
		txt_quantity.setColumns(10);
		txt_quantity.setBounds(217, 253, 194, 26);
		container.add(txt_quantity);
		
		lblNewLabel = new JLabel("Quantity");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(42, 258, 146, 16);
		container.add(lblNewLabel);
		
		fillTable();
		txt_totalPrice.setText(Integer.toString(CartHandler.getInstance().getTotalPrice()));
		
		btn_addCart.addActionListener(this);
		btn_checkOut.addActionListener(this);
		txt_productID.getDocument().addDocumentListener(this);
	}

	//method untuk validasi sebelum menambahkan barang kedalam item
	private Boolean validateAdd(String id, String quantity) {
		CartHandler controller = CartHandler.getInstance();
		if(!controller.checkID(id)) {
			JOptionPane.showMessageDialog(this, "Product ID cannot be empty and must exist!"); 
			return false;
		}
		if(!controller.checkQuantity(id, quantity)) {
			JOptionPane.showMessageDialog(this, "Quantity cannot below zero or more than product stock!");
			return false;
		}
		return true;
	}
	
	//method untuk mengambil data dari database dan mengisi JTable dengan data tersebut
	private void fillTable() {
		List<CartModel> data = CartHandler.getInstance().getCartList();
		Vector<String> tableColumn = new Vector<String>();
		tableColumn.add("Product ID");
		tableColumn.add("Product Name");
		tableColumn.add("Unit Price");
		tableColumn.add("Quantity");
		tableColumn.add("Price");
		DefaultTableModel model = new DefaultTableModel(tableColumn,0){
			public boolean isCellEditable(int row, int column)
		    {
		      return false;
		    }
		};
		for (CartModel d : data) {
			Vector<Object> e = new Vector<Object>();
			ProductModel item = d.getProduct();
			e.add(item.getId());
			e.add(item.getName());
			e.add(item.getPrice());
			e.add(d.getQuantity());
			int price = item.getPrice() * d.getQuantity();
			e.add(price);
			
			model.addRow(e);
		}
		tbl_cart.setModel(model);
	}
	
	//method untuk mengembalikan tampilan ke tampilan awal dan mengisi ulang JTable dengan data terbaru
	public void reset() {
		txt_productID.setText("");
		txt_quantity.setText("");
		fillTable();
		txt_totalPrice.setText(Integer.toString(CartHandler.getInstance().getTotalPrice()));
		btn_checkOut.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_addCart) {
			//jika tombol add to cart ditekan
			CartHandler controller = CartHandler.getInstance();
			String id = txt_productID.getText();
			String quantity = txt_quantity.getText();
			if(validateAdd(id, quantity)) {
				CartModel res = controller.insertData(Integer.parseInt(id), Integer.parseInt(quantity));
				if(res!=null) {
					txt_productID.setText("");
					txt_quantity.setText("");
					fillTable();
					txt_totalPrice.setText(Integer.toString(CartHandler.getInstance().getTotalPrice()));
					btn_checkOut.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(this, "Add item to cart failed!");
				}
			}
		}
		
		if(e.getSource() == btn_checkOut) {
			//jika tombol check out ditekan
			MainView.getInstance().openCheckoutFrame();
			System.out.println(CartHandler.getInstance());
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if(e.getDocument() == txt_productID.getDocument()) {
			//jika ada karakter yang dimasukan kedalam textfield ID
			if(!txt_productID.getText().isEmpty()) {				
				ProductModel item = (ProductModel) ProductHandler.getInstance().find(Integer.parseInt(txt_productID.getText()));
				if(item!=null) {
					txt_productName.setText(item.getName() );
				}
				else {
					txt_productName.setText("");
				}
			}
			else {
				txt_productName.setText("");
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		if(e.getDocument() == txt_productID.getDocument()) {
			//jika ada karakter yang dihapus kedalam textfield ID
			if(!txt_productID.getText().isEmpty()) {				
				ProductModel item = (ProductModel) ProductHandler.getInstance().find(Integer.parseInt(txt_productID.getText()));
				if(item!=null) {
					txt_productName.setText(item.getName() );
				}
				else {
					txt_productName.setText("");
				}
			}
			else {
				txt_productName.setText("");
			}
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	
}
