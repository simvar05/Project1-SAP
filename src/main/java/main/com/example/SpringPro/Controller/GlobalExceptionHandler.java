package main.com.example.SpringPro.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(RuntimeException.class)
        public String handleException(RuntimeException ex, Model model, HttpServletRequest request) {

            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("url", request.getRequestURL());
            return "error-page";
        }
    }

