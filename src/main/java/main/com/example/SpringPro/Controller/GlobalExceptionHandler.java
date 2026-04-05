package main.com.example.SpringPro.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ControllerAdvice
    public class GlobalExceptionHandlerAdvice {

        @ExceptionHandler(value=Exception.class)
        public String handleException(RuntimeException ex, HttpServletRequest request, Model model) {

            model.addAttribute("message", ex.getMessage());
            model.addAttribute("url", request.getRequestURL());
            return "error-page";
        }
    }
}
