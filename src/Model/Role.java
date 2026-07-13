
package Model;


public class Role {
    private int id;
    private String name;
    private String description;
    
    public Role(String name){
        //this.id = id;
        this.name = name;
        //this.description = description;
    }
    
    public String getRoleName(){
        return name;
    }
}
