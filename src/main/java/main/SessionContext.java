package main;

public class SessionContext {
    private static Object currentUser;

    public static void clear() {
        currentUser = null;
    }

    public static Object getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Object user) {
        currentUser = user;
    }
}