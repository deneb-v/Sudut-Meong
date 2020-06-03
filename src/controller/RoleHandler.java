package controller;

import java.util.List;

import model.Model;
import model.RoleModel;

public class RoleHandler extends Controller{
	
	private static RoleHandler controller;
	private RoleModel model;

	private RoleHandler() {
		model = new RoleModel();
	}
	
	public static synchronized RoleHandler getInstance() {
		return (controller==null) ? controller = new RoleHandler() : controller;
	}
	
	//method yang digunakan untuk mengambil seluruh data Role dari database
	@Override
	public List<Model> getAllData() {
		return model.getData("Role");
	}
	
	//method untuk mencari data role yang ada pada database berdasarkan id-nya
	@Override
	public Model find(int id) {
		return model.findData(id);
	}

}
