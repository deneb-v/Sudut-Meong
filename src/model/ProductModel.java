package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel extends Model{
	
	private String name;
	private String description;
	private int price;
	private int stock;

	public ProductModel(int id, String name, String description, int price, int stock) {
		super(id);
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	
	

	public ProductModel(int id,String name, String description, int price) {
		super(id);
		this.name = name;
		this.description = description;
		this.price = price;
	}

	

	public ProductModel(String name, String description, int price, int stock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}



	public ProductModel() {
	}
	
	//method untuk mengubah data yang diambil dari database yaitu Object ResultSet menjadi Object Product
	@Override
	public Model convData(ResultSet rawData) {
		try {
			return new ProductModel(rawData.getInt("id"),
					rawData.getString("name"),
					rawData.getString("description"),
					rawData.getInt("price"),
					rawData.getInt("stock"));
		} catch (SQLException e) {
			return null;
		}
	}

	//method untuk menambahkan data kedalam database
	@Override
	public Model insertData() {
		PreparedStatement query = db.prepareStatement("INSERT INTO Product(name, description, price , stock) VALUES(?,?,?,?)");
		try {
			query.setString(1, this.name);
			query.setString(2, this.description);
			query.setInt(3, this.price);
			query.setInt(4, this.stock);
			query.execute();
			PreparedStatement getInserted = db.prepareStatement("SELECT MAX(id) AS id FROM Product");
			ResultSet res = getInserted.executeQuery();
			res.next();
			this.setId(res.getInt("id"));
			
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//method untuk mengupdate data baru kedalam database
	@Override
	public Model updateData() {
		PreparedStatement query = db.prepareStatement("UPDATE Product SET name=?, description=?, price=?, stock=? WHERE id=? ");
		try {
			query.setString(1, this.name);
			query.setString(2, this.description);
			query.setInt(3, this.price);
			query.setInt(4, this.stock);
			query.setInt(5, getId());
			query.execute();
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//method untuk menghapus data product dari database
	@Override
	public Boolean deleteData() {
		PreparedStatement query = db.prepareStatement("DELETE FROM Product WHERE id=?");
		try {
			query.setInt(1, getId());
			query.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	//method untuk mencari data product yang ada pada database berdasarkan id-nya
	@Override
	public Model findData(int id) {
		PreparedStatement query = db.prepareStatement("SELECT * FROM Product WHERE id=?");
		try {
			query.setInt(1, id);
			ResultSet res = query.executeQuery();
			res.next();
			return convData(res);
		} catch (SQLException e) {
			return null;
		}
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getStock() {
		return stock;
	}



	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
