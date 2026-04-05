package main.com.example.SpringPro.Model;

import main.com.example.SpringPro.Repo.Role_User;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role_User role;
    @Column(name="username")
    private String username;
    @Column(name="password")
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
    public User() {}


}
