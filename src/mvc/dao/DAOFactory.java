package mvc.dao;

import mvc.dao.IGradeDAO;

public abstract class DAOFactory {

	public static final int MYSQL = 1;  
    public static final int SQLITE = 0;

    public abstract IGradeDAO getGradeDAO();
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
