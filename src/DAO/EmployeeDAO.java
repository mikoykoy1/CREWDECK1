
package DAO;

import java.util.ArrayList;
import java.util.List;
import Model.EmployeeModel;


public class EmployeeDAO {
    //ArrayList
    private static List<EmployeeModel> database = new ArrayList();
    
    //CREATE
    public void add(EmployeeModel emp){
        
        int size = database.size();
        int currentId = size <= 0 ? 1 : database.get(size - 1).getEmployeeId() + 1;
        
        EmployeeModel employee = new EmployeeModel(currentId, emp.getEmployeeName(), emp.getEmployeePosition(),
                        emp.getEmployeeDepartment(), emp.getEmployeeContactNum(), emp.getEmployeeEmail(),
                        emp.getSalary());
        
        database.add(employee);
    }
    
    //READ (all)
    public List<EmployeeModel> getAll(){
        return database;
    }
    
    //READ (one)
    public EmployeeModel getItem(int id){
        for (EmployeeModel emp : database) {
            
            if (emp.getEmployeeId() == id) {
                return emp;
            }
                            
        }
        return null;
    }
    
    //UPDATE
    public void update(EmployeeModel updatedemp){
         for (EmployeeModel emp : database) {
            
            if (emp.getEmployeeId() == updatedemp.getEmployeeId()) {
                int index = database.indexOf(emp);
                database.set(index, updatedemp);
            }
                            
        }
    }
    
    //DELETE
    public void delete(int id){
        for (EmployeeModel emp : database) {
            
            if (emp.getEmployeeId() == id) {
                int index = database.indexOf(emp);
                database.remove(index);
                break;
            }                  
        }         
    }
    
    
}
