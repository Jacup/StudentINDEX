package mvc.controller;

import java.util.List;

import mvc.model.Grade;
import mvc.repository.GradeRepo;
import mvc.view.MainView;

public class GradeController {

	private GradeRepo model;
	private MainView view;
	
	public GradeController(GradeRepo model, MainView view) {
		this.model = model;
		this.view = view;
		
		view.setController(this);
		
		refreshGrades();
	}
	
	public void insertGrade(Grade grade) {
		model.insertGrade(grade);
		refreshGrades();
	}
	
	public void updateGrade(Grade grade) {
		model.updateGrade(grade);
		refreshGrades();
	}
	
	public void deleteGrade(Integer gradeId) {
		model.deleteGrade(gradeId);
		refreshGrades();
	}
	
	private void refreshGrades() {
		List<Grade> grades = model.getGrades();
		view.refreshGrades(grades);
	}
}

