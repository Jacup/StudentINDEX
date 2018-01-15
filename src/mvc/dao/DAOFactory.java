package mvc.dao;

import mvc.dao.IGradeDAO;

public abstract class DAOFactory {

	public static final int MYSQL = 0;  
    public static final int SQLITE = 1;

    public abstract IGradeDAO getGradeDAO();
    /**
     * Factory-method
     * 
     * @param database
     *            the database to choose
     * @return a matching factory
     */
    public static DAOFactory getDAOFactory(int database) {
        switch (database) {
        case MYSQL:
            return new MysqlDAOFactory();
        case SQLITE:
             return new SqliteDAOFactory();
        default:
            return null;
        }
    }

}
