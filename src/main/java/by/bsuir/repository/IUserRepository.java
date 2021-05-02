package by.bsuir.repository;

import by.bsuir.domain.User;

import java.util.List;

public interface IUserRepository extends ICrudRepository<Long, User> {

    List<User> searchUsersByQuery(String query);

    Double getUserExpensiveCarPrice(Integer userId);
}
