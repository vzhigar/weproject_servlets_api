package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.db.DBConnector;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.IUserDAO;

import java.sql.*;

public class DBUserImplementation implements IUserDAO {
    private final int LOGIN_INDEX = 1, PASSWORD_INDEX = 2;

    @Override
    public User getUser(String login, String password) throws DAOException {
        final String SELECT_USER_QUERY = "SELECT login, password FROM web.users WHERE login = ? AND password = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(SELECT_USER_QUERY);
            statement.setString(LOGIN_INDEX, login);
            statement.setString(PASSWORD_INDEX, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString(LOGIN_INDEX));
                user.setPassword(resultSet.getString(PASSWORD_INDEX));
            }
        } catch (SQLException e) {
            System.err.println(Constants.ERR_SQL_EXCEPTION + e.getMessage());
        } finally {
            DBConnector.closeAll(connection, resultSet, statement);
        }
        return user;
    }

    @Override
    public void addUser(String login, String password) throws DAOException {
        final String INSERT_USER_QUERY = "INSERT INTO web.users(login, password) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(INSERT_USER_QUERY);
            statement.setString(LOGIN_INDEX, login);
            statement.setString(PASSWORD_INDEX, password);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(Constants.ERR_SQL_EXCEPTION + e.getMessage());
        } finally {
            DBConnector.closePreparedStatements(statement);
            DBConnector.closeConnection(connection);
        }
    }

    @Override
    public boolean isLoginExists(String login) throws DAOException {
        final String QUERY = "SELECT * FROM web.users WHERE login = " + login + "";
        boolean isLoginExists = false;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QUERY);
            if (resultSet.next()) {
                isLoginExists = false;
            }
        } catch (SQLException e) {
            System.err.println(Constants.ERR_SQL_EXCEPTION + e.getMessage());
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(statement);
            DBConnector.closeConnection(connection);
        }
        return isLoginExists;
    }
}
