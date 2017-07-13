package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.interfaces.IUserDAO;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserImplementation implements IUserDAO {

    private static final Map<String, String> map = new HashMap<String, String>();
    static {
        map.put("admin", "admin");
        map.put("user", "user");
        map.put("alex", "alex");
    }

    @Override
    public User getUser(String login, String password) {
        if (isLoginExists(login)) {
            String pass = map.get(login);
            if (pass.equals(password)) {
                return new User(login, password, Role.USER);
            }
        }
        return null;
    }

    @Override
    public void addUser(String login, String password) {
        if (!isLoginExists(login)) {
            map.put(login, password);
        }
    }

    @Override
    public boolean isLoginExists(String login) {
        return map.containsKey(login);
    }
}
