package com.simple.spring.controller;


import com.simple.spring.entity.User;
import com.simple.spring.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public void saveUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/{userId}")
    @Cacheable(value="user", key = "#userId")
    public User getUser(@PathVariable Integer userId){
        logger.debug("inside getUser() userId: {}", userId);
        return userService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    @CacheEvict(value="user", key = "#userId")
    public User deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/update")
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
        Cache cache = cacheManager.getCache("user");
        logger.debug("currently present cache value :: {}", cache.get(user.getUserId(), User.class));
        logger.debug("{}", cache.evictIfPresent(user.getUserId()));
        cache.put(user.getUserId(), user);
    }
}
