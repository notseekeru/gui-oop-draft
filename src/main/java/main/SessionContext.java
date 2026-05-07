package main;

import main.model.UserModel;

public class SessionContext {
    private static UserModel currentUser;

    public static void clear() {
        currentUser = null;
    }

    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserModel user) {
        currentUser = user;
    }
}
