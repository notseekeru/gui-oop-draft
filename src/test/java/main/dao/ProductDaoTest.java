package main.dao;

import main.model.Product;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDaoTest {

    @Test
    public void testFindAllReturnsList() {
        ProductDao dao = new ProductDao();

        List<Product> products = dao.findAll();

        assertThat(products).isNotNull();
    }
}
