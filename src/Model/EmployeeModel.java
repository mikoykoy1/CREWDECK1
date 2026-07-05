
package Model;

public class EmployeeModel {
    private int employeeId;
    private String employeeName;
    private String employeePosition;
    private String employeeDepartment;
    private int employeeContactNum;
    private String employeeEmail;
    private double salary;
    
    
    //Default Constructor
    public EmployeeModel(){
        
        this.employeeId = 0;
        this.employeeName = " ";
        this.employeePosition = " ";
        this.employeeDepartment = " ";
        this.employeeContactNum = 0;
        this.employeeEmail = " ";
        this.salary = 0;
    }
    
    //Perimeterized Constructor
    public EmployeeModel(int employeeId, String employeeName, String employeePosition,
                        String employeeDepartment, int employeeContactNum, String employeeEmail,
                        double salary){
        
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePosition = employeePosition;
        this.employeeDepartment = employeeDepartment;
        this.employeeContactNum = employeeContactNum;
        this.employeeEmail = employeeEmail;
        this.salary = salary;
    }

    
    
    //getter
    
    public int getEmployeeId(){
        return employeeId;
    }
    public String getEmployeeName(){
        return employeeName;
    }
    public String getEmployeePosition(){
        return employeePosition;
    }
    public String getEmployeeDepartment(){
        return employeeDepartment;
    }
    public int getEmployeeContactNum(){
        return employeeContactNum;
    }
    public String getEmployeeEmail(){
        return employeeEmail;
    }
    public double getSalary(){
        return salary;
    }
    
    //setter
    public void setEmployeeId(int employeeId){
       this.employeeId = employeeId;
    }
    public void setEmployeeId(String employeeName){
       this.employeeName = employeeName;
    }
    public void setEmployeePosition(String employeePosition){
       this.employeePosition = employeePosition;
    }
    public void setEmployeeDepartment(String employeeDepartment){
       this.employeeDepartment = employeeDepartment;
    }
    public void setEmployeeContactNum(int employeeContactNum){
       this.employeeContactNum = employeeContactNum;
    }
    public void setEmployeeEmail(String employeeEmail){
       this.employeeEmail = employeeEmail;
    }
    public void setSalary(double salary){
       this.salary = salary;
    }
    
}
