package by.gsu.epamlab.factories;

import by.gsu.epamlab.implementations.DBUserImplementation;
import by.gsu.epamlab.interfaces.IUserDAO;

public class UserFactory {
    public static IUserDAO getClassFromFactory(){
        //return new MemoryUserImplementation();
        return new DBUserImplementation();
    }
}
