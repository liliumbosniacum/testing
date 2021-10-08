package com.lilium.testing.service;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.dto.UserInfoDTO;
import com.lilium.testing.dto.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userServiceWithMock;
    private UserService userServiceWithSpy;
    private UserInfoService userInfoServiceMock;
    private UserInfoService userInfoServiceSpy;

    @BeforeEach
    public void setup() {
        userInfoServiceMock = mock(UserInfoService.class);
        userInfoServiceSpy = spy(new UserInfoService());

        doAnswer(a -> {
            final String userId = a.getArgument(0);
            if (userId.equals("not-there")) {
                return null;
            }

            final UserInfoDTO dto = new UserInfoDTO("1", "a", "n");

            return dto;
        }).when(userInfoServiceMock).getUserInfo(anyString());

        userServiceWithMock = new UserService(userInfoServiceMock);
        userServiceWithSpy = new UserService(userInfoServiceSpy);
    }

    @Test
    void helloWorldTest() {
        String helloWorld = userServiceWithMock.helloWorld();

        assertThat(helloWorld).isNotNull();
        assertThat(helloWorld).isEqualTo("hello world");
    }

    @Test
    public void testGetAllUserNames() {
        final List<String> names = userServiceWithMock.getAllUserNames();

        assertThat(names)
                .hasSize(3)
                .startsWith("James Bond")
                .doesNotContainNull()
                .doesNotContain("888")
                .containsAnyOf("James Bond", "Not There")
                .containsExactly("James Bond", "Frank Castle", "T Pain")
                .containsExactlyInAnyOrder("Frank Castle", "James Bond", "T Pain")
                .contains("Frank Castle");

        assertThat("Frank Castle").isIn(names);
    }

    @Test
    public void testGetAllUsers() {
        final List<UserDTO> allUsers = userServiceWithMock.getAllUsers();

        assertThat(allUsers)
                .hasSize(3)
                .extracting(UserDTO::getName)
                .containsExactly("James Bond", "Frank Castle", "T Pain");

        assertThat(allUsers)
                .extracting(UserDTO::getAge)
                .allMatch(age -> age > 20);

        final List<UserDTO> copyOfUsers = List.of(
                allUsers.get(1),
                allUsers.get(0),
                allUsers.get(2)
        );

        assertThat(allUsers).hasSameElementsAs(copyOfUsers);
    }

    @Test
    public void testGetAdminAndMods() {
        final List<UserDTO> allAdminOrModUsers = userServiceWithMock.getAllAdminOrModUsers();

        assertThat(allAdminOrModUsers)
                .hasSize(2)
                .extracting(UserDTO::getType)
                .containsExactlyInAnyOrder(UserType.ADMIN, UserType.MODERATOR);
    }

    @Test
    public void testWithDesc() {
        final UserDTO dto = userServiceWithMock.getAllUsers().get(0);

        assertThat(dto.getAge())
                .as("Checking the age of user with name %s failed", dto.getName())
                .isEqualTo(21); // Change to some other value to see the desc
    }

    @Test
    public void testWithErrorMessageOverride() {
        final UserDTO dto = userServiceWithMock.getAllUsers().get(0);

        final int expectedAge = 21;
        assertThat(dto.getAge())
                .withFailMessage("Users age should be %s", expectedAge)
                .isEqualTo(expectedAge);
    }

    @Test
    public void testExceptions() {
        assertThatThrownBy(() -> {
            UserDTO user = userServiceWithMock.getAllUsers().get(55);
        }).isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageContaining("Index 55")
        .hasMessage("Index 55 out of bounds for length 3")
        .hasMessageStartingWith("Index 55 out")
        .hasMessageEndingWith("length 3")
        .hasStackTraceContaining("java.lang.ArrayIndexOutOfBoundsException");

        assertThatCode(() -> {
            UserDTO user = userServiceWithMock.getAllUsers().get(1);
        }).doesNotThrowAnyException();
    }

    @Test
    public void testGetUserInfos_NoUserIds() {
        assertThat(userServiceWithMock.getUserInfos(null)).isEmpty();
        assertThat(userServiceWithMock.getUserInfos(new ArrayList<>())).isEmpty();
    }

    @Test
    public void testGetUserInfosWithMock() {
        final List<UserInfoDTO> userInfos = userServiceWithMock.getUserInfos(
                List.of("anything", "not-there")
        );

        assertThat(userInfos).hasSize(1);
        verify(userInfoServiceMock, times(2)).getUserInfo(anyString());
    }

    @Test
    public void testGetUserInfosWithSpy() {
        final List<UserDTO> allUsers = userServiceWithSpy.getAllUsers();

        final List<UserInfoDTO> userInfos = userServiceWithSpy.getUserInfos(
                List.of(allUsers.get(1).getId(), "not-there")
        );

        assertThat(userInfos).hasSize(1);
        verify(userInfoServiceSpy, times(2)).getUserInfo(anyString());
    }
}
