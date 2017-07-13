package by.gsu.epamlab.db;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;

import java.sql.*;

public class DBConnector {

    public static Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            Class.forName(Constants.SQL_DRIVER);
            connection = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println(Constants.ERR_CLASS_LOADING_PROBLEM);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return connection;
    }

    public static void closeResultSet(ResultSet resultSet) throws DAOException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException(Constants.ERR_RESULT_SET_CLOSE);
            }
        }
    }

    public static void closeStatement(Statement statement) throws DAOException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DAOException(Constants.ERR_STATEMENT_CLOSE);
            }
        }
    }

    public static void closeConnection(Connection connection) throws DAOException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(Constants.ERR_CONNECTION_CLOSE);
            }
        }
    }

    public static void closePreparedStatements(PreparedStatement... statements) throws DAOException {
        for (PreparedStatement statement : statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DAOException(Constants.ERR_PREPARED_STATEMENT_CLOSE);
                }
            }
        }
    }

    public static void closeAll(Connection connection, ResultSet resultSet, Statement statement, PreparedStatement... statements) throws DAOException {
        closeResultSet(resultSet);
        closeStatement(statement);
        closePreparedStatements(statements);
        closeConnection(connection);
    }
}
