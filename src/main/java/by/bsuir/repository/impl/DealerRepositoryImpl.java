package by.bsuir.repository.impl;
import by.bsuir.beans.DatabaseProperties;
import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;
import by.bsuir.exception.NoSuchEntityException;
import by.bsuir.repository.IDealerRepository;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("dealerRepository")
public class DealerRepositoryImpl implements IDealerRepository {

//    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    @Autowired
    @Qualifier("databaseProperties")
    //@Inject
    //@Named - JSR-330
    // = getAnnotationSpringContext().getBean(DatabaseProperties.class); - will work with @Autowired
    // = getAnnotationSpringContext().getBean("databaseProperties", DatabaseProperties.class); - will work with @Autowired + @Qualifier
    private DatabaseProperties properties;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String OPEN_DATE = "open_date";
    private static final String LOCATION_DESCRIPTION = "location_description";
    private static final String LOCATION_ID = "location_id";
    private static final String CREATED = "created";
    private static final String CHANGED = "changed";
    private static final String OPEN_HOUR = "open_hour";
    private static final String CLOSE_HOUR = "close_hour";

    @Override
    public Dealer save(Dealer obj) {
        final String insertDealerQuery = "insert into dealer (name, open_date," +
                " location_description, location_id, created, changed, " +
                "open_hour, close_hour) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.
                getConnection(jdbcURL, login, password);
             PreparedStatement statement =
                     connection.prepareStatement(insertDealerQuery);
             PreparedStatement lastInsertId = connection.prepareStatement(
                     "SELECT currval('dealer_id_seq') as lastInsertId")) {

            statement.setString(1, obj.getName());
            statement.setDate(2, new Date(obj.getOpenDate().getTime()));
            statement.setString(3, obj.getLocationDescription());
            statement.setLong(4, obj.getLocationId());
            statement.setTimestamp(5, obj.getCreated());
            statement.setTimestamp(6, obj.getChanged());
            statement.setInt(7, obj.getOpenHour());
            statement.setInt(8, obj.getCloseHour());

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
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Dealer> findAll() {
        final String findAllQuery = "select * from dealer order by id";

        List<Dealer> result = new ArrayList<>();

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.
                getConnection(jdbcURL, login, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAllQuery)) {

            while (resultSet.next()) {
                result.add(parseResultSetAsDealer(resultSet));
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Dealer findById(Long id) {
        final String findByID = "select * from dealer where id = ?";

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        ResultSet resultSet;
        try (Connection connection =
                     DriverManager.getConnection(jdbcURL, login, password);
             PreparedStatement statement = connection.prepareStatement(findByID)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return parseResultSetAsDealer(resultSet);
            }

            throw new NoSuchEntityException("No such dealer with id:" + id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Optional<Dealer> findOne(Long key) {
        return Optional.of(findById(key));
    }

    @Override
    public Dealer update(Dealer obj) {
        final String updateDealerQuery = "update  dealer set name = ?, " +
                "open_date= ?, location_description= ?, location_id= ?, " +
                "created= ?, changed= ?, open_hour= ?, close_hour= ? " +
                "where id = ?";

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        try (Connection connection = DriverManager.
                getConnection(jdbcURL, login, password);
             PreparedStatement statement = connection.
                     prepareStatement(updateDealerQuery)) {

            statement.setString(1, obj.getName());
            statement.setDate(2, new Date(obj.getOpenDate().getTime()));
            statement.setString(3, obj.getLocationDescription());
            statement.setLong(4, obj.getLocationId());
            statement.setTimestamp(5, obj.getCreated());
            statement.setTimestamp(6, obj.getChanged());
            statement.setInt(7, obj.getOpenHour());
            statement.setInt(8, obj.getCloseHour());
            statement.setLong(9, obj.getId());

            statement.executeUpdate();
            return findById(obj.getId());

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public void delete(Dealer obj) {
        final String findByIdQuery = "delete from dealer where id = ?";

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
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
            throw new RuntimeException("SQL Issues!");
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
    public List<Dealer> searchDealersByQuery(String query) {
        String srtr1 = ("'%").concat(query).concat("%'");

        final String findByString = "select * from dealer where name ilike "
                + srtr1 ;

        List<Dealer> result = new ArrayList<>();

        try {
            //Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        ResultSet resultSet;
        try (Connection connection =
                     DriverManager.getConnection(jdbcURL, login, password);
             PreparedStatement statement = connection.prepareStatement(findByString)) {

    //        statement.setString(1, stringForSearch);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(parseResultSetAsDealer(resultSet));
            }
            return result;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<String> searchDealersForUser(User user) {

        Long id = user.getId();

        final String findDealersForUser = "select search_dealers_for_user(?)";

        List<String> result = new ArrayList<>();

        try {
            //.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            Class.forName(properties.getDriverName());
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        String jdbcURL = properties.getUrl();
        String login = properties.getLogin();
        String password = properties.getPassword();

        ResultSet resultSet;
        try (Connection connection =
                     DriverManager.getConnection(jdbcURL, login, password);
             PreparedStatement statement =
                     connection.prepareStatement(findDealersForUser)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                throw new NoSuchEntityException("No such dealer or user #" + id
                        +" does not have car");
            }
            while (resultSet.next()) {
                result.add(resultSet.getString("search_dealers_for_user"));
            }
            return result;


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    private Dealer parseResultSetAsDealer(ResultSet resultSet)
            throws SQLException {

        Dealer dealer = new Dealer();
        dealer.setId(resultSet.getLong(ID));
        dealer.setName(resultSet.getString(NAME));
        dealer.setOpenDate(resultSet.getDate(OPEN_DATE));
        dealer.setLocationDescription(resultSet.getString(LOCATION_DESCRIPTION));
        dealer.setLocationId(resultSet.getLong(LOCATION_ID));
        dealer.setCreated(resultSet.getTimestamp(CREATED));
        dealer.setChanged(resultSet.getTimestamp(CHANGED));
        dealer.setOpenHour(resultSet.getInt(OPEN_HOUR));
        dealer.setCloseHour(resultSet.getInt(CLOSE_HOUR));

        return dealer;
    }
}
