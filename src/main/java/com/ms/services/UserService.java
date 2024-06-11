package com.ms.services;

import com.ms.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> list = new ArrayList<>();


    public UserService() {
        list.add(new User("Ram", "1234", "ram@gmail.com"));
        list.add(new User("Sita", "1234", "s@gmail.com"));
        list.add(new User("Gita", "1234", "g@gmail.com"));
    }

    //Get all users
    public List<User> getAllUser() {
        return this.list;
    }

    //Get Unique USer by name
    public User getUserByName(String name) {
        return this.list.stream().filter((user)-> user.getUsername().equals(name)).findAny().orElse(null);
    }

    //Add User
    public User addUser(User user) {
        this.list.add(user);
        return user;
    }
}
