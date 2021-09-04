package com.lilium.testing.service;

import com.lilium.testing.dto.UserDTO;
import com.lilium.testing.dto.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
    }

    @Test
    void helloWorldTest() {
        String helloWorld = userService.helloWorld();

        assertThat(helloWorld).isNotNull();
        assertThat(helloWorld).isEqualTo("hello world");
    }

    @Test
    public void testGetAllUserNames() {
        final List<String> names = userService.getAllUserNames();

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
        final List<UserDTO> allUsers = userService.getAllUsers();

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
        final List<UserDTO> allAdminOrModUsers = userService.getAllAdminOrModUsers();

        assertThat(allAdminOrModUsers)
                .hasSize(2)
                .extracting(UserDTO::getType)
                .containsExactlyInAnyOrder(UserType.ADMIN, UserType.MODERATOR);
    }

    @Test
    public void testWithDesc() {
        final UserDTO dto = userService.getAllUsers().get(0);

        assertThat(dto.getAge())
                .as("Checking the age of user with name %s failed", dto.getName())
                .isEqualTo(21); // Change to some other value to see the desc
    }

    @Test
    public void testWithErrorMessageOverride() {
        final UserDTO dto = userService.getAllUsers().get(0);

        final int expectedAge = 21;
        assertThat(dto.getAge())
                .withFailMessage("Users age should be %s", expectedAge)
                .isEqualTo(expectedAge);
    }

    @Test
    public void testExceptions() {
        assertThatThrownBy(() -> {
            UserDTO user = userService.getAllUsers().get(55);
        }).isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageContaining("Index 55")
        .hasMessage("Index 55 out of bounds for length 3")
        .hasMessageStartingWith("Index 55 out")
        .hasMessageEndingWith("length 3")
        .hasStackTraceContaining("java.lang.ArrayIndexOutOfBoundsException");

        assertThatCode(() -> {
            UserDTO user = userService.getAllUsers().get(1);
        }).doesNotThrowAnyException();
    }
}
