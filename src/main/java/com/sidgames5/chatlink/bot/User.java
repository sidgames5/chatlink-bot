package com.sidgames5.chatlink.bot;

public class User {
    private String name;
    private String uniqueId;

    public User(String name, String uniqueId) {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
