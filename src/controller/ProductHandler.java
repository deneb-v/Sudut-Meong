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
	
	//method untuk memasukan product baru kedalam database
	public ProductModel insertData(String name, String description, int price, int stock) {
		return (ProductModel) new ProductModel(name, description, price, stock).insertData();
	}
	
	//method untuk melakukan update data product
	public ProductModel updateData(int id, String name, String description, int price, int stock) {
		return (ProductModel) new ProductModel(id, name, description, price, stock).updateData();
	}

	//method untuk melakukan penghapusan product dari database
	public Boolean deleteData(int id) {
		ProductModel data = (ProductModel) find(id);
		return data.deleteData();
	}
	
	//method untuk melakukan update mengurangi stock pada database sebanyak yang sudah digunakan
	public void useStock(int productID, int quantity) {
		ProductModel product = (ProductModel) find(productID);
		product.setStock(product.getStock()-quantity);
		product.updateData();
	}
	
	//method untuk melakukan update penambahan stock pada database
	public ProductModel reStock(int productID, int quantity) {
		ProductModel product = (ProductModel) find(productID);
		product.setStock(product.getStock()+quantity);
		return (ProductModel)product.updateData();
	}
	
	//method untuk mengambil seluruh data product dari database
	@Override
	public List<Model> getAllData() {
		return model.getData("Product");
	}

	//method untuk mencari data product yang ada pada database berdasarkan id-nya
	@Override
	public Model find(int id) {
		return model.findData(id);
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
	
	//Method untuk mengecek dengan menggunakan id apakah sebuah product terdapat pada database 
	public Boolean checkID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah nama product yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkName(String name) {
		if(name.isEmpty()) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah deskripsi product yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkDesc(String desc) {
		if(desc.isEmpty()) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah harga product yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkPrice(String price) {
		if(!isNumber(price)) {
			return false;
		}
		if(Integer.parseInt(price)<=0) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah stock product yang dimasukan sudah sesuai dengan ketentuan
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
