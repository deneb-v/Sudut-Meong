package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import controller.EmployeeHandler;
import view.MainView;



public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		MainView.getInstance().setLocationRelativeTo(null);
		MainView.getInstance().setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
