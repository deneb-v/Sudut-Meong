package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel extends Model {
	
	private int roleID;
	private String name;
	private String username;
	private Date DOB;
	private int salary;
	private String status;
	private String password;

	public EmployeeModel(int id, int roleID, String name, String username, Date DOB, int salary, String status, String password) {
		super(id);
		this.roleID = roleID;
		this.name = name;
		this.username = username;
		this.DOB = DOB;
		this.salary = salary;
		this.status = status;
		this.password = password;
	}
	
	public EmployeeModel(int roleID, String name, String username, Date dOB, int salary, String status, String password) {
		this.roleID = roleID;
		this.name = name;
		this.username = username;
		DOB = dOB;
		this.salary = salary;
		this.status = status;
		this.password = password;
	}

	public EmployeeModel(int id, int roleID, String name, String username, Date dOB, int salary) {
		super(id);
		this.roleID = roleID;
		this.name = name;
		this.username = username;
		DOB = dOB;
		this.salary = salary;
	}



	public EmployeeModel() {
	}

	//method untuk mengubah data yang diambil dari database yaitu Object ResultSet menjadi Object Employee
	@Override
	public Model convData(ResultSet rawData) {
		try {
			return new EmployeeModel(rawData.getInt("id"),
									rawData.getInt("roleID"),
									rawData.getString("name"), 
									rawData.getString("username"),
									rawData.getDate("DOB"),
									rawData.getInt("salary"),
									rawData.getString("status"),
									rawData.getString("password"));
		} catch (SQLException e) {
			return null;
		}
	}

	//method untuk menambahkan data kedalam database
	@Override
	public Model insertData() {
		PreparedStatement query = db.prepareStatement("INSERT INTO Employee(roleID,name,username,password,DOB,salary,status) VALUES(?,?,?,?,?,?,?)");
		try {
			query.setInt(1, this.roleID);
			query.setString(2, this.name);
			query.setString(3, this.username);
			query.setString(4, this.password);
			query.setDate(5, this.DOB);
			query.setInt(6, this.salary);
			query.setString(7, this.status);
			query.execute();
			PreparedStatement getInserted = db.prepareStatement("SELECT MAX(id) AS id FROM Employee");
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
		PreparedStatement query = db.prepareStatement("UPDATE Employee SET name=?, username=?, DOB=?, salary=?  WHERE id=? ");
		try {
			query.setString(1, this.name);
			query.setString(2, this.username);			
			query.setDate(3, this.DOB);			
			query.setInt(4, this.salary);			
			query.setInt(5, getId());			
			query.execute();
			return this;
		} catch (SQLException e) {
			return null;
		}
	}
	
	//method untuk mengupdate data password baru kedalam database
	public Model resetPassword() {
		PreparedStatement query = db.prepareStatement("UPDATE Employee SET password=? WHERE id=?");
		try {
			query.setString(1, this.password);
			query.setInt(2, getId());
			query.execute();
			return this;
		} catch (Exception e) {
			return null;
		}
	}
	
	//method untuk mengupdate status Employee menjadi "Fired" ke dalam database
	public Model fireEmployee() {
		PreparedStatement query = db.prepareStatement("UPDATE Employee SET status=? WHERE id=?");
		try {
			query.setString(1, "Fired");
			query.setInt(2, getId());
			query.execute();
			return this;
		} catch (Exception e) {
			return null;
		}
	}

	//method untuk menghapus data employee dari database
	@Override
	public Boolean deleteData() {
		PreparedStatement query = db.prepareStatement("DELETE FROM Employee WHERE id=?");
		try {
			query.setInt(1, getId());
			query.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	//method untuk mencari data employee yang ada pada database berdasarkan id-nya 
	@Override
	public Model findData(int id) {
		PreparedStatement query = db.prepareStatement("SELECT * FROM Employee WHERE id=?");
		try {
			query.setInt(1, id);
			ResultSet data = query.executeQuery();
			data.next();
			return convData(data);
		} catch (SQLException e) {
			return null;
		}
	}
	
	//method untuk mengambil data employee dari database yang melakukan login dengan menggunakan username dan password
	public Model login(String username, String password) {
		PreparedStatement query = db.prepareStatement("SELECT*FROM Employee WHERE username=? AND password=? AND status='Employed'");
		try {
			query.setString(1, username);
			query.setString(2, password);
			ResultSet res = query.executeQuery();
			res.next();
			return convData(res);
		} catch (SQLException e) {
			return null;
		}
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
