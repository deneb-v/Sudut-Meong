package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

public class TransactionModel extends Model{

	private Date purchaseDateTime;
	private int voucherID;
	private int employeeID;
	private String paymentType;
	
	public Date getPurchaseDateTime() {
		return purchaseDateTime;
	}



	public void setPurchaseDateTime(Date purchaseDateTime) {
		this.purchaseDateTime = purchaseDateTime;
	}



	public int getVoucherID() {
		return voucherID;
	}



	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}



	public int getEmployeeID() {
		return employeeID;
	}



	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}



	public String getPaymentType() {
		return paymentType;
	}



	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



	public TransactionModel(int id, Date purchaseDateTime, int voucherID, int employeeID, String paymentType) {
		super(id);
		this.purchaseDateTime = purchaseDateTime;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
	}

	
	
	public TransactionModel(Date purchaseDateTime, int voucherID, int employeeID, String paymentType) {
		this.purchaseDateTime = purchaseDateTime;
		this.voucherID = voucherID;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
	}



	public TransactionModel() {
	}

	@Override
	public Model convData(ResultSet rawData) {
		try {
			return new TransactionModel(rawData.getInt("id"),
					rawData.getDate("purchase_datetime"),
					rawData.getInt("voucherID"),
					rawData.getInt("employeeID"),
					rawData.getString("paymentType"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Model insertData() {
		PreparedStatement query = db.prepareStatement("INSERT INTO `Transaction`(purchase_datetime,voucherID,employeeID,paymentType) VALUES(?,?,?,?) ");
		try {
			query.setDate(1, this.purchaseDateTime);
			if(this.voucherID==0) {				
				query.setNull(2, Types.INTEGER);
			}
			else {
				query.setInt(2, this.voucherID);
			}
			query.setInt(3, this.employeeID);
			query.setString(4, this.paymentType);
			query.execute();
			
			PreparedStatement getInserted = db.prepareStatement("SELECT MAX(id) AS id FROM `Transaction`");
			ResultSet res = getInserted.executeQuery();
			res.next();
			this.setId(res.getInt("id"));
			
			return this;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Model updateData() {
		PreparedStatement query = db.prepareStatement("UPDATE `Transaction` SET purchase_datetime=?, voucherID=?, employeeID=?, paymentType=? WHERE id=? ");
		try {
			query.setDate(1, this.purchaseDateTime);
			query.setInt(2, this.voucherID);
			query.setInt(3, this.employeeID);
			query.setString(4, this.paymentType);
			query.setInt(5, this.voucherID);
			query.execute();
			return this;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Boolean deleteData() {
		PreparedStatement query = db.prepareStatement("DELETE FROM `Transaction` WHERE id=?");
		try {
			query.setInt(1, getId());
			query.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public Model findData(int id) {
		PreparedStatement query = db.prepareStatement("SELECT * FROM `Transaction` WHERE id=?");
		try {
			query.setInt(1, id);
			return convData(query.executeQuery());
		} catch (SQLException e) {
			return null;
		}
	}

	
	public List<TransactionModel> getTransactionReport(int month, int year){
		PreparedStatement query = db.prepareStatement("SELECT * FROM `Transaction` WHERE YEAR(purchase_datetime) = ? AND MONTH(purchase_datetime) = ?");
		try {
			query.setInt(1, year);
			query.setInt(2, month);
			ResultSet res = query.executeQuery();
			List<TransactionModel> data  = new Vector<>();
			while(res.next()) {
				data.add((TransactionModel) convData(res));
			} 
			return data;
		} catch (Exception e) {
			return null;
		}
	}
}
