package main.dao;

import main.model.Product;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {

    @Test
    public void testFindAllReturnsList() {
        ProductDao dao = new ProductDao();

        // This will connect to shopfx.db
        // Depending on existing data, the list could be empty or not
        List<Product> products = dao.findAll();

        assertThat(products).isNotNull();
        // Just verify execution didn't throw an exception and returned a proper list object
    }
}
