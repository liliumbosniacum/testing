package com.lilium.testing.service;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.dto.UserInfoDTO;
import com.lilium.testing.dto.UserType;
import com.lilium.testing.helper.UserProviderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserInfoService userInfoService;

    @Autowired
    public UserService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public String helloWorld() {
        return "hello world";
    }

    public List<String> getAllUserNames() {
        return UserProviderHelper.getUsers().stream()
                .map(UserDTO::getName)
                .collect(Collectors.toList());
    }

    public UserDTO getJamesBond() {
        return UserProviderHelper.getUsers().stream()
                .filter(x -> x.getName().equals("James Bond"))
                .findFirst()
                .orElse(null);
    }

    public List<UserDTO> getAllAdminOrModUsers() {
        return UserProviderHelper.getUsers().stream()
                .filter(x -> x.getType() != UserType.USER)
                .collect(Collectors.toList());
    }

    public List<UserInfoDTO> getUserInfos(final List<String> userIds) {
        if(CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }

        return userIds.stream()
                .map(userId -> userInfoService.getUserInfo(userId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllUsers() {
        return UserProviderHelper.getUsers();
    }
}
