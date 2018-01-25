package mvc.repository;

import java.util.List;

import mvc.model.Grade;

public interface IGradeRepo {
	
	public List<Grade> getGrades();
	
	public void insertGrade(Grade grade);
	public void updateGrade(Grade grade);
	public void deleteGrade(Integer gradeId);
	
	public void createTableGrade();
}
