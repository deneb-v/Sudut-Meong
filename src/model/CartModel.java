package model;

import java.util.List;
import java.util.Vector;

public class CartModel {
	
	private ProductModel product;
	private int quantity;
	
	public CartModel(ProductModel product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public CartModel() {
		
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
