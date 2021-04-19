package by.bsuir.repository;

import java.util.List;
import java.util.Optional;

/*Generic interface for CRUD operations
 * @params K - primary key of object
 *         V - object type*/
public interface CrudRepository<K, V> {

    V save(V obj);

    List<V> findAll();

    V findById(K key);

    Optional<V> findOne(K key);

    V update(V obj);

    K delete(V obj);
}
