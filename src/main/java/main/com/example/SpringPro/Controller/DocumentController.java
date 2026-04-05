package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Repo.*;

import java.util.List;


@RestController
public class DocumentController {

   private final DocumentService documentService;

   @Autowired
   public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }


    @PostMapping("/documents/create")
    public DocumentVersion createVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
       return documentService.createVersion(request.getDocument_id(), request.getDocument_content(), request.getUserid());
    }

@PostMapping("/documents/userCreate")
public User createUser(@RequestBody User user) throws RuntimeException {
       return documentService.createUser(user.getId(),user.getRoles(),user.getUsername(), user.getPassword());
}

@GetMapping("documents/userCreate/{id}")
public User getUser(@PathVariable Long id,Model model) throws RuntimeException {
       model.addAttribute("documents",documentService.getUserById(id));
       return documentService.getUserById(id);
}

@PostMapping("/documents/appVersion/{id}")
public String approveVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {


    System.out.println("DEBUG: Получено ID от Postman: " + request.getUserid());
    System.out.println("DEBUG: Получен Edit от Postman: " + request.getEdit());
      documentService.approvedVersion(request.getUserid(),request.getDocument_id(), request.getEdit());
      return "New Version approved for document: " + request.getDocument_id();

}

@GetMapping("/document/allVersions")
public List<DocumentVersion> getAllVersions() throws RuntimeException {

       return documentService.getDocumentVersions();
}


    @PostMapping("/documents/creates")
    public Document createDocument(@RequestBody User user,Long id, String documentContent,String name) throws RuntimeException{

       return documentService.createDocument(user,id,name,documentContent);
    }

   @GetMapping("/documents/{id}")
    public Document getDocument(@PathVariable Long id,Model model){

       model.addAttribute("document", documentService.getDocumentById(id));
       return documentService.getDocumentById(id);
   }

   @GetMapping("/documents/all")
    public List<Document> getDocuments(Model model){
       model.addAttribute("document", documentService.getDocuments());
       return documentService.getDocuments();
   }



}
