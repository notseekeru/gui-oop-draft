package main.services;

import java.util.List;

import main.dao.ProductDao;
import main.model.Product;

public class ProductService {

    private final ProductDao productDao = new ProductDao();

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(int id) {
        return productDao.findById(id);
    }
}
