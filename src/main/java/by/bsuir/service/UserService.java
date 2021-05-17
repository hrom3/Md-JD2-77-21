package by.bsuir.service;

import by.bsuir.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save (User user);

    User findById(Long userId);
}
