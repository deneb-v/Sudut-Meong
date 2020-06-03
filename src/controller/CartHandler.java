package controller;

import java.util.List;
import java.util.Vector;

import model.CartModel;
import model.Model;
import model.ProductModel;
import view.CheckoutInternalView;

public class CartHandler{

	
	private static CartHandler controller;
	private List<CartModel> cartList;
	
	private CartHandler() {
		cartList = new Vector<CartModel>();
	}
	
	public static synchronized CartHandler getInstance() {
		return (controller==null) ? controller = new CartHandler() : controller;
	}
	
	public Boolean isDuplicateItem(int id) {
		for (CartModel cartModel : cartList) {
			if(cartModel.getProduct().getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	//Method untuk menghapus seluruh isi list cart
	public void emptyCart() {
		cartList.clear();
	}
	
	//Method untuk memasukan item ke dalam list cart
	public CartModel insertData(int id, int quantity) {
		ProductModel model = new ProductModel();
		CartModel item = new CartModel((ProductModel) model.findData(id), quantity);
		if(!isDuplicateItem(id)) {			
			try {			
				cartList.add(item);
				return item;
			} catch (Exception e) {
				return null;
			}
		}
		else {
			return updateStock(id, quantity);
		}
	}
	
	//Method untuk melakukan update stock pada barang yang sudah ada di dalam list cart
	public CartModel updateStock(int id, int quantity) {
		for (CartModel x : cartList) {
			if(x.getProduct().getId() == id) {
				x.setQuantity(quantity+x.getQuantity());
				return x;
			}
		}
		return null;
	}

	//Method untuk memanggil list cart
	public List<CartModel> getCartList() {
		return cartList;
	}
	
	
	//Method untuk menghitung total seluruh harga barang yang ada di dalam list cart
	public int getTotalPrice() {	
		int totalPrice = 0; 
		
		for (CartModel cartModel : cartList) {
			totalPrice += (cartModel.getQuantity() * (cartModel.getProduct().getPrice()));
		}
		return totalPrice;
	}

	//Method untuk mengecek apakah sebuah string adalah angka
	public Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	//Method untuk mengecek dengan menggunakan id apakah sebuah barang terdapat pada database 
	public Boolean checkID(String id) {
		if(!isNumber(id)) {
			return false;
		}
		
		if(ProductHandler.getInstance().find(Integer.parseInt(id))==null) {
			return false;
		}
		return true;
	}
	
	//Method untuk mengecek apakah stock barang cukup
	public Boolean checkQuantity(String id,String quantity) {
		ProductModel item = (ProductModel) ProductHandler.getInstance().find(Integer.parseInt(id));
		
		if(!isNumber(quantity)) {
			return false;
		}
		if(Integer.parseInt(quantity) < 0) {
			return false;
		}
		if(Integer.parseInt(quantity) > item.getStock()) {
			return false;
		}
		return true;
	}
}
