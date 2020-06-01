package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoucherModel extends Model{
	
	private Float discount;
	private Date validDate;
	private String status;
	

	public VoucherModel(int id, Float discount, Date validDate, String status) {
		super(id);
		this.discount = discount;
		this.validDate = validDate;
		this.status = status;
	}
	
	public VoucherModel(Float discount, Date validDate, String status) {
		this.discount = discount;
		this.validDate = validDate;
		this.status = status;
	}
	
	

	public VoucherModel(int id, Float discount, Date validDate) {
		super(id);
		this.discount = discount;
		this.validDate = validDate;
	}

	public VoucherModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Model convData(ResultSet rawData) {
		try {
			return new VoucherModel(rawData.getInt("id"), rawData.getFloat("discount"), rawData.getDate("validDate"), rawData.getString("status"));
		} catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Model insertData() {
		PreparedStatement query = db.prepareStatement("INSERT INTO Voucher(discount,validDate,status) VALUES(?,?,?)");
		try {
			query.setFloat(1, this.discount);
			query.setDate(2, this.validDate);
			query.setString(3, "Unused");
			query.execute();
			PreparedStatement getInserted = db.prepareStatement("SELECT MAX(id) AS id FROM Voucher");
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
		PreparedStatement query = db.prepareStatement("UPDATE Voucher SET discount=?,validDate=? WHERE id=? ");
		try {
			query.setFloat(1, this.discount);
			query.setDate(2, this.validDate);
			query.setInt(3, getId());
			query.execute();
			return this;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public void useVoucher(int id) {
		PreparedStatement query = db.prepareStatement("UPDATE Voucher SET status=? WHERE id=?");
		try {
			query.setString(1, "Used");
			query.setInt(2, id);
			query.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean deleteData() {
		PreparedStatement query = db.prepareStatement("DELETE FROM Voucher WHERE id=?");
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
		PreparedStatement query = db.prepareStatement("SELECT * FROM Voucher WHERE id=?");
		try {
			query.setInt(1, id);
			ResultSet res = query.executeQuery();
//			System.out.println(res);
			res.next();
			return convData(res);
		} catch (SQLException e) {
			return null;
		}
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
