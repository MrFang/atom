package ru.atom.lecture08.websocket.model;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User)o;

        return user.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
