package id.ac.ui.cs.advprog.testproject.controller;

import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductController controller;
    private ProductService service;
    private Model model;

    @BeforeEach
    void setUp() {
        controller = new ProductController();
        service = mock(ProductService.class);
        model = mock(Model.class);
        ReflectionTestUtils.setField(controller, "service", service);
    }

    @Test
    void createProductPage_shouldReturnCreateViewAndAddEmptyProduct() {
        String view = controller.createProductPage(model);

        assertEquals("createProduct", view);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void createProductPost_shouldCallServiceCreateAndRedirect() {
        Product product = new Product();
        String view = controller.createProductPost(product, model);

        verify(service).create(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void productListPage_shouldAddProductsAndReturnListView() {
        Product product = new Product();
        List<Product> list = Arrays.asList(product);
        when(service.findAll()).thenReturn(list);

        String view = controller.productListPage(model);

        verify(service).findAll();
        verify(model).addAttribute("products", list);
        assertEquals("productList", view);
    }

    @Test
    void editProductPage_whenProductFound_shouldReturnEditView() {
        Product product = new Product();
        when(service.findById("id1")).thenReturn(product);

        String view = controller.editProductPage("id1", model);

        verify(service).findById("id1");
        verify(model).addAttribute("product", product);
        assertEquals("editProduct", view);
    }

    @Test
    void editProductPage_whenProductNotFound_shouldRedirectToList() {
        when(service.findById("missing")).thenReturn(null);

        String view = controller.editProductPage("missing", model);

        assertEquals("redirect:/product/list", view);
    }

    @Test
    void editProductPost_shouldCallUpdateAndRedirect() {
        Product product = new Product();

        String view = controller.editProductPost(product);

        verify(service).update(product);
        assertEquals("redirect:list", view);
    }

    @Test
    void deleteProductPage_whenFound_shouldReturnDeleteView() {
        Product product = new Product();
        when(service.findById("id2")).thenReturn(product);

        String view = controller.deleteProductPage("id2", model);

        verify(service).findById("id2");
        verify(model).addAttribute("product", product);
        assertEquals("deleteProduct", view);
    }

    @Test
    void deleteProductPage_whenNotFound_shouldRedirectToList() {
        when(service.findById("nope")).thenReturn(null);

        String view = controller.deleteProductPage("nope", model);

        assertEquals("redirect:/product/list", view);
    }

    @Test
    void deleteProductPost_shouldCallDeleteAndRedirect() {
        Product product = new Product();

        String view = controller.deleteProductPost(product);

        verify(service).delete(product);
        assertEquals("redirect:list", view);
    }
}
