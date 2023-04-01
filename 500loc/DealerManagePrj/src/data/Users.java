package data;

import data.UsersList.Role;
import data.UsersList.Status;

/**
 *
 * @author Asus
 */
public class Users {

    String usersName;
    String password;
//    Role role;
//    Status status;
    private String role;
    private String status;
    private Users usersN = null; 
    
    public Users() {
    }

    public Users(Users users) {
        this.usersN = users;
    }

    public Users(String usersName, String password, String role) {
        this.usersName = usersName;
        this.password = password;
        this.role = role;
    }

    public Users(String usersName, String password, String role, String status) {
        this.usersName = usersName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

//    public Users(String inname, String inpassword, AllEnum.inRole role1, AllEnum.inStatus status1) {
//        this.usersName = usersName;
//        this.password = password;
//        
//                
//    }
    public String getName() {
        return usersName;
    }

    public String getPassword() {
        return password;
    }

//    public Role getRole() {
//        return role;
//    }
//    
//    public Status getStatus() {
//        return status;
//    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
