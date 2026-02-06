package id.ac.ui.cs.advprog.testproject.repository;

import id.ac.ui.cs.advprog.testproject.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {


    @InjectMocks
    ProductRepository productRepository;


    @BeforeEach
    void setUp() {
    }
    // Positive scenario
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateWhenFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedData = new Product();
        updatedData.setProductId(product.getProductId());
        updatedData.setProductName("Sampo Cap Bango");
        updatedData.setProductQuantity(91);

        Product updatedProduct = productRepository.update(updatedData);
        assertNotNull(updatedProduct);
        assertEquals(updatedData.getProductId(), updatedProduct.getProductId());
        assertEquals(updatedData.getProductName(), updatedProduct.getProductName());
        assertEquals(updatedData.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testDeleteWhenFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product deletedProduct = productRepository.delete(product);
        assertNotNull(deletedProduct);
        assertEquals(product.getProductId(), deletedProduct.getProductId());
        assertNull(productRepository.findById(product.getProductId()));
    }

    // Negative scenario
    @Test
    void testFindByIdWhenNotFound() {
        Product product = productRepository.findById("missing-id");
        assertNull(product);
    }

    @Test
    void testUpdateWhenNotFound() {
        Product product = new Product();
        product.setProductId("missing-id");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);

        Product updatedProduct = productRepository.update(product);
        assertNull(updatedProduct);
    }

    @Test
    void testDeleteWhenNotFound() {
        Product product = new Product();
        product.setProductId("missing-id");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);

        Product deletedProduct = productRepository.delete(product);
        assertNull(deletedProduct);
    }
    
}
