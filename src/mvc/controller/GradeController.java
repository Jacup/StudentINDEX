package mvc.controller;

import java.util.List;

import mvc.dao.DAOFactory;
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
		
		chooseDatabase(0);
		
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
	
	public void chooseDatabase(Integer baza) {
        DAOFactory dao = DAOFactory.getDAOFactory(baza);
        model.refreshDB(dao);
        refreshGrades();
    }
	
    public void CreateTable() {
        model.createTableGrade();
        refreshGrades();
    }
}