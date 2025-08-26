package com.codewitharal.store;

import com.codewitharal.store.entities.User;
import com.codewitharal.store.repositories.UserRepository;
import com.codewitharal.store.services.userService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);

        var service = context.getBean(userService.class );

        service.fetchPaginatedProducts(0, 10);
    }
}

