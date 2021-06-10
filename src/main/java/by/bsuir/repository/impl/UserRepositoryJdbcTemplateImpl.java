package by.bsuir.repository.impl;

import by.bsuir.domain.User;
import by.bsuir.repository.IUserRepository;
import by.bsuir.repository.IUserColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UserRepositoryJdbcTemplateImpl implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public User save(User obj) {
        return null;
    }

    @Override
    public List<User> findAll() {
            return jdbcTemplate.query("select * from users", this::getUserRowMapper);
    }

    private User getUserRowMapper(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(IUserColumn.ID));
        user.setName(rs.getString(IUserColumn.NAME));
        user.setSurname(rs.getString(IUserColumn.SURNAME));
        user.setBirthDay(rs.getDate(IUserColumn.BIRTH_DAY));
        user.setLogin(rs.getString(IUserColumn.LOGIN));
        user.setWeight(rs.getFloat(IUserColumn.WEIGHT));
        user.setDeleted(rs.getBoolean(IUserColumn.IS_DELETED));
        user.setCreated(rs.getTimestamp(IUserColumn.CREATED));
        user.setChanged(rs.getTimestamp(IUserColumn.CHANGED));
        return user;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Optional<User> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public User update(User obj) {
        return null;
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public List<User> searchUsersByQuery(String query) {
        return null;
    }

    @Override
    public Double getUserExpensiveCarPrice(Integer userId) {
        return null;
    }
}
