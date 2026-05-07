package main.services;

import main.model.Product;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    @Test
    public void testFindAllProducts() {
        ProductService service = new ProductService();
        List<Product> products = service.findAll();

        assertThat(products).isNotNull();
    }
}
