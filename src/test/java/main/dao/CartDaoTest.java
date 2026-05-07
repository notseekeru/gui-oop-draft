package main.dao;

import main.model.CartItem;
import main.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartDaoTest {

    private CartDao cartDao;
    private final int TEST_USER_ID = 999;
    private final int TEST_PRODUCT_ID = 1;

    @BeforeEach
    public void setup() {
        cartDao = new CartDao();
        cartDao.clearCart(TEST_USER_ID);
    }

    @AfterEach
    public void tearDown() {
        cartDao.clearCart(TEST_USER_ID);
    }

    @Test
    public void testAddAndGetCartItem() {
        cartDao.addToCart(TEST_USER_ID, TEST_PRODUCT_ID);

        List<CartItem> items = cartDao.getCartItems(TEST_USER_ID);
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getUserId()).isEqualTo(TEST_USER_ID);

        int count = cartDao.getCartCount(TEST_USER_ID);
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testRemoveItemFromCart() {
        cartDao.addToCart(TEST_USER_ID, TEST_PRODUCT_ID);
        List<CartItem> items = cartDao.getCartItems(TEST_USER_ID);

        cartDao.removeItem(items.get(0).getId());

        assertThat(cartDao.getCartItems(TEST_USER_ID)).isEmpty();
    }
}
