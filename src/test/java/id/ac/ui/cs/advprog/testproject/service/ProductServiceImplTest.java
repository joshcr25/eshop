package id.ac.ui.cs.advprog.testproject.service;

import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductServiceImpl service;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        service = new ProductServiceImpl();
        productRepository = mock(ProductRepository.class);
        ReflectionTestUtils.setField(service, "productRepository", productRepository);
    }

    @Test
    void createWhenIdBlankShouldGenerateId() {
        Product product = new Product();
        product.setProductId(" ");
        product.setProductName("Item");
        product.setProductQuantity(1);

        Product created = service.create(product);

        assertNotNull(created.getProductId());
        assertFalse(created.getProductId().isBlank());
        verify(productRepository).create(product);
    }

    @Test
    void createWhenIdNullShouldGenerateId() {
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("Item");
        product.setProductQuantity(1);

        Product created = service.create(product);

        assertNotNull(created.getProductId());
        assertFalse(created.getProductId().isBlank());
        verify(productRepository).create(product);
    }

    @Test
    void createWhenIdAlreadyExistsShouldKeepId() {
        Product product = new Product();
        product.setProductId("fixed-id");
        product.setProductName("Item");
        product.setProductQuantity(1);

        Product created = service.create(product);

        assertEquals("fixed-id", created.getProductId());
        verify(productRepository).create(product);
    }

    @Test
    void findAllShouldConvertIteratorToList() {
        Product first = new Product();
        first.setProductId("id-1");
        Product second = new Product();
        second.setProductId("id-2");

        Iterator<Product> iterator = List.of(first, second).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = service.findAll();

        assertEquals(2, result.size());
        assertEquals("id-1", result.get(0).getProductId());
        assertEquals("id-2", result.get(1).getProductId());
        verify(productRepository).findAll();
    }

    @Test
    void findByIdShouldDelegateToRepository() {
        Product product = new Product();
        product.setProductId("id-1");
        when(productRepository.findById("id-1")).thenReturn(product);

        Product result = service.findById("id-1");

        assertNotNull(result);
        assertEquals("id-1", result.getProductId());
        verify(productRepository).findById("id-1");
    }

    @Test
    void updateShouldDelegateToRepository() {
        Product product = new Product();
        product.setProductId("id-1");
        when(productRepository.update(product)).thenReturn(product);

        Product result = service.update(product);

        assertSame(product, result);
        verify(productRepository).update(product);
    }

    @Test
    void deleteShouldDelegateToRepository() {
        Product product = new Product();
        product.setProductId("id-1");
        when(productRepository.delete(product)).thenReturn(product);

        Product result = service.delete(product);

        assertSame(product, result);
        verify(productRepository).delete(product);
    }
}
