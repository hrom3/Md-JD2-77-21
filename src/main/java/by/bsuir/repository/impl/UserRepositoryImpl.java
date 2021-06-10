package by.bsuir.repository.impl;

import by.bsuir.beans.DatabaseProperties;
import by.bsuir.domain.User;
import by.bsuir.exception.NoSuchEntityException;
import by.bsuir.repository.IUserColumn;
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


    private static final String DRIVER_NOT_LOADED =
            "JDBC Driver Cannot be loaded!";
    private static final String SQL_ISSUE ="SQL Issues!";

    public UserRepositoryImpl() {
    }


    @Override
    public User save(User obj) {
        final String insertUserQuery = "insert into users (name, surname," +
                " birth_date, login, weight, is_deleted, " +
                "created, changed) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println(DRIVER_NOT_LOADED);
            throw new RuntimeException(DRIVER_NOT_LOADED);
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.
                getConnection(jdbcURL, login, password);
             PreparedStatement statement =
                     connection.prepareStatement(insertUserQuery);
             PreparedStatement lastInsertId = connection.prepareStatement(
                     "SELECT currval('users_id_seq') as lastInsertId")) {

            statement.setString(1, obj.getName());
            statement.setString(2,obj.getSurname());
            statement.setDate(3, new Date(obj.getBirthDay().getTime()));
            statement.setString(4, obj.getLogin());
            statement.setFloat(5, obj.getWeight());
            statement.setBoolean(6, obj.isDeleted());
            statement.setTimestamp(7, obj.getCreated());
            statement.setTimestamp(8, obj.getChanged());


            statement.executeUpdate();

            long insertedId;
            ResultSet lastIdResultSet = lastInsertId.executeQuery();
            if (lastIdResultSet.next()) {
                insertedId = lastIdResultSet.getLong("lastInsertId");
            } else {
                throw new RuntimeException("We cannot read sequence " +
                        "last value during User creation!");
            }

            return findById(insertedId);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(SQL_ISSUE);
        }
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
        return Optional.of(findById(key));
    }

    @Override
    public User update(User obj) {
        final String updateUserQuery = "update users set name = ?, surname= ?," +
                " birth_date= ?, login= ?, weight= ?, is_deleted= ?, created= ?," +
                " changed= ? WHERE id = 6";

        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println(DRIVER_NOT_LOADED);
            throw new RuntimeException(DRIVER_NOT_LOADED);
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.
                getConnection(jdbcURL, login, password);
             PreparedStatement statement = connection.
                     prepareStatement(updateUserQuery)) {

            statement.setString(1, obj.getName());
            statement.setString(2,obj.getSurname());
            statement.setDate(3, new Date(obj.getBirthDay().getTime()));
            statement.setString(4, obj.getLogin());
            statement.setFloat(5, obj.getWeight());
            statement.setBoolean(6, obj.isDeleted());
            statement.setTimestamp(7, obj.getCreated());
            statement.setTimestamp(8, obj.getChanged());
            statement.setLong(9, obj.getId());

            statement.executeUpdate();
            return findById(obj.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(SQL_ISSUE);
        }
    }

    @Override
    public void delete(User obj) {
        final String findByIdQuery = "delete from users where id = ?";

        try {
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println(DRIVER_NOT_LOADED);
            throw new RuntimeException(DRIVER_NOT_LOADED);
        }

        PreparedStatement statement = null;

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager
                .getConnection(jdbcURL, login, password)) {
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, obj.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(SQL_ISSUE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
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
        user.setId(resultSet.getLong(IUserColumn.ID));
        user.setName(resultSet.getString(IUserColumn.NAME));
        user.setSurname(resultSet.getString(IUserColumn.SURNAME));
        user.setBirthDay(resultSet.getDate(IUserColumn.BIRTH_DAY));
        user.setLogin(resultSet.getString(IUserColumn.LOGIN));
        user.setWeight(resultSet.getFloat(IUserColumn.WEIGHT));
        user.setDeleted(resultSet.getBoolean(IUserColumn.IS_DELETED));
        user.setCreated(resultSet.getTimestamp(IUserColumn.CREATED));
        user.setChanged(resultSet.getTimestamp(IUserColumn.CHANGED));

        return user;
    }
}
