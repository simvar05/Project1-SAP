package Controller;

import Model.Document;
import Service.DocumentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class DocumentController {

   private final DocumentService documentService;

   @Autowired
   public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }

   @GetMapping("/documents")
    public String getDocument(@PathVariable Long id,Model model){

       model.addAttribute("document", documentService.getDocumentById(id));
       return "document";
   }

   @GetMapping("/documents")
    public String getDocuments(@PathVariable Long id,Model model){
       model.addAttribute("document", documentService.getDocuments());
       return "The documents are loaded!";
   }


}
