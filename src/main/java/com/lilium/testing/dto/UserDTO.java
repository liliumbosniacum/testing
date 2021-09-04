package com.lilium.testing.dto;

public class UserDTO {
    private String id;
    private String name;
    private String username;
    private int age;
    private UserType type;

    public UserDTO(String id, String name, String username, int age, UserType type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.age = age;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public UserType getType() {
        return type;
    }
}
