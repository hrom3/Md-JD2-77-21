package by.bsuir.repository.impl;

import by.bsuir.beans.DatabaseProperties;
import by.bsuir.domain.User;
import by.bsuir.exception.NoSuchEntityException;
import by.bsuir.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    //@Qualifier("databaseProperties")
    private DatabaseProperties properties;

   // public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DAY = "birth_date";
    private static final String LOGIN = "login";
    private static final String WEIGHT = "weight";
    private static final String IS_DELETED = "is_deleted";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";

    private static final String DRIVER_NOT_LOADED =
            "JDBC Driver Cannot be loaded!";

    public UserRepositoryImpl() {
    }


    @Override
    public User save(User obj) {
        return null;
    }

    @Override
    public List<User> findAll() {

        final String findAllQuery = "select * from users order by id";
        final String driverName = properties.getDriverName();

        List<User> result = new ArrayList<>();

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.println(DRIVER_NOT_LOADED);
            throw new RuntimeException(DRIVER_NOT_LOADED);
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.getConnection(jdbcURL,
                login,password);
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
        final String findById = "select * from users where id = ?";
        final String driverName = properties.getDriverName();

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.println(DRIVER_NOT_LOADED);
            throw new RuntimeException(DRIVER_NOT_LOADED);
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try {
            connection = DriverManager.getConnection(jdbcURL, login, password);
            statement = connection.prepareStatement(findById);
            statement.setLong(1, key);
            rs = statement.executeQuery();
            //Row mapping
            if (rs.next()) {
                User user = new User();
                user = parseResultSetAsUser(rs);

                return user;
            }

            throw new NoSuchEntityException("No such user with id:" + key);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
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
