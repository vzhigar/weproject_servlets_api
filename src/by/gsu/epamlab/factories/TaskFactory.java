package by.gsu.epamlab.factories;

import by.gsu.epamlab.implementations.DBTaskImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

public class TaskFactory {
    public static ITaskDAO getTaskFromFactory() {
        return new DBTaskImplementation();
    }
}
