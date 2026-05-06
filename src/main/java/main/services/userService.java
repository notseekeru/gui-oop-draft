package main.services;

import main.dao.userDao;

public class userService{

    private final userDao userDao = new userDao();

    public int login(String username, String password){
        return userDao.authenticate(username,password);
    }

    public boolean register(String username, String password){
        return userDao.register(username, password);
    }

    public void ensureDatabaseExists() {
        userDao.ensureDatabaseExists();
    }
}
