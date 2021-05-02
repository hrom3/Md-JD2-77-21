package by.bsuir.repository.impl;

import by.bsuir.domain.User;
import by.bsuir.repository.IUserRepository;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements IUserRepository {

    public static final String POSTRGES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/student_demo";
    public static final String DATABASE_LOGIN = "postgres";
    public static final String DATABASE_PASSWORD = "postgres";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DAY = "birth_date";
    private static final String LOGIN = "login";
    private static final String WEIGHT = "weight";
    private static final String IS_DELETED = "is_deleted";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    @Override
    public User save(User obj) {
        return null;
    }

    @Override
    public List<User> findAll() {

        final String findAllQuery = "select * from users order by id";

        List<User> result = new ArrayList<>();

        try {
            Class.forName(POSTRGES_DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcUrl = StringUtils.join(DATABASE_URL, DATABASE_PORT,
                DATABASE_NAME);

        try (Connection connection = DriverManager.getConnection(jdbcUrl,
                DATABASE_LOGIN,DATABASE_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllQuery);) {

            while (resultSet.next()) {
                result.add(parseResultSetAsUser(resultSet));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User findById(Long key) {
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

    private User parseResultSetAsUser(ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setBirthDay(resultSet.getDate(BIRTH_DAY));
        user.setLogin(resultSet.getString(LOGIN));
        user.setWeight(resultSet.getFloat(WEIGHT));
        user.setDeleted(resultSet.getBoolean(IS_DELETED));
        user.setCreated(resultSet.getTimestamp(CREATED));
        user.setChanged(resultSet.getTimestamp(CHANGED));

        return user;
    }
}
