package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.exceptions.DAOException;

import java.sql.Date;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(String login, String query) throws DAOException;
    void addTask(String login, String name, Date date, String description) throws DAOException;
    void deleteTask(String[] id) throws DAOException;
    void updateTask(int id, String name, Date date, String description) throws DAOException;
    void setFixed(String[] id, boolean isFixed) throws DAOException;
    void setDeleted(String[] id, boolean isDeleted) throws DAOException;
    Task getTaskById(int id) throws DAOException;
    void emptyRecycle(String login) throws DAOException;
    void setFileName(int id, String fileName) throws DAOException;
}
