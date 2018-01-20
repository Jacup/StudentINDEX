package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mvc.model.Grade;

public class MysqlGradeDAO implements IGradeDAO {

	private static final Logger logger = Logger.getLogger(MysqlGradeDAO.class);
    
	private static final String CREATE_QUERY = 
			"INSERT INTO grade (grade, weight, descripton) VALUES (?,?,?)";
    private static final String READ_QUERY = 
    		"SELECT id, grade, weight, descripton FROM grade WHERE id = ?";
    private static final String READALL_QUERY = 
    		"SELECT * FROM grade";
    private static final String UPDATE_QUERY = 
    		"UPDATE grade SET grade=?, weight=?, descripton=? WHERE id = ?";
    private static final String DELETE_QUERY = 
    		"DELETE FROM grade WHERE id = ?";
	private static final String CREATE_TABLE = 
			"CREATE TABLE `index`.`grade` (\n" + 
			"  `id` INT NOT NULL AUTO_INCREMENT,\n" + 
			"  `grade` VARCHAR(50) NOT NULL,\n" + 
			"  `weight` VARCHAR(50) NULL,\n" + 
			"  `descripton` VARCHAR(45) NULL,\n" + 
			"  PRIMARY KEY (`id`));\n" + 
			"";
 
	@Override
	public int create(Grade grade) {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = MysqlDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, grade.getGrade());
            preparedStatement.setString(2, grade.getWeight());
            preparedStatement.setString(3, grade.getDesc());
            preparedStatement.execute();
            result = preparedStatement.getGeneratedKeys();
 
            if (result.next() && result != null) {
                return result.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                logger.error(rse.getMessage());
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                logger.error(sse.getMessage());
            }
            try {
                conn.close();
            } catch (Exception cse) {
                logger.error(cse.getMessage());
            }
        }
        return -1;
	}

	@Override
	public Grade read(int id) {
		Grade grade = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
        	conn = MysqlDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(READ_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            if (result.next() && result != null) {
            	grade = new Grade(result.getInt(1), 
            						result.getString(2), 
            						result.getString(3), 
            						result.getString(4)); 
            } else {
                // TODO
            	logger.info("Error. There is no grade with ID = "+ id);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                logger.error(rse.getMessage());
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                logger.error(sse.getMessage());
            }
            try {
                conn.close();
            } catch (Exception cse) {
                logger.error(cse.getMessage());
            }
        }
        return grade;
	}

	@Override
	public List<Grade> readAll() {
		List<Grade> listof_grades = new ArrayList<Grade>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
        	conn = MysqlDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(READALL_QUERY);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
            
            if (result != null){
            while(result.next())
            	listof_grades.add(
            					new Grade(
            							result.getInt(1), 
            							result.getString(2), 
            							result.getString(3), 
            							result.getString(4) 
            							)
            					);
            } else {
                // TODO
            	logger.info("There's no grades!");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                logger.error(rse.getMessage());
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                logger.error(sse.getMessage());
            }
            try {
                conn.close();
            } catch (Exception cse) {
                logger.error(cse.getMessage());
            }
        }
        return listof_grades;
	}

	@Override
	public boolean update(Grade grade) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
        	conn = MysqlDAOFactory.createConnection();
            preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, grade.getGrade());
            preparedStatement.setString(2, grade.getWeight());
            preparedStatement.setString(3, grade.getDesc());
            preparedStatement.setInt(4, grade.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                logger.error(sse.getMessage());
            }
            try {
                conn.close();
            } catch (Exception cse) {
                logger.error(cse.getMessage());
            }
        }
        return false;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = null;
	    PreparedStatement preparedStatement = null;
	    try {
	    	conn = MysqlDAOFactory.createConnection();
	    	preparedStatement = conn.prepareStatement(DELETE_QUERY);
	    	preparedStatement.setInt(1, id);
	    	preparedStatement.execute();
	    	return true;
	    } catch (SQLException e) {
	    	logger.error(e.getMessage());
	    } finally {
	    	try {
	    		preparedStatement.close();
	    	} catch (Exception sse) {
	    		logger.error(sse.getMessage());
	    	}
	    	try {
	    		conn.close();
	        } catch (Exception cse) {
	        	logger.error(cse.getMessage());
	        }
	    }
	    return false;
	}
	
	@Override
	public void createTable(){
		Connection conn = null;
		Statement stmt = null;
		try {
	    	conn = MysqlDAOFactory.createConnection();
	    	stmt = conn.createStatement();
	    	stmt.executeUpdate(CREATE_TABLE);
		} catch (SQLException e) {
	    	logger.error(e.getMessage());
		} finally {
	    	try {
	    		stmt.close();
	    	} catch (Exception sse) {
	    		logger.error(sse.getMessage());
	    	}
	    	try {
	    		conn.close();
	        } catch (Exception cse) {
	        	logger.error(cse.getMessage());
	        }
		}
	}
}
