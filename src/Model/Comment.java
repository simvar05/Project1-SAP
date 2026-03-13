package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long version_id;
    private Long User_id;
    private String comment;
    private LocalTime time;

    public Comment(Long id, Long version_id, Long User_id, String comment, LocalTime time) {
        this.id = id;
        this.version_id = version_id;
        this.User_id = User_id;
        this.comment = comment;
        this.time = time;


    }

    public Long getId() {
        return id;
    }
}
