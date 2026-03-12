package Service;

import Model.Comment;
import Model.Document;
import Model.DocumentVersion;
import Model.User;
import Repo.*;
import jakarta.persistence.*;
import jdk.internal.org.jline.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sun.security.provider.ML_DSA_Impls.version;

@Service
public class DocumentService {


    @Autowired
    private final DocumentRepository documentRepo;
    private final VersionRepo versionRepo;
    private final UserRepo userRepo;
    private final CommentsRepo commentRepo;

    public DocumentService(DocumentRepository documentRepo, VersionRepo versionRepo, UserRepo userRepo, CommentsRepo commentRepo) {
        this.documentRepo = documentRepo;
        this.versionRepo = versionRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }


    public DocumentVersion createVersion(Long doc_id, String content) throws RuntimeException {
        Document dco = documentRepo.findById(doc_id).orElse(null);
        if (dco == null) {
            throw new RuntimeException("Document not found");
        } else {
            DocumentVersion version = new DocumentVersion(content, "Simeon Angelov", 304304L, dco, LocalTime.now(), Status_documentVer.APPROVED);
            return versionRepo.save(version);
        }
    }

    public void approvedVersion(User user, Long doc_id, String edit) throws RuntimeException {


        if (user.getRoles() == Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to approve this version");
        }
        DocumentVersion version = versionRepo.findById(doc_id).orElse(null);
        if (version.getStatus() == Status_documentVer.WAITING) {
            DocumentVersion newVersion = new DocumentVersion(edit, user.getUsername(), version.getId() + 1, version.getDocument(), LocalTime.now(), Status_documentVer.APPROVED);
            versionRepo.save(version);
        } else {
            throw new RuntimeException("Document has already been approved");
        }


    }


    public void declinedVersion(User user,Long doc_id,String edit) throws RuntimeException {


        if (user.getRoles() != Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to decline this version");
        }
        DocumentVersion version = versionRepo.findById(doc_id).orElse(null);
        if (version.getStatus() == Status_documentVer.WAITING) {
            DocumentVersion newVersion = new DocumentVersion(edit, user.getUsername(), version.getId() + 1, version.getDocument(), LocalTime.now(), Status_documentVer.REJECTED);
            versionRepo.save(version);
        } else {
            throw new RuntimeException("Document has already been declined");
        }

    }

    public Comment addComment(Long user_id, String text, Long version_id) throws RuntimeException {
        User user=userRepo.findById(user_id);
        DocumentVersion version=versionRepo.findById(version_id).orElse(null);
        Comment previous_comment = commentRepo.findTopByOrderByIdDes();
        if (user.getRoles() == Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to add any comments");
        }
        Comment comment = new Comment(previous_comment.getId(),version.getId(),user.getId(),text,LocalTime.now());
        return commentRepo.save(comment);

    }




    public DocumentVersion editDocument(Long user_id ,Long doc_id, String edit) throws RuntimeException {

           DocumentVersion version = versionRepo.findById(doc_id).orElse(null);
           User user= userRepo.findById(user_id);
           if(version.getStatus() != Status_documentVer.IN_PROGRESS &&  user.getRoles()!=Role_User.AUTHOR){
               throw new RuntimeException("Document can be edited by the author and when it's in inspection");
           }
           DocumentVersion newVersion= new DocumentVersion(edit,user.getUsername(), version.getId()+1, version.getDocument(),LocalTime.now(),Status_documentVer.STILL);
           return versionRepo.save(newVersion);
        }



     public Document createDocument(User user, Long doc_id, String text, String name) throws RuntimeException {

        if(user.getRoles()!= Role_User.AUTHOR){
            throw new RuntimeException("You are not allowed to create a document");
        }
        Document dco= new Document(doc_id, text, name);
        return documentRepo.save(dco);
     }


     public void historyOfDocumentVersion(User user,String name, LocalTime date, Status_documentVer status) throws RuntimeException {
        if(user.getRoles()!= Role_User.AUTHOR){
            throw new RuntimeException("Only the author can see the history of the document's version");
        }
        DocumentVersion found_version;
        List<DocumentVersion> versionsByName= new ArrayList<>(versionRepo.findByName(name));
        if(!versionsByName.isEmpty()) {
            for (DocumentVersion version : versionsByName) {
                if (version.getStatus() == status && version.getDate().equals(date)) {
                    if (version.getDocumentVersion() > 1) {
                        DocumentVersion previousDocument = versionRepo.findByDocumentVersion(version.getDocumentVersion() - 1);
                        System.out.println("Change in current document version: " + version.getEdit() + "\n And the previous one:  " +previousDocument.getEdit());
                    }

                }
            }
        }
     }

     public void seeAllActiveVersions(Long user_id) throws RuntimeException {

        User user=userRepo.findById(user_id);
        if(user.getRoles()!= Role_User.READER){
            throw new RuntimeException("You are not allowed to use this function!");
        }
        List<DocumentVersion> approveVersions= new ArrayList<>(versionRepo.findByStatus(Status_documentVer.APPROVED));
        List<DocumentVersion> activeVersions= new ArrayList<>(versionRepo.findByStatus(Status_documentVer.DRAFT));
        for(DocumentVersion version : approveVersions){
            System.out.println(version.toString());
         }

        for(DocumentVersion version : activeVersions){
            System.out.println(version.toString());
        }
     }

     public User createUser(User user) throws RuntimeException {
        if(user.getRoles()!= Role_User.READER){}
     }



}
