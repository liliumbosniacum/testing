package com.lilium.testing.service;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.dto.UserInfoDTO;
import com.lilium.testing.helper.UserProviderHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    public UserInfoDTO getUserInfo(final String userId) {
        final UserDTO user = UserProviderHelper.getUser(userId);
        if (user == null) {
            return null;
        }

        final List<UserInfoDTO> userInfos = UserProviderHelper.getUserInfos();
        return userInfos.stream()
                .filter(x -> x.getUserId().endsWith(userId))
                .findFirst()
                .orElse(null);
    }
}
