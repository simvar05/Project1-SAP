package Model;

import Repo.Role_User;

public class User {

    private Long id;
    private Role_User role;
    private String username;
    private String password;

    public User(Long id, Role_User role, String username, String password) {
        this.id= id;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id=id;
    }
    public void setRole(Role_User role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role_User getRoles() {return role; }


}
