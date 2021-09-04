package com.lilium.testing.service;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.dto.UserType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static List<UserDTO> USERS = List.of(
            new UserDTO(UUID.randomUUID().toString(), "James Bond", "007", 21, UserType.ADMIN),
            new UserDTO(UUID.randomUUID().toString(), "Frank Castle", "punisher", 40, UserType.MODERATOR),
            new UserDTO(UUID.randomUUID().toString(), "T Pain", "MrT", 46, UserType.USER)
    );

    public String helloWorld() {
        return "hello world";
    }

    public List<String> getAllUserNames() {
        return USERS.stream()
                .map(UserDTO::getName)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllAdminOrModUsers() {
        return USERS.stream()
                .filter(x -> x.getType() != UserType.USER)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers() {
        return USERS;
    }
}
