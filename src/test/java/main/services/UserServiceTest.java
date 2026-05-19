package main.services;

import org.junit.jupiter.api.Test;
import main.model.UserModel;
import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    @Test
    public void testLoginWithInvalidCredentialsReturnsNull() {
        UserService service = new UserService();

        UserModel user = service.login("definitely_invalid_user_12345", "wrongpass");

        assertThat(user).isNull();
    }

    @Test
    public void testRegisterNewUserReturnsTrue() {
        UserService service = new UserService();
        String uniqueUsername = "testuser_" + System.currentTimeMillis();
        boolean registered = service.register(uniqueUsername, "password");
        assertThat(registered).isTrue();
    }

    @Test
    public void testRegisterExistingUserReturnsFalse() {
        UserService service = new UserService();
        String testUser = "existing_user";
        service.register(testUser, "pass");

        boolean registeredAgain = service.register(testUser, "pass2");
        assertThat(registeredAgain).isFalse();
    }
}
