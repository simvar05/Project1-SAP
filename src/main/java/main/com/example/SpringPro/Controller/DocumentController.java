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


    @PostMapping("/documents/creates")
    public Document createDocument(@RequestBody DocumentDTO docDto) throws RuntimeException{

       return documentService.createDocument(docDto.getId(), docDto.getDoc_id(),docDto.getDocument_content(), docDto.getName());
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
