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
	
	public VoucherModel insertData(Float discount, String date) {
		Date validDate = Date.valueOf(date);
		
		return (VoucherModel) new VoucherModel(discount, validDate, "valid").insertData();
	}
	
	public VoucherModel updateData(int id, Float discount, String date) {
		Date validDate = Date.valueOf(date);
		return (VoucherModel) new VoucherModel(id, discount, validDate).insertData();
	}
	
	public Boolean deleteData(int id) {
		VoucherModel data = (VoucherModel) find(id);
		return data.deleteData();
	}
	
	public void useVoucher(int id) {
		model.useVoucher(id);
	}

	@Override
	public List<Model> getAllData() {
		return model.getData("Voucher");
	}

	@Override
	public Model find(int id) {
		// TODO Auto-generated method stub
		return model.findData(id);
	}
	
	public Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public Boolean checkID(int id) {
		if(find(id)==null) {
			return false;
		}
		return true;
	}
	
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
	
	public Boolean checkValidDate(String str) {
		try {
			Date.valueOf(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
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
