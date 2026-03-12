package Repo;

import Model.User;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo {
    List<User> findAllByIdAndRole(Long user_id, Role_User role);
    User findTopByOrderByIdDes(Long user_id);
    User findById(Long user_id);
}
