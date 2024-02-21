package com.simple.spring.service.impl;

import com.simple.spring.entity.User;
import com.simple.spring.repo.UserRepository;
import com.simple.spring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @PostConstruct
    public void addSomeUsers(){

        userRepository.saveAll(
                Arrays.asList(
                        new User(1001, "ranesh", "paallli hill"),
                        new User(1004, "maesh", "niketan"),
                        new User(1008, "nabi", "hilltation house"),
                        new User(1071, "ganu", "sepang plats")
                        )
        );
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        logger.debug("{} user saved", user);
    }

    @Override
    public User getUser(int userId) {
        logger.debug("fetching user with ID: {}", userId);
        return userRepository.findById(userId).orElse(new User());
    }

    @Override
    public User deleteUser(int userId) {
        User user = userRepository.findById(userId).orElse(new User());
        logger.debug("deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        return user;
    }

    @Override
    public void updateUser(User userUpdate) {
        logger.debug("updating user with details: {}", userUpdate);
        User user = userRepository.findById(userUpdate.getUserId()).orElse(null);
        if(Objects.isNull(user)){
            logger.debug("not updating as user is null");
            return;
        };
        logger.debug("updating user with ID: {}", user.getUserId());
        userUpdate.setUserId(user.getUserId());
        userRepository.save(userUpdate);
    }
}
