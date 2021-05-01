package by.bsuir.repository;

import by.bsuir.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<Long, User> {

    List<User> searchUsersByQuery(String query);

    Double getUserExpensiveCarPrice(Integer userId);
}