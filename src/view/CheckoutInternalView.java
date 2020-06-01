package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.CartHandler;
import controller.EmployeeHandler;
import controller.TransactionHandler;
import controller.VoucherHandler;
import model.CartModel;
import model.ProductModel;
import model.TransactionModel;
import model.VoucherModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class CheckoutInternalView extends JInternalFrame implements ActionListener{
	private JTable tbl_data;
	private JTextField txt_voucher;
	private JComboBox cb_paymentMethod;
	private JTextField txt_custMoney;
	private JCheckBox chk_voucher;
	private JLabel txt_totalPrice;
	private JButton btn_applyVoucher;
	private JButton btn_pay;

	public CheckoutInternalView() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Cashier - Checkout");
		setBounds(0, 0, 691, 521);
		getContentPane().setLayout(null);
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBounds(0, 0, 683, 492);
		getContentPane().add(container);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 667, 212);
		container.add(scrollPane);
		
		tbl_data = new JTable();
		scrollPane.setViewportView(tbl_data);
		
		chk_voucher = new JCheckBox("Voucher");
		chk_voucher.setHorizontalAlignment(SwingConstants.RIGHT);
		chk_voucher.setHorizontalTextPosition(SwingConstants.RIGHT);
		chk_voucher.setBounds(167, 272, 83, 23);
		container.add(chk_voucher);
		
		txt_voucher = new JTextField();
		txt_voucher.setEditable(false);
		txt_voucher.setBounds(283, 271, 173, 26);
		container.add(txt_voucher);
		txt_voucher.setColumns(10);
		
		btn_applyVoucher = new JButton("Apply Voucher");
		btn_applyVoucher.setEnabled(false);
		btn_applyVoucher.setBounds(468, 271, 117, 29);
		container.add(btn_applyVoucher);
		
		JLabel lblNewLabel_1 = new JLabel("Payment Method");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(133, 314, 117, 16);
		container.add(lblNewLabel_1);
		
		cb_paymentMethod = new JComboBox();
		cb_paymentMethod.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Credit"}));
		cb_paymentMethod.setSelectedIndex(0);
		cb_paymentMethod.setBounds(283, 309, 173, 26);
		container.add(cb_paymentMethod);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Money");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(143, 352, 108, 16);
		container.add(lblNewLabel_2);
		
		txt_custMoney = new JTextField();
		txt_custMoney.setColumns(10);
		txt_custMoney.setBounds(283, 347, 173, 26);
		container.add(txt_custMoney);
		
		btn_pay = new JButton("Check Out");
		btn_pay.setBounds(277, 428, 117, 29);
		container.add(btn_pay);
		
		JLabel lblNewLabel = new JLabel("Total Price :");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(153, 391, 113, 25);
		container.add(lblNewLabel);
		
		txt_totalPrice = new JLabel("9999999 (Discount 100%)");
		txt_totalPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		txt_totalPrice.setBounds(283, 391, 302, 25);
		container.add(txt_totalPrice);
		
		chk_voucher.addActionListener(this);
		btn_applyVoucher.addActionListener(this);
		btn_pay.addActionListener(this);
		
		refresh();
	}
	
	private void fillTable() {
		List<CartModel> data = CartHandler.getInstance().getCartList();
		System.out.println(CartHandler.getInstance());
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
		};;
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
		tbl_data.setModel(model);
	}

	public void refresh() {
		fillTable();
		
		if(chk_voucher.isSelected()) {
			int voucherid = Integer.parseInt(txt_voucher.getText());
			String discount = Float.toString(((VoucherModel)VoucherHandler.getInstance().find(voucherid)).getDiscount());
			txt_totalPrice.setText(Integer.toString(TransactionHandler.getInstance().applyVoucher(voucherid))+" (Discount "+discount+"%)");
		}
		else {			
			txt_totalPrice.setText(Integer.toString(TransactionHandler.getInstance().calculateTotalPrice(0)));
		}
		txt_custMoney.setText("");
	}
	
	private Boolean validateApplyVoucher(String id) {
		if(!TransactionHandler.getInstance().checkVoucher(id)) {
			JOptionPane.showMessageDialog(this, "Voucher must be not used before and must be used before the valid date!");
			return false;
		}
		return true;
	}
	
	private Boolean validateCheckout(String paymentMethod, String voucherID, String money) {
		if(!TransactionHandler.getInstance().checkVoucher(voucherID)) {
			JOptionPane.showMessageDialog(this, "Voucher must be not used before and must be used before the valid date!");
			return false;
		}
		if(!TransactionHandler.getInstance().checkPaymentMethod(paymentMethod)) {
			JOptionPane.showMessageDialog(this, "Payment method must be used!");
			return false;
		}
		if(!TransactionHandler.getInstance().checkMoney(money, Integer.parseInt(voucherID))) {
			JOptionPane.showMessageDialog(this, "Customer money cannot be empty and must be enough!");
			return false; 
		}
		return true;
	}
	
	private Boolean validateCheckout(String paymentMethod, String money) {
		if(!TransactionHandler.getInstance().checkPaymentMethod(paymentMethod)) {
			JOptionPane.showMessageDialog(this, "Payment method must be used!");
			return false;
		}
		if(!TransactionHandler.getInstance().checkMoney(money, 0)) {
			JOptionPane.showMessageDialog(this, "Customer money cannot be empty and must be enough!");
			return false;
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_applyVoucher) {
			if(validateApplyVoucher(txt_voucher.getText())) {				
				refresh();
			}
		}
		
		if(e.getSource() == btn_pay) {
			String paymentMethod = cb_paymentMethod.getSelectedItem().toString();
			String voucherID = txt_voucher.getText();
			int employeeID = EmployeeHandler.getInstance().getOnLog().getId();
			String money = txt_custMoney.getText();
			TransactionModel res=null;
			if(chk_voucher.isSelected()) {				
				if(validateCheckout(paymentMethod, voucherID, money)) {
					res = TransactionHandler.getInstance().processTransaction(paymentMethod, Integer.parseInt(voucherID), Integer.parseInt(money), employeeID);
				}
			}
			else {
				if(validateCheckout(paymentMethod, money)) {
					res = TransactionHandler.getInstance().processTransaction(paymentMethod, 0, Integer.parseInt(money), employeeID);
				}
			}
			if(res!=null) {
				int change = TransactionHandler.getInstance().calculateChange(Integer.parseInt(money), res);
				JOptionPane.showMessageDialog(this, "Purchase success!\nChange = "+change);
				CartHandler.getInstance().emptyCart();
				System.out.println(CashierInternalView.getInstance().toString());
				CashierInternalView.getInstance().reset();
				refresh();
				chk_voucher.setSelected(false);
				txt_voucher.setText("");
				this.setVisible(false);
			}
		}
		
		if(e.getSource() == chk_voucher) {
			System.out.println(chk_voucher.isSelected());
			if(chk_voucher.isSelected()) {				
				txt_voucher.setEditable(true);
				btn_applyVoucher.setEnabled(true);
			}
			else {
				txt_voucher.setEditable(false);
				txt_voucher.setText("");
				btn_applyVoucher.setEnabled(false);
				refresh();

			}
		}
	}
}
