package com.lilium.testing.controller;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/james")
    public UserDTO getJamesBond() {
        return userService.getJamesBond();
    }

    @GetMapping("/usernames")
    public List<String> getUsernames() {
        return userService.getAllUserNames();
    }
}
