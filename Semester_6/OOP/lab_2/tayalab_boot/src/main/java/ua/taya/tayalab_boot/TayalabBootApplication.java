package ua.taya.tayalab_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TayalabBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TayalabBootApplication.class, args);
    }

}
