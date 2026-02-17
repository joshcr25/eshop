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

    @Test
    void testFindByIdWhenFound() {
        ProductRepository productRepo = new ProductRepository();
        Product product = new Product();
        product.setProductId("12345678-9012-3456-7890-123456789012");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);
        productRepo.create(product);

        Product productSearched = productRepo.findById("12345678-9012-3456-7890-123456789012");
        assertNotNull(productSearched);
        assertEquals( "12345678-9012-3456-7890-123456789012", productSearched.getProductId());
        assertEquals( "Sampo Cap Bango", product.getProductName());
        assertEquals( 10, product.getProductQuantity());

    }

    // Negative scenario
    @Test
    void testFindByIdWhenNotFoundButHasOneProductInRepository() {
        ProductRepository productRepo = new ProductRepository();
        Product product = new Product();
        product.setProductId("12345678-9012-3456-7890-123456789012");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);
        productRepo.create(product);

        Product productSearched = productRepo.findById("missing-id");
        assertNull(productSearched);
    }

    @Test
    void testFindByIdWhenNotFoundButHasTwoProductsInRepository() {
        ProductRepository productRepo = new ProductRepository();
        Product product1 = new Product();
        product1.setProductId("12345678-9012-3456-7890-123456789012");
        product1.setProductName("Sampo Cap Bango");
        product1.setProductQuantity(10);
        productRepo.create(product1);
        Product product2 = new Product();
        product2.setProductId("ebcaabce-1282-2911-2920-2728dbfe2929");
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(75);
        productRepo.create(product2);

        Product productSearched = productRepo.findById("14017182-9028-5647-292a-738201ab2627");
        assertNull(productSearched);
    }

    @Test
    void testFindByIdWhenNotFoundButNoProductInRepository() {
        Product product = productRepository.findById("36045928-baca-abae-ffff-19282722bacf");
        assertNull(product);
    }

    @Test
    void testUpdateWhenNotFound() {
        Product product = new Product();
        product.setProductId("7414fabce-9028-2829-1722-29102ba81701");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);

        Product updatedProduct = productRepository.update(product);
        assertNull(updatedProduct);
    }

    @Test
    void testDeleteWhenNotFound() {
        Product product = new Product();
        product.setProductId("1942b267-27b2-9282-2ba7-1919ab262190");
        product.setProductName("Sampo Cap Bango");
        product.setProductQuantity(10);

        Product deletedProduct = productRepository.delete(product);
        assertNull(deletedProduct);
    }

    @Test
    void testFindByIdWhenFoundAfterFirstMismatch() {
        Product product1 = new Product();
        product1.setProductId("17019999-24a1-baef-10eb-00082764b167");
        product1.setProductName("Sampo Cap Bango");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        Product second = new Product();
        second.setProductId("4abc6333-9007-b2a2-189a-aaaaa123bd0c");
        second.setProductName("Sampo Cap Bambang");
        second.setProductQuantity(2);
        productRepository.create(second);

        Product found = productRepository.findById("4abc6333-9007-b2a2-189a-aaaaa123bd0c");
        assertNotNull(found);
        assertEquals("4abc6333-9007-b2a2-189a-aaaaa123bd0c", found.getProductId());
    }

    @Test
    void testUpdateWhenFoundAfterFirstMismatch() {
        Product product1 = new Product();
        product1.setProductId("ddefabcd-0284-7505-9918-cbfeda172903");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(1);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("ff0b2929-3929-2828-77db-0b0dca7e8809");
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(2);
        productRepository.create(product2);

        Product updated = new Product();
        updated.setProductId("ff0b2929-3929-2828-77db-0b0dca7e8809");
        updated.setProductName("Kecap Mas Bango");
        updated.setProductQuantity(20);

        Product result = productRepository.update(updated);
        assertNotNull(result);
        assertEquals("Kecap Mas Bango", result.getProductName());
    }

    @Test
    void testDeleteWhenFoundAfterFirstMismatch() {
        Product first = new Product();
        first.setProductId("770719ab-7029-7ab2-24cc-11fffe09ab67");
        first.setProductName("Sampo Cap Bango");
        first.setProductQuantity(1);
        productRepository.create(first);

        Product second = new Product();
        second.setProductId("ab09e7ff-2ed9-7e11-ab0c-27edbac79101");
        second.setProductName("Sampo Cap Bambang");
        second.setProductQuantity(2);
        productRepository.create(second);

        Product deleted = productRepository.delete(second);
        assertNotNull(deleted);
        assertNull(productRepository.findById("ab09e7ff-2ed9-7e11-ab0c-27edbac79101"));
    }
    
}
