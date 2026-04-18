package main.com.example.SpringPro.Model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import main.com.example.SpringPro.Repo.Status_documentVer;

import javax.print.DocFlavor;
import java.util.List;


@Entity
public class Document {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documentContent;
    private String name;
    @OneToMany(mappedBy = "document")
    private List<DocumentVersion> versions;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Document(Long id, String documentContent, String name) {
        this.id = id;
        this.documentContent = documentContent;
        this.name=name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDocumentContent() {
        return documentContent;
    }
    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<DocumentVersion> getVersions() {
        return versions;
    }
    public String getStatus() {

        if(versions.isEmpty()){
                return "DRAFT";
        }
        return versions.get(versions.size()-1).getStatus().toString();
    }
    public String getUsername() {


        if(this.user==null){
            return null;
        }
        return this.user.getUsername();
    }
public void setUser(User user){
        this.user=user;
}

public User getUser() {
        return user;
}


    public String getVersion() {
       if(versions.isEmpty()){
          return null;
       }
        return String.valueOf(versions.get(versions.size()-1).getDocumentVersion());
    }


    public Document() {

    }


}
