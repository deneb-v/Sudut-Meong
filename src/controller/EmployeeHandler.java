package controller;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import model.EmployeeModel;
import model.Model;
import view.MainView;

public class EmployeeHandler extends Controller{
	
	private static EmployeeHandler controller;
	private EmployeeModel model;
	private EmployeeModel onLog;

	public EmployeeModel getOnLog() {
		return onLog;
	}

	public void setOnLog(EmployeeModel onLog) {
		this.onLog = onLog;
	}

	private EmployeeHandler() {
		model = new EmployeeModel();
		onLog = null;
	}
	
	public static synchronized EmployeeHandler getInstance() {
		return (controller==null) ? controller = new EmployeeHandler() : controller;
	}
	
	public EmployeeModel insertData(int roleID, String name, String username, String dOB, int salary, String status) {
		String password = randomPassword();
		Date DOB = Date.valueOf(dOB);
		
		return (EmployeeModel) new EmployeeModel(roleID, name, username, DOB, salary, status, password).insertData();
	}
	
	public EmployeeModel updateData(int id, int roleID, String name, String username, String dOB, int salary) {
		Date DOB = Date.valueOf(dOB);
		
		return (EmployeeModel) new EmployeeModel(id, roleID, name, username, DOB, salary).updateData();
	}
	
	public EmployeeModel fireEmployee(int id) {
		EmployeeModel data = (EmployeeModel) find(id);
		System.out.println(data.getName());
		System.out.println(data.getId());
		System.out.println(data.getStatus());
		return (EmployeeModel) data.fireEmployee();
	}
	
	public EmployeeModel resetPassword(int id) {
		EmployeeModel data = (EmployeeModel) find(id);
		data.setPassword(randomPassword());
		return (EmployeeModel) data.resetPassword();
	}
	
	public Boolean checkUsernamePassword(String username, String password) {
		if(username.isEmpty()) {
			return false;
		}
		if(password.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public EmployeeModel login(String username, String password) {
		onLog = (EmployeeModel) model.login(username, password);
		if(onLog!=null) {
			if(onLog.getId()==1) {
				MainView.getInstance().openHrView();
			}
			else if(onLog.getId()==2) {
				MainView.getInstance().openManagerView();
			}
			else if(onLog.getId()==3) {
				MainView.getInstance().openStorageView();
			}
			else if(onLog.getId()==4) {
				MainView.getInstance().openPromoView();
			}
			else if(onLog.getId()==5) {
				MainView.getInstance().openCashierView();
			}
		}
		return onLog;
	}

	@Override
	public List<Model> getAllData() {
		return model.getData("Employee");
	}

	@Override
	public Model find(int id) {
		return model.findData(id);
	}
	
	private String randomPassword() {
		Random r = new Random();
		Integer res = r.nextInt(99999999-12345678+1)+12345678;
		
		return res.toString();
	}
	
	public Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public Boolean checkUsername(String str) {
		if(str.isEmpty()) {
			return false;
		}
		
		List<Model> data = EmployeeHandler.getInstance().getAllData();
		
		for (Model model : data) {
			if(((EmployeeModel) model).getUsername().matches(str)) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean checkUsername(String str, int id) {
		if(str.isEmpty()) {
			return false;
		}
		
		List<Model> data = EmployeeHandler.getInstance().getAllData();
		
		for (Model model : data) {
			if(((EmployeeModel) model).getId()!=id&&((EmployeeModel) model).getUsername().matches(str)) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean checkValidDate(String str) {
		try {
			Date.valueOf(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public Boolean checkName(String name) {
		if(name.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public Boolean checkDOB(String dob) {
		if(dob.isEmpty()) {
			return false;
		}
		if(!checkValidDate(dob)) {
			return false;
		}
		if(!Date.valueOf(dob).before(new java.util.Date())) {
			return false;
		}
		return true;
	}
	
	public Boolean checkRole(int id) {
		if(RoleHandler.getInstance().find(id)==null) {
			return false;
		}
		return true;
	}
	
	public Boolean checkSalary(String salary) {
		if(salary.isEmpty()) {
			return false;
		}
		if(!isNumber(salary)) {
			return false;
		}
		if(Integer.parseInt(salary)<=0) {
			return false;
		}
		return true;
	}
	
	public Boolean checkEmployeeID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}


	


}
