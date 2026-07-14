package Model;

import java.time.LocalDate;

public class Employee {
    private int userId; // Shared Primary Key
    private User user;  // Composition: Links directly to the User object
    
    private String name;
    private String contactNum;
    private String address;
    private String department;
    private LocalDate dateHired;
    private double salary;
    private String status;

    // Default Constructor
    public Employee() {}

    // Minimal Constructor (Required fields for a new hire)
    public Employee(User user, String name, String department) {
        this.user = user; //
        if (user != null) {
            this.userId = user.getId(); // Syncs the ID automatically
        }
        this.name = name; //
        this.department = department; //
        this.dateHired = LocalDate.now(); // Defaults to today
        this.status = "Active";
    }

    // Full Constructor matching exactly the finalized database schema
    public Employee(User user, String name, String contactNum, String address, 
                    String department, LocalDate dateHired, double salary, String status) {
        this.user = user; //
        if (user != null) {
            this.userId = user.getId(); //
        }
        this.name = name; //
        this.contactNum = contactNum; //
        this.address = address; //
        this.department = department; //
        this.dateHired = dateHired; //
        this.salary = salary; //
        this.status = status; //
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.userId = user.getId(); //
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}