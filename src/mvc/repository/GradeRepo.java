package mvc.repository;

import java.util.List;

import mvc.dao.DAOFactory;
import mvc.dao.IGradeDAO;
import mvc.model.Grade;

public class GradeRepo implements IGradeRepo{

	DAOFactory sqliteFactory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
    IGradeDAO gradeDAO = sqliteFactory.getGradeDAO();

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
//	
//	private void executeCommand(String command) {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(command);
//            stmt.close();
//            conn.close();
//        }  catch (Exception ex) {
//        	ex.printStackTrace();
//        }
//	}
}

