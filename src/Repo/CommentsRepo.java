package Repo;

import Model.Comment;
import Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comment, Long> {
    Comment findTopByOrderByIdDes();
}
