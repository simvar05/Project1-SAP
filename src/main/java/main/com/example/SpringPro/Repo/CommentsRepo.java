package main.com.example.SpringPro.Repo;

import main.com.example.SpringPro.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends JpaRepository<Comment, Long> {
    Comment findTopByOrderByIdDesc();
}
