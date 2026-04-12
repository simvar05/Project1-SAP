package main.com.example.SpringPro.Repo;

import main.com.example.SpringPro.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    List<User> findAllByIdAndRole(Long user_id, Role_User role);
    User findTopByOrderByIdDesc();
    User findById(long id);
    User findByUsername(String username);
}
