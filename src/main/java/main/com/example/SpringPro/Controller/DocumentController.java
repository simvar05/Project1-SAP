package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.Document;

import java.util.List;


@RestController
public class DocumentController {

   private final DocumentService documentService;

   @Autowired
   public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }


    @PostMapping("/documents/create")
    public DocumentVersion createVersion(@RequestBody Document document,DocumentVersion version,User user) throws RuntimeException {
       return documentService.createVersion(document.getId(),document.getDocumentContent(),user);
    }

   @GetMapping("/documents/{id}")
    public Document getDocument(@PathVariable Long id,Model model){

       model.addAttribute("document", documentService.getDocumentById(id));
       return documentService.getDocumentById(id)   ;
   }

   @GetMapping("/documents")
    public List<Document> getDocuments(@PathVariable Long id,Model model){
       model.addAttribute("document", documentService.getDocuments());
       return documentService.getDocuments();
   }


}
