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
	
	//method untuk mengembalikan employee yang sedang login kedalam aplikasi
	public EmployeeModel getOnLog() {
		return onLog;
	}

	//method untuk mengassign employee mana yang sedang login kedalam aplikasi
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
	
	//method untuk menambahkan employee baru kedalam database
	public EmployeeModel insertData(int roleID, String name, String username, String dOB, int salary, String status) {
		String password = randomPassword();
		Date DOB = Date.valueOf(dOB);
		
		return (EmployeeModel) new EmployeeModel(roleID, name, username, DOB, salary, status, password).insertData();
	}
	
	//method untuk melakukan update data employee
	public EmployeeModel updateData(int id, int roleID, String name, String username, String dOB, int salary) {
		Date DOB = Date.valueOf(dOB);
		
		return (EmployeeModel) new EmployeeModel(id, roleID, name, username, DOB, salary).updateData();
	}
	
	//method untuk memecat employee
	public EmployeeModel fireEmployee(int id) {
		EmployeeModel data = (EmployeeModel) find(id);
		System.out.println(data.getName());
		System.out.println(data.getId());
		System.out.println(data.getStatus());
		return (EmployeeModel) data.fireEmployee();
	}
	
	//method untuk mereset password employee
	public EmployeeModel resetPassword(int id) {
		EmployeeModel data = (EmployeeModel) find(id);
		data.setPassword(randomPassword());
		return (EmployeeModel) data.resetPassword();
	}
	
	//method untuk melakukan cek pada username dan password yang dimasukan pada saat login
	public Boolean checkUsernamePassword(String username, String password) {
		username = username.trim();
		password = password.trim();
		if(username.isEmpty()) {
			return false;
		}
		if(password.isEmpty()) {
			return false;
		}
		return true;
	}
	
	//method untuk melakukan login lalu membuka tampilan sesuai dengan role dari employee tersebut
	public EmployeeModel login(String username, String password) {
		onLog = (EmployeeModel) model.login(username, password);
		if(onLog!=null) {
			if(onLog.getRoleID()==1) {
				MainView.getInstance().openHrView();
			}
			else if(onLog.getRoleID()==2) {
				MainView.getInstance().openManagerView();
			}
			else if(onLog.getRoleID()==3) {
				MainView.getInstance().openStorageView();
			}
			else if(onLog.getRoleID()==4) {
				MainView.getInstance().openPromoView();
			}
			else if(onLog.getRoleID()==5) {
				MainView.getInstance().openCashierView();
			}
			MainView.getInstance().setAccountName("Hello!, "+onLog.getName());
		}
		return onLog;
	}
	
	//method untuk melakukan logout
	public void logout() {
		onLog = null;
		MainView.getInstance().reset();
		MainView.getInstance().openLoginFrame();
		MainView.getInstance().setAccountName("Not Logged In");
	}

	//method untuk mengambil seluruh data employee dari database
	@Override
	public List<Model> getAllData() {
		return model.getData("Employee");
	}

	//method untuk mencari data employee yang ada pada database berdasarkan id-nya
	@Override
	public Model find(int id) {
		return model.findData(id);
	}
	
	//method untuk melakukan generate password baru untuk employee
	private String randomPassword() {
		Random r = new Random();
		Integer res = r.nextInt(99999999-12345678+1)+12345678;
		
		return res.toString();
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
	
	//method untuk mengecek apakah sebuah username yang baru dibuat adalah username yang unik atau belum pernah dibuat
	public Boolean checkUsername(String str) {
		str = str.trim();
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
	
	//method untuk mengecek apakah sebuah username yang baru diupdate adalah username yang unik atau belum pernah dibuat
	public Boolean checkUsername(String str, int id) {
		str = str.trim();
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
	
	//Method untuk mengecek apakah sebuah string adalah Date dengan format yang benar
	public Boolean checkValidDate(String str) {
		try {
			Date.valueOf(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	//Method untuk mengecek apakah nama yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkName(String name) {
		name = name.trim();
		if(name.isEmpty()) {
			return false;
		}
		return true;
	}
	
	//Method untuk mengecek apakah DateOfBirth yang dimasukan sudah sesuai ketentuan
	public Boolean checkDOB(String dob) {
		dob = dob.trim();
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
	
	//method untuk mengecek apakah roleID yang dipilih terdapat pada database
	public Boolean checkRole(int id) {
		if(RoleHandler.getInstance().find(id)==null) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah gaji/salary yang dimasukan sudah sesuai ketentuan
	public Boolean checkSalary(String salary) {
		salary = salary.trim();
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
	
	//Method untuk mengecek dengan menggunakan id apakah sebuah employee terdapat pada database 
	public Boolean checkEmployeeID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}


	


}
