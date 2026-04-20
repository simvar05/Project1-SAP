package main.com.example.SpringPro.Model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="version_id")
    private DocumentVersion version;
    @ManyToOne
    @JoinColumn(name = "document_id") // Това ще създаде колона document_id в базата
    private Document document;
    @ManyToOne
    private User user;
    private String comment;
    private LocalTime time;

    public Comment(Long id, DocumentVersion version, User user, String comment, LocalTime time) {
        this.id = id;
       this.version = version;
       this.user = user;
        this.comment = comment;
        this.time = time;


    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DocumentVersion getVersion() {
        return version;
    }
    public void setVersion(DocumentVersion version) {
        this.version = version;
    }
   public void setUser(User user) {
        this.user = user;
   }
   public User getUser() {
        return user;
   }
   public Long getUserId(){
        return user.getId();
   }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
        public  LocalTime getTime() {
        return time;

        }
        public void setTime(LocalTime time) {
        this.time = time;
        }
}
