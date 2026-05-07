package main.services;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    @Test
    public void testLoginWithInvalidCredentialsReturnsMinusOne() {
        userService service = new userService();

        int userId = service.login("definitely_invalid_user_12345", "wrongpass");

        assertThat(userId).isEqualTo(-1);
    }
}
