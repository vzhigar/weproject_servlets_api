package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.db.DBConnector;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.utils.ParametersConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTaskImplementation implements ITaskDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<Task> getTasks(String login, String query) throws DAOException {
        final int PS_LOGIN_INDEX = 1;
        final int ID_INDEX = 1;
        final int NAME_INDEX = 3;
        final int DATE_INDEX = 4;
        final int DESCRIPTION_INDEX = 5;
        final int FILE_NAME_INDEX = 9;
        List<Task> list = new ArrayList<Task>();
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(PS_LOGIN_INDEX, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID_INDEX);
                String name = resultSet.getString(NAME_INDEX);
                Date date = resultSet.getDate(DATE_INDEX);
                String description = resultSet.getString(DESCRIPTION_INDEX);
                String fileName = resultSet.getString(FILE_NAME_INDEX);
                Task task = new Task(id, name, date, description, fileName);
                list.add(task);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, resultSet, null, statement);
        }
        return list;
    }

    @Override
    public void addTask(String login, String name, Date date, String description) throws DAOException {
        final int LOGIN_INDEX = 1;
        final int NAME_INDEX = 2;
        final int DATE_INDEX = 3;
        final int DESCRIPTION_INDEX = 4;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.ADD_QUERY);
            statement.setString(LOGIN_INDEX, login);
            statement.setString(NAME_INDEX, name);
            statement.setDate(DATE_INDEX, date);
            statement.setString(DESCRIPTION_INDEX, description);
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public void deleteTask(String[] id) throws DAOException {
        final int ID_INDEX = 1;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.DELETE_QUERY);
            List <Integer> list = ParametersConverter.convert(id);
            for (Integer i : list) {
                statement.setInt(ID_INDEX, i);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public void updateTask(int id, String name, Date date, String description) throws DAOException {
        final int NAME_INDEX = 1;
        final int DATE_INDEX = 2;
        final int DESCRIPTION_INDEX = 3;
        final int ID_INDEX = 4;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.UPDATE_QUERY);
            statement.setString(NAME_INDEX, name);
            statement.setDate(DATE_INDEX, date);
            statement.setString(DESCRIPTION_INDEX, description);
            statement.setInt(ID_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public void setFixed(String[] id, boolean isFixed) throws DAOException {
        final int FIXED_INDEX = 1;
        final int FIXED_DATE_INDEX = 2;
        final int ID_INDEX =     3;
        int fixed = (isFixed) ? 1 : 0;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.SET_FIXED_QUERY);
            List<Integer> list = ParametersConverter.convert(id);
            for (Integer i : list) {
                statement.setInt(FIXED_INDEX, fixed);
                Date date = new Date(System.currentTimeMillis());
                statement.setDate(FIXED_DATE_INDEX, date);
                statement.setInt(ID_INDEX, i);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public void setDeleted(String[] id, boolean isDeleted) throws DAOException {
        final int DELETED_INDEX = 1;
        final int ID_INDEX = 2;
        int deleted = (isDeleted) ? 1 : 0;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.SET_DELETED_QUERY);
            List<Integer> list = ParametersConverter.convert(id);
            for (Integer i : list) {
                statement.setInt(DELETED_INDEX, deleted);
                statement.setInt(ID_INDEX, i);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public Task getTaskById(int id) throws DAOException {
        final int ID_INDEX = 1;
        final int RS_NAME_INDEX = 3;
        final int RS_DATE_INDEX = 4;
        final int RS_DESCRIPTION_INDEX = 5;
        final int RS_FILE_NAME_INDEX = 9;
        Task task = null;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.GET_BY_ID_QUERY);
                    statement.setInt(ID_INDEX, id);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        String name = resultSet.getString(RS_NAME_INDEX);
                        Date date = resultSet.getDate(RS_DATE_INDEX);
                        String description = resultSet.getString(RS_DESCRIPTION_INDEX);
                        String fileName = resultSet.getString(RS_FILE_NAME_INDEX);
                        task = new Task(id, name, date, description, fileName);
                    }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, resultSet, null, statement);
        }
        return task;
    }

    @Override
    public void emptyRecycle(String login) throws DAOException {
        final int PS_LOGIN_INDEX = 1;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.EMPTY_RECYCLE_QUERY);
            statement.setString(PS_LOGIN_INDEX, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }

    @Override
    public void setFileName(int id, String fileName) throws DAOException {
        final int PS_FILENAME_INDEX = 1;
        final int PS_ID_INDEX = 2;
        try {
            connection = DBConnector.getConnection();
            statement = connection.prepareStatement(DbConstants.SET_FILE_NAME_QUERY);
            statement.setString(PS_FILENAME_INDEX, fileName);
            statement.setInt(PS_ID_INDEX, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e.getCause());
        } finally {
            DBConnector.closeAll(connection, null, null, statement);
        }
    }


    private static class DbConstants{
        static final String GET_BY_ID_QUERY = "SELECT * FROM web.tasks WHERE id = ?";
        static final String ADD_QUERY = "INSERT INTO web.tasks (login, name, date, description) VALUES (?, ?, ?, ?)";
        static final String DELETE_QUERY = "DELETE FROM web.tasks WHERE id = ?";
        static final String UPDATE_QUERY = "UPDATE web.tasks SET name = ?, date = ?, description = ? WHERE id = ?";
        static final String SET_FIXED_QUERY = "UPDATE web.tasks SET fixed = ?, fixedDate = ? WHERE id = ?";
        static final String SET_DELETED_QUERY = "UPDATE web.tasks SET deleted = ? WHERE id = ?";
        static final String EMPTY_RECYCLE_QUERY = "DELETE FROM web.tasks WHERE deleted = 1 AND login = ?";
        static final String SET_FILE_NAME_QUERY = "UPDATE web.tasks SET fileName = ? WHERE id = ?";
    }

}
