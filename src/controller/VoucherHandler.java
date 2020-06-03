package controller;

import java.sql.Date;
import java.util.List;

import model.EmployeeModel;
import model.Model;
import model.VoucherModel;

public class VoucherHandler extends Controller{

	private static VoucherHandler controller;
	private VoucherModel model;
	
	private VoucherHandler() {
		model = new VoucherModel();
	}
	
	public static synchronized VoucherHandler getInstance() {
		return (controller == null) ? controller = new VoucherHandler() : controller;
	}
	
	//method untuk memasukan voucher baru ke dalam database
	public VoucherModel insertData(Float discount, String date) {
		Date validDate = Date.valueOf(date);
		
		return (VoucherModel) new VoucherModel(discount, validDate, "valid").insertData();
	}
	
	//method untuk melakukan update data voucher 
	public VoucherModel updateData(int id, Float discount, String date) {
		Date validDate = Date.valueOf(date);
		return (VoucherModel) new VoucherModel(id, discount, validDate).insertData();
	}
	
	//method untuk melakukan penghapusan voucher berdasarkan id-nya
	public Boolean deleteData(int id) {
		VoucherModel data = (VoucherModel) find(id);
		return data.deleteData();
	}
	
	//method untuk mengupdate status voucher menjadi sudah digunakan
	public void useVoucher(int id) {
		model.useVoucher(id);
	}

	//method untuk mengambil seluruh data voucher dari database
	@Override
	public List<Model> getAllData() {
		return model.getData("Voucher");
	}

	//method untuk mencari data voucher yang ada pada database berdasarkan id-nya
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
	
	//Method untuk mengecek dengan menggunakan id apakah sebuah voucher terdapat pada database 
	public Boolean checkID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}
	
	//method untuk mengecek apakah diskon/discount yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkDiscount(String str) {
		if(str.isEmpty()) {
			return false;
		}
		if(!isNumber(str)) {
			return false;
		}
		if(Integer.parseInt(str)<1&&Integer.parseInt(str)>100) {
			return false;
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
	
	//method untu mengecek apakah date/tanggal yang dimasukan sudah sesuai dengan ketentuan
	public Boolean checkDate(String date) {
		if(date.isEmpty()) {
			return false;
		}
		if(!checkValidDate(date)) {
			return false;
		}
		if(Date.valueOf(date).before(new java.util.Date())) {
			return false;
		}
		return true;
	}

}
