package main.com.example.SpringPro.Controller;


import jakarta.servlet.http.HttpSession;
import main.com.example.SpringPro.Model.Comment;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.http.HttpStatus;
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
        @ResponseBody
        public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CommentDTO comment) throws RuntimeException {


            System.out.println("Received comment: " + comment.getComment());
            System.out.println("Received version ID: " + comment.getVersionid());
            System.out.println("Received userId: " + comment.getUserid());
            try {
                documentService.addComment(comment.getUserid(), comment.getComment(), comment.getVersionid());
                return ResponseEntity.ok("Comment created successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Comment not created");
            }
        }
        @GetMapping("/getcomments/{id}")
        public ResponseEntity<?> getComments(@PathVariable Long id) throws RuntimeException {


            try{
              documentService.findAllComments(id);
              return ResponseEntity.ok("Comments found successfully");
            }
            catch (Exception e){
                return ResponseEntity.status(500).body("Error" + e.getMessage());
            }
        }

}
