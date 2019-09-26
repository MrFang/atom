package ru.atom.lecture08.websocket.dao;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.atom.lecture08.websocket.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class UserDao {
    private static final CopyOnWriteArrayList<User> instance = new CopyOnWriteArrayList<>();

    public boolean save(User user) {
        return instance.add(user);
    }

    public List<User> getAll() {
        return instance;
    }

    @Nullable
    public User getByLogin(String name) {
        User toCompare = new User()
                .setName(name);

        List<User> userList = instance.stream()
                .filter(user -> user.equals(toCompare))
                .collect(Collectors.toList());

        return userList.size() == 0 ? null : userList.get(0);
    }
}
