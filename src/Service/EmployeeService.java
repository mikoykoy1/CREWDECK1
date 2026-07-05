
package Service;

import Model.EmployeeModel;
import DAO.EmployeeDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class EmployeeService {
    
    private EmployeeDAO employeeDAO = new EmployeeDAO();
        
        public boolean registerEmployee(EmployeeModel emp){
        if (emp.getEmployeeName().equals("") || emp.getSalary() <= 0) {
            return false;
        }
        
        employeeDAO.add(emp);
        return true;        
    }
    
    public List<EmployeeModel> fetchAllRecords(){
        return employeeDAO.getAll();
    }
    
    public EmployeeModel fetchOneRecord(int id){
        return employeeDAO.getItem(id);
    }
    
    public boolean modifyEmployee(EmployeeModel emp){
        boolean isNotFound = true;
        
        for (EmployeeModel e : fetchAllRecords()) {
            if(e.getEmployeeId() == emp.getEmployeeId()){
                isNotFound = false;
                break;
            }
        }
        if (emp.getEmployeeId() <= 0 || emp.getEmployeeName().equals("") || emp.getSalary() <= 0 || isNotFound) {
            return false;
        }
        
        employeeDAO.update(emp);
        return true;
    }
    
    public void removeEmployee(int id){
        employeeDAO.delete(id);
    }
    
    //CONVERT LIST TABLE (GET TABLE DATA)
    public DefaultTableModel getTableModel(){
        String columns[] = {"ID", "Name", "Position", "Department", "Contact Number", "Email" , "Salary"};
        Object[][] data = new Object[fetchAllRecords().size()][8];
        
        for (int i = 0; i < fetchAllRecords().size(); i++) {
            EmployeeModel emp = fetchAllRecords().get(i);
            data[i][0] = emp.getEmployeeId();
            data[i][1] = emp.getEmployeeName();
            data[i][2] = emp.getEmployeePosition();
            data[i][3] = emp.getEmployeeDepartment();
            data[i][4] = emp.getEmployeeContactNum();
            data[i][5] = emp.getEmployeeEmail();
            data[i][6] = emp.getSalary();
        }
        
        return new DefaultTableModel(data, columns);
    }
    
}
