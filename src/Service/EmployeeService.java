package Service;

import Model.Employee;
import DAO.EmployeeDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeeService {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    
    // Register the profile details (called after UserService sets up the login account)
    public boolean registerEmployee(Employee emp) {
        try {
            if (emp.getName().equals("") || emp.getSalary() < 1) {
                return false;
            }
        
            employeeDAO.add(emp);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error registering profile: " + e.getMessage());
            return false;
        }
    }
    
    public List<Employee> fetchAllRecords() throws SQLException {
        try {
            return employeeDAO.getAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public Employee fetchOneRecord(int id){
       try {
            return employeeDAO.getItem(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }
    
    public boolean modifyEmployee(Employee emp) {
        try {
            boolean isNotFound = true;
            for (Employee e : fetchAllRecords()) {
                if (e.getUserId() == emp.getUserId()) {
                    isNotFound = false;
                    break;
                }
            }
            if (isNotFound) return false;

            if (emp.getUserId() <= 0 || emp.getName().equals("") || emp.getSalary() < 1) {
                return false;
            }

            employeeDAO.update(emp);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public void removeEmployee(int id) {
        try {
            employeeDAO.delete(id); // Deletes from users, cascades to employee records automatically
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Refactored to display your new database columns in your JTable
    public DefaultTableModel getTableModel() {
        try {
            String columns[] = {"User ID", "Name", "Department", "Salary", "Status"};
            List<Employee> records = fetchAllRecords();
            Object[][] data = new Object[records.size()][5];

            for (int i = 0; i < records.size(); i++) {
                Employee emp = records.get(i);
                data[i][0] = emp.getUserId();
                data[i][1] = emp.getName();
                data[i][2] = emp.getDepartment();
                data[i][3] = emp.getSalary();
                data[i][4] = emp.getStatus();
            }
            return new DefaultTableModel(data, columns);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public Employee fetchRecord(int id) {
        try {
            return employeeDAO.getItem(id);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    
    
    
}