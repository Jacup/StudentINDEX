package mvc.dao;

import java.util.List;
import mvc.model.Grade;

public interface IGradeDAO {
    
    public int create(Grade grade);
    
    public void createTable();
 
    public Grade read(int id);

    public List<Grade> readAll();
 
    public boolean update(Grade grade);
 
    public boolean delete(int id);
}
