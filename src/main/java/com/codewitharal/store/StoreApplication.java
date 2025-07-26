package com.codewitharal.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        userService.registerUser(new User(1L,"aral","aralmuftuoglu@gmail.com","1234"));
        userService.registerUser(new User(1L,"aral","arlmuftuoglu@gmail.com","1234"));


//        OrderService orderService = context.getBean(OrderService.class);
//        OrderService orderService2 = context.getBean(OrderService.class);
//
//        orderService.placeOrder();
//
//
//        context.getBean(HeavyResource.class);

//        NotificationManager notificationManager =  context.getBean(NotificationManager.class);
//        notificationManager.sendNotification("sa as","a");
    }
}

