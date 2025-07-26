package com.codewitharal.store;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository  userRepository;
    private NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        System.out.println("User Service Created");
    }

    public void registerUser(User user){
        if(userRepository.findByEmail(user.getEmail())!=null){
            System.out.println("User already exists");
            return;
        }
        userRepository.save(user);
        notificationService.sendNotification(user.getEmail(),"User registered successfully");
    }
}
