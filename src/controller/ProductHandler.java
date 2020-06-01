package controller;

import java.sql.SQLException;
import java.util.List;

import model.Model;
import model.ProductModel;

public class ProductHandler extends Controller{

	private static ProductHandler controller;
	private ProductModel model;
	
	public ProductHandler() {
		model = new ProductModel();
	}
	
	public static synchronized ProductHandler getInstance() {
		return (controller == null) ? controller = new ProductHandler() : controller;
	}
	
	public ProductModel insertData(String name, String description, int price, int stock) {
		return (ProductModel) new ProductModel(name, description, price, stock).insertData();
	}
	
	public ProductModel updateData(int id, String name, String description, int price, int stock) {
		return (ProductModel) new ProductModel(id, name, description, price, stock).updateData();
	}

	public Boolean deleteData(int id) {
		ProductModel data = (ProductModel) find(id);
		return data.deleteData();
	}
	
	public void useStock(int productID, int quantity) {
		ProductModel product = (ProductModel) find(productID);
		product.setStock(product.getStock()-quantity);
		product.updateData();
	}
	
	public ProductModel reStock(int productID, int quantity) {
		ProductModel product = (ProductModel) find(productID);
		product.setStock(product.getStock()+quantity);
		return (ProductModel)product.updateData();
	}
	
	
	@Override
	public List<Model> getAllData() {
		// TODO Auto-generated method stub
		return model.getData("Product");
	}

	@Override
	public Model find(int id) {
		// TODO Auto-generated method stub
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
	
	public Boolean checkID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}
	
	public Boolean checkName(String name) {
		if(name.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Boolean checkDesc(String desc) {
		if(desc.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Boolean checkPrice(String price) {
		if(!isNumber(price)) {
			return false;
		}
		if(Integer.parseInt(price)<=0) {
			return false;
		}
		return true;
	}
	
	public Boolean checkStock(String stock) {
		if(!isNumber(stock)) {
			return false;
		}
		if(Integer.parseInt(stock)<=0) {
			return false;
		}
		return true;
	}

}
