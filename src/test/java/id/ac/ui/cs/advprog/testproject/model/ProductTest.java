package id.ac.ui.cs.advprog.testproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product product;


    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    // Positive scenario for getters
    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());

    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    // Negative scenario for getters
    @Test
    void testGetProductIdForNegativeScenario() {
        assertNotEquals("12345678-9012-1234-5678-901234567890", this.product.getProductId());

    }

    @Test
    void testGetProductNameForNegativeScenario() {
        assertNotEquals("Sampo Cap Bango", this.product.getProductName());
    }

    @Test
    void testGetProductQuantityForNegativeScenario() {
        assertNotEquals(50, this.product.getProductQuantity());
    }
}