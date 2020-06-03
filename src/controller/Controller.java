package controller;

import java.util.List;

import model.Model;
import view.MainView;
import view.View;

public abstract class Controller {

	public Controller() {
	}
	
	//method untuk mengembalikan mainView
	public View getView() {
		return MainView.getInstance();
	};
	public abstract List<Model> getAllData();
	public abstract Model find(int id);
}
