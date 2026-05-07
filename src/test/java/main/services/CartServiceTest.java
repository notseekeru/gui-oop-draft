package main.services;

import main.model.CartItem;
import main.model.Product;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartServiceTest {

    @Test
    public void testCheckoutWithEmptyCart() {
        CartService service = new CartService();
        String result = service.checkout(1, Collections.emptyList());

        assertThat(result).isEqualTo("Your cart is empty.");
    }

    @Test
    public void testCheckoutWithItems() {
        CartService service = new CartService();

        Product p = new Product(1, "Test", 10.0, 5);
        CartItem cartItem = new CartItem(1, 1, p, 2);

        String result = service.checkout(1, List.of(cartItem));

        assertThat(result).isEqualTo("Order placed! Thank you.");
    }
}
