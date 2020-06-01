package controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import model.CartModel;
import model.Model;
import model.TransactionItemModel;
import model.TransactionModel;
import model.VoucherModel;

public class TransactionHandler extends Controller{
	
	private static TransactionHandler controller;
	private TransactionModel model;
	private TransactionItemModel detailModel;
	private CartHandler cart;

	private TransactionHandler() {
		model = new TransactionModel();
		detailModel = new TransactionItemModel();
		cart = CartHandler.getInstance();
	}
	
	public static synchronized TransactionHandler getInstance() {
		return (controller==null) ? controller = new TransactionHandler() : controller;
	}
	
	public int calculateTotalPrice(int voucherID) {
		int totalPrice = cart.getTotalPrice();
		if(voucherID!=0) {
			VoucherModel voucher = (VoucherModel) VoucherHandler.getInstance().find(voucherID);
			return (int) (totalPrice - (totalPrice * (voucher.getDiscount()/100)));
		}
		return totalPrice;
	}
	
	public int applyVoucher(int voucherID) {
		return calculateTotalPrice(voucherID);
	}
	
	public int calculateChange(int money, TransactionModel transaction) {
		return money - calculateTotalPrice(transaction.getVoucherID());
	}
	
	public TransactionModel processTransaction(String paymentType, int voucherID, int money, int employeeID) {
		
		VoucherHandler.getInstance().useVoucher(voucherID);
		Date purchaseDateTime = new Date(new java.util.Date().getTime());
		TransactionModel transaction = (TransactionModel) new TransactionModel(purchaseDateTime, voucherID, employeeID, paymentType).insertData();
		Vector<CartModel> c = (Vector<CartModel>) cart.getCartList();
		for (CartModel cartModel : c) {
			ProductHandler.getInstance().useStock(cartModel.getProduct().getId(), cartModel.getQuantity());
			new TransactionItemModel(transaction.getId(), cartModel.getProduct().getId(), cartModel.getQuantity()).insertData();
		}
		return transaction;
	}
	
	public List<TransactionModel> getTransactionReport(int month, int year){
		return model.getTransactionReport(month, year);
	}
	
	public List<TransactionItemModel> getTransactionDetail(int transactionID){
		return detailModel.getTransactionDetail(transactionID);
	}

	@Override
	public List<Model> getAllData() {
		return model.getData("`Transaction`");
	}

	@Override
	public Model find(int id) {
		return model.findData(id);
	}

	public Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public Boolean checkVoucher(String id) {
		if(!isNumber(id)) {
			return false;
		}
		if(!VoucherHandler.getInstance().checkID(Integer.parseInt(id))) {
			return false;
		}
		VoucherModel voucher = (VoucherModel) VoucherHandler.getInstance().find(Integer.parseInt(id));
		if(voucher.getStatus().matches("Used")) {
			System.out.println("validate");
			return false;
		}
		if(voucher.getValidDate().before(new java.util.Date())) {
			return false;
		}
		return true;
	}
	
	public Boolean checkPaymentMethod(String method) {
		if(method.matches("Credit")||method.matches("Cash")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean checkMoney(String money,int voucherID) {
		if(!isNumber(money)) {
			return false;
		}
		if(Integer.parseInt(money)<calculateTotalPrice(voucherID)) {
			return false;
		}
		return true;
	}
}
