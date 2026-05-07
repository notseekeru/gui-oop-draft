package main;

import main.model.userModel;

public class SessionContext {
    private static userModel currentUser;

    public static void clear() {
        currentUser = null;
    }

    public static userModel getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(userModel user) {
        currentUser = user;
    }
}
