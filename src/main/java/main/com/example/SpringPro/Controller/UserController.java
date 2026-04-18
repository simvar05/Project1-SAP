package main.com.example.SpringPro.Controller;


import main.com.example.SpringPro.Model.Comment;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    DocumentService documentService;

    public UserController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) throws RuntimeException {
        System.out.println("Received username: " + user.getUsername());
        System.out.println("Received roles: " + user.getRole());
        System.out.println("Received password: " + user.getPassword());
     User createdUser= documentService.createUser(null,user.getRole(),user.getUsername(), user.getPassword());
       if(createdUser==null){
           return ResponseEntity.badRequest().body("User not created");
       }
       return ResponseEntity.ok("User created successfully");
    }
    @GetMapping("users/{id}")
    public User getUser(@PathVariable Long id, Model model) throws RuntimeException {
        model.addAttribute("users",documentService.getUserById(id));
        return documentService.getUserById(id);
    }

    @PostMapping("/comments/{id}")
    public Comment createComment(@PathVariable Long id,@RequestBody CommentDTO comment) throws RuntimeException {
        return documentService.addComment(id,comment.getUserid(),comment.getText(),comment.getVersionid());
    }

}
