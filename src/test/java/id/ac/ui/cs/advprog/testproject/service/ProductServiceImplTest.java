package id.ac.ui.cs.advprog.testproject.service;

import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.repository.ProductRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepositoryInterface productRepository;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("product-1");
        sampleProduct.setProductName("Sample");
        sampleProduct.setProductQuantity(1);
    }

    @Test
    void createShouldGenerateIdWhenMissing() {
        Product productWithoutId = new Product();
        productWithoutId.setProductName("Generated");
        productWithoutId.setProductQuantity(5);

        Product created = productService.create(productWithoutId);

        assertNotNull(created.getProductId());
        assertFalse(created.getProductId().isBlank());
        verify(productRepository).create(productWithoutId);
    }

    @Test
    void createShouldKeepExistingId() {
        Product created = productService.create(sampleProduct);

        assertEquals("product-1", created.getProductId());
        verify(productRepository).create(sampleProduct);
    }

    @Test
    void createShouldGenerateIdWhenBlank() {
        Product blankProduct = new Product();
        blankProduct.setProductId(" ");
        blankProduct.setProductName("Blank");
        blankProduct.setProductQuantity(2);

        Product created = productService.create(blankProduct);

        assertNotNull(created.getProductId());
        assertFalse(created.getProductId().isBlank());
        verify(productRepository).create(blankProduct);
    }

    @Test
    void findAllShouldConvertIteratorToList() {
        Product secondProduct = new Product();
        secondProduct.setProductId("product-2");
        List<Product> products = Arrays.asList(sampleProduct, secondProduct);
        Iterator<Product> iterator = products.iterator();
        doReturn(iterator).when(productRepository).findAll();

        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
        assertSame(sampleProduct, result.get(0));
        assertSame(secondProduct, result.get(1));
    }

    @Test
    void findByIdUpdateAndDeleteShouldDelegateToRepository() {
        doReturn(sampleProduct).when(productRepository).findById("product-1");
        doReturn(sampleProduct).when(productRepository).update(sampleProduct);
        doReturn(sampleProduct).when(productRepository).delete(sampleProduct);

        assertSame(sampleProduct, productService.findById("product-1"));
        assertSame(sampleProduct, productService.update(sampleProduct));
        assertSame(sampleProduct, productService.delete(sampleProduct));
    }
}
