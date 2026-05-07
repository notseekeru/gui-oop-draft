package main.services;

import main.dao.UserDao;
import main.model.UserModel;

public class UserService {

    private final UserDao userDao = new UserDao();

    public UserModel login(String username, String password) {
        return userDao.authenticate(username, password);
    }

    public boolean register(String username, String password) {
        if (userDao.userExists(username)) {
            return false;
        }
        return userDao.insertUser(username, password);
    }
}
