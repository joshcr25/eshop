package id.ac.ui.cs.advprog.testproject.repository;

import id.ac.ui.cs.advprog.testproject.model.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface ProductRepositoryInterface {
    public Product create(Product product);

    public Iterator<Product> findAll();

    public Product findById(String productId);

    public Product update(Product updatedProduct);
    public Product delete(Product deletedProduct);
}

