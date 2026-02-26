package id.ac.ui.cs.advprog.testproject.repository;

import id.ac.ui.cs.advprog.testproject.model.Product;

import java.util.Iterator;

public interface ProductRepositoryInterface {
    Product create(Product product);
    Iterator<Product> findAll();
    Product findById(String productId);
    Product update(Product updatedProduct);
    Product delete(Product updatedProduct);
}
