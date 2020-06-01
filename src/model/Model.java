package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public abstract class Model {
	
	private int id;
	protected Connect db = Connect.getConnection();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Model(int id) {
		this.id = id;
	}
	
	public Model() {
		
	}
	
	public abstract Model convData(ResultSet rawData);
	
	public List<Model> getData(String table){
		PreparedStatement query = db.prepareStatement("SELECT * FROM "+table);
		
		try {
//			System.out.println(query);
			ResultSet rawData = query.executeQuery();
			List<Model> data  = new Vector<>();
			while(rawData.next()) {
				data.add(convData(rawData));
			}
			
			return data;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public abstract Model insertData();
	public abstract Model updateData();
	public abstract Boolean deleteData();
	public abstract Model findData(int id);

}
