package mvc;

import org.apache.log4j.PropertyConfigurator;

import mvc.controller.GradeController;
import mvc.repository.GradeRepo;
import mvc.view.MainView;

public class CreateTable {

	public static void main(String[] args) {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		GradeRepo model = new GradeRepo();
//		model.createTableGrade();
		MainView view = new MainView();
		new GradeController(model, view);
		
		view.setVisible(true);
	}
}
