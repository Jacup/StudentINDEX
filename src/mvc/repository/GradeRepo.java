package mvc.repository;

import java.util.List;

import mvc.dao.DAOFactory;
import mvc.dao.IGradeDAO;
import mvc.model.Grade;

public class GradeRepo implements IGradeRepo{

	DAOFactory sqlFactory;
    IGradeDAO gradeDAO;

    public void refreshDB(DAOFactory factory) {
        this.sqlFactory = factory;
        this.gradeDAO = sqlFactory.getGradeDAO();
    }
    
	@Override
	public List<Grade> getGrades() {
        return gradeDAO.readAll();
	}
	
	@Override
	public void insertGrade(Grade grade) {
        gradeDAO.create(grade);
	}

	@Override
	public void createTableGrade() {
		gradeDAO.createTable();
	}
	
	
	public void updateGrade(Grade grade) {
		gradeDAO.update(grade);	
	}
	
	public void deleteGrade(Integer gradeId) {
		gradeDAO.delete(gradeId);
	}
}

