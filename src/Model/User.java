/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.management.relation.Role;

/**
 *
 * @author BE214
 */
public class User {
    private int id;
    private String email;
    private boolean isActive;
    private String passwordHash;
    private List<String> roles;
    //temp password after hire and before employee login
    private String temporaryPassword;
    
    //constructors
    public User(){
        this.roles = new ArrayList<>();
        this.isActive = true;
    }
    public User(int id, String email, String passwordHash, boolean isActive ){
        this.id =  id;
        this.email =  email;
        this.isActive =  isActive;
        this.passwordHash = passwordHash;
        this.roles = new ArrayList<>();
    }
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
     
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public boolean getIsActive(){
        return isActive;
    }
    
     public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
     
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }
    
}
