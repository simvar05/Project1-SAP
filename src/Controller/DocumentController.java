package Controller;

import Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class DocumentController {

   private final DocumentService documentService;

   @Autowired
   public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }



}
