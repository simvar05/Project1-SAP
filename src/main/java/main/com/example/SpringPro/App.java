package main.com.example.SpringPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "main.com.example.SpringPro")
@EnableJpaRepositories(basePackages = "main.com.example.SpringPro.Repo")
@EntityScan(basePackages = "main.com.example.SpringPro.Model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
