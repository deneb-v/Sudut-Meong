package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class TransactionItemModel extends Model{
	
	private int transactionID;
	private int productID;
	private int quantity;
	

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public TransactionItemModel(int transactionID, int productID, int quantity) {
		super(0);
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public TransactionItemModel() {
	}

	//method untuk mengubah data yang diambil dari database, yaitu dari Object ResultSet menjadi Object TransactionItem
	@Override
	public Model convData(ResultSet rawData) {
		try {
			return new TransactionItemModel(rawData.getInt("transactionID"),
					rawData.getInt("productID"),
					rawData.getInt("quantity"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//method untuk menambahkan data kedalam database
	@Override
	public Model insertData() {
		PreparedStatement query = db.prepareStatement("INSERT INTO TransactionItem(transactionID,productID,quantity) VALUES(?,?,?) ");
		try {
			query.setInt(1, this.transactionID);
			query.setInt(2, this.productID);
			query.setInt(3, this.quantity);
			query.execute();
			PreparedStatement getInserted = db.prepareStatement("SELECT MAX(transactionID) AS id FROM `TransactionItem`");
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
		PreparedStatement query = db.prepareStatement("UPDATE TransactionItem SET transactionID=?, productID, quantity=? WHERE id=? ");
		try {
			query.setInt(1, this.transactionID);
			query.setInt(2, this.productID);
			query.setInt(3, this.quantity);
			query.execute();
			return this;
		} catch (SQLException e) {
			return null;
		}
	}

	//method untuk menghapus data detail transaction dari database
	@Override
	public Boolean deleteData() {
		PreparedStatement query = db.prepareStatement("DELETE FROM TransactionItem WHERE transactionID=?");
		try {
			query.setInt(1, this.transactionID);
			query.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public Model findData(int id) {
		//method ini digantikan dengan method getTransactionDetail
		return null;
	}
	
	//method untuk mengambil data detail transaksi dari database
	public List<TransactionItemModel> getTransactionDetail(int transactionID){
		PreparedStatement query = db.prepareStatement("SELECT * FROM TransactionItem WHERE transactionID=?");
		try {
			query.setInt(1, transactionID);
			ResultSet res = query.executeQuery();
			List<TransactionItemModel> data  = new Vector<>();
			while(res.next()) {
				data.add((TransactionItemModel) convData(res));
			} 
			return data;
		} catch (Exception e) {
			return null;
		}
	}

}
