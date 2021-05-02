package by.bsuir.repository;

import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;

import java.util.List;

public interface IDealerRepository extends ICrudRepository <Long, Dealer> {

    List<Dealer> searchDealersByQuery(String query);

    List<Dealer> searchDealersForUser(User user);
}
