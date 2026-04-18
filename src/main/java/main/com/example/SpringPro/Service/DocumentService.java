package main.com.example.SpringPro.Service;

import jakarta.transaction.Transactional;
import main.com.example.SpringPro.Model.Comment;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class DocumentService {


    @Autowired
    private final DocumentRepository documentRepo;
    private final VersionRepo versionRepo;
    private final UserRepo userRepo;
    private final CommentsRepo commentRepo;
    private final static List<DocumentVersion> previousVersions=new ArrayList<>();

    public DocumentService(DocumentRepository documentRepo, VersionRepo versionRepo, UserRepo userRepo, CommentsRepo commentRepo) {
        this.documentRepo = documentRepo;
        this.versionRepo = versionRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;

    }


    public DocumentVersion createVersion(Long doc_id, String content,Long id) throws RuntimeException {
        Document dco = documentRepo.findById(doc_id).orElse(null);
        DocumentVersion dcv= versionRepo.findTopByOrderByIdDesc();
        User user = userRepo.findById(id).orElse(null);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        Long newVersionNumber = (dcv != null) ? dcv.getId() + 1 : 1;
        if (dco == null) {
            throw new RuntimeException("Document not found");
        } else {
            DocumentVersion version = new DocumentVersion(content, user.getUsername(), newVersionNumber , dco, LocalTime.now(), Status_documentVer.STILL);
            DocumentService.previousVersions.add(version);
            return versionRepo.save(version);
        }
    }

    @Transactional
    public void approvedVersion(Long id, Long doc_id, String edit) throws RuntimeException {


        User user = userRepo.findById(id).orElse(null);
        if(user==null){
            throw new RuntimeException("User not found");
        }

        System.out.println("LOG: User " + user.getUsername() + " has role: " + user.getRole());
        if (user.getRole() != Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to approve this version");
        }
        DocumentVersion version = versionRepo.findByDocument_Id(doc_id);
        System.out.println("2. Намерена версия за документ " + doc_id + ": " + (version != null ? "ДА" : "НЕ"));
        System.out.println("3. Текущ статус на последната версия: " + version.getStatus());
        if(version == null) {
            throw new RuntimeException("Document not found");
        }
        if (version.getStatus() != Status_documentVer.STILL) {
            throw new RuntimeException("Document is not ready to be approved yet");
        } else {
            int nextDocumentVersion=version.getDocumentVersion()+1;
            version.setDocumentVersion(nextDocumentVersion);
            version.setStatus(Status_documentVer.APPROVED);
            version.setCheckAT(LocalTime.now());
            version.setEdit(edit);
            version.setName(user.getUsername());
            DocumentService.previousVersions.add(version);
            Document doc=documentRepo.findById(doc_id).orElse(null);
            /*if(doc!=null){
                doc.getVersions().get(version.getDocumentVersion()).setStatus(Status_documentVer.APPROVED);
                documentRepo.save(doc);
            }*/
            versionRepo.saveAndFlush(version);
            System.out.println("Статусът е сменен!");
        }

    }


    @Transactional
    public void declinedVersion(Long id, String edit, Long doc_id) throws RuntimeException {


        User user=userRepo.findById(id).orElse(null);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        System.out.println("DEBUG: User " + user.getUsername() + " has role: [" + user.getRole() + "]");
        if (user.getRole() != Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to decline this version");
        }
        DocumentVersion version = versionRepo.findByDocument_Id(doc_id);
        if (version.getStatus() == Status_documentVer.STILL) {
            int nextDocumentVersion=version.getDocumentVersion()+1;
            version.setDocumentVersion(nextDocumentVersion);
            version.setEdit(edit);
            version.setName(user.getUsername());
            version.setCheckAT(LocalTime.now());
            version.setStatus(Status_documentVer.REJECTED);
            DocumentService.previousVersions.add(version);
            versionRepo.save(version);
        } else {
            throw new RuntimeException("The Document is not waiting!");
        }

    }

    public Comment addComment(Long id,Long user_id, String text, Long version_id) throws RuntimeException {
        User user=userRepo.findById(user_id).orElse(null);
        if(user==null){

            throw new RuntimeException("User not found");
        }

        DocumentVersion version=versionRepo.findById(version_id).orElse(null);
        if(version==null){
            throw new RuntimeException("Version not found");
        }
        if (user.getRole() != Role_User.REVIEWER) {
            throw new RuntimeException("You are not allowed to add any comments");
        }
        Comment comment = new Comment(null,version,user,text,LocalTime.now());
        return commentRepo.save(comment);

    }




    public DocumentVersion editDocument(Long user_id ,Long doc_id) throws RuntimeException {

           DocumentVersion version = versionRepo.findByDocument_Id(doc_id);
           if(version==null){
               throw new RuntimeException("Version not found");
           }
           User user= userRepo.findById(user_id).orElse(null);
           if(user==null){
               throw new RuntimeException("User not found");
           }
           if(version.getStatus() != Status_documentVer.APPROVED &&  user.getRole()!=Role_User.AUTHOR){
               throw new RuntimeException("Document can be edited by the author and when it's in inspection");
           }
          int nextDocumentVersion=version.getDocumentVersion()+1;
          version.setDocumentVersion(nextDocumentVersion);
          version.setEdit(version.getEdit());
          version.setName(user.getUsername());
          version.setCheckAT(LocalTime.now());
          version.setStatus(Status_documentVer.IN_PROGRESS);
          return versionRepo.save(version);
        }



     public Document createDocument(Long user_id, String text, String name) throws RuntimeException {

         System.out.println("DEBUG: Търсим потребител с ID: " + user_id);
         User user = userRepo.findById(user_id).orElse(null);
         System.out.println("DEBUG: Намерен потребител: " + (user != null ? user.getUsername() : "НЕ Е НАМЕРЕН"));
         System.out.println("DEBUG: Роля в Java обекта: " + (user != null ? user.getRole() : "NULL"));

         if(user==null){
             throw new RuntimeException("User not found");
         }
        if(user.getRole()!= Role_User.AUTHOR){
            throw new RuntimeException("You are not allowed to create a document");
        }
        Document dco= new Document();
        dco.setName(name);
        dco.setDocumentContent(text);
        dco.setUser(user);
        return documentRepo.save(dco);
     }


     public List<DocumentVersion> historyOfDocumentVersion(Long user_id,Long document_id) throws RuntimeException {


        User user=userRepo.findById(user_id).orElse(null);
        if(user==null){
            throw new RuntimeException("User not found");
        }
        if (user.getRole() != Role_User.AUTHOR) {
            throw new RuntimeException("Only the author can see the history of the document's version");
        }
        List<DocumentVersion> currentVersion = new ArrayList<>();
        if(DocumentService.previousVersions!= null){

        for (DocumentVersion dc : previousVersions) {
            if (dc.getDocumentId().equals(document_id)) {
                currentVersion.add(dc);
            }
        }

        }
        return currentVersion;
    }

     public List<DocumentVersion> seeAllActiveVersions(Long user_id) throws RuntimeException {

        User user=userRepo.findById(user_id).orElse(null);
        if(user.getRole()!= Role_User.READER){
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
        List<DocumentVersion> combinedVersions= new ArrayList<>();
        combinedVersions.addAll(approveVersions);
        combinedVersions.addAll(activeVersions);
        return combinedVersions;
     }

     public DocumentVersion versionDone(Long user_id,Long doc_id) {
         User user = userRepo.findById(user_id).orElse(null);
         if (user == null) {
             throw new RuntimeException("User not found");
         }
         if (user.getRole() != Role_User.REVIEWER) {
             throw new RuntimeException("You are not allowed to use this function!");
         }
         DocumentVersion dco = versionRepo.findByDocument_Id(doc_id);
         if (dco.getStatus() == Status_documentVer.STILL) {
             throw new RuntimeException("The document is in \"Still\" mode ");
         }
         dco.setStatus(Status_documentVer.STILL);
         dco.setEdit(null);
         dco.setName(dco.getName());
         dco.setCheckAT(LocalTime.now());
         return versionRepo.save(dco);
     }
     public User createUser(Long id,Role_User role, String username, String password) throws RuntimeException {
        User user= new User(id,role,username,password);
        for (User users: userRepo.findAll()){
            if(users.getPassword().equals(password)){
               return null;
            }
        }

        return userRepo.save(user);
     }

     public User findUser(Long id)throws RuntimeException {
        User user = userRepo.findById(id).orElse(null);
        return user;
     }
    public Document getDocumentById(Long id){

        return documentRepo.findById(id).orElse(null);
    }

    public DocumentVersion getDocumentVersionById(Long id){
        return versionRepo.findById(id).orElse(null);
    }

    public User  getUserById(Long id){
        return userRepo.findById(id).orElse(null);
    }

    public List<Document> getDocuments(){
        return documentRepo.findAll();
    }
    public List<DocumentVersion> getDocumentVersions(){
        return versionRepo.findAll();
    }

    public User loginUser(String username,String password) throws RuntimeException {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
       return user;

    }

}
