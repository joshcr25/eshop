package id.ac.ui.cs.advprog.testproject.service;

import id.ac.ui.cs.advprog.testproject.model.Order;
import id.ac.ui.cs.advprog.testproject.model.Payment;
import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.repository.OrderRepository;
import id.ac.ui.cs.advprog.testproject.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    private Order order;
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData = new HashMap<>();
        paymentData.put("Jalan Mock", "50000");
        paymentData.put("Jalan Escapia", "120000");

        payment = new Payment(order.getId(), "Cash On Delivery", paymentData);
    }

    @Test
    void testAddPayment() {
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, payment.getMethod(), paymentData);

        assertEquals(order.getId(), result.getId());
        assertEquals("Cash On Delivery", result.getMethod());
        assertEquals("PENDING", result.getStatus());
        assertEquals(paymentData, result.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccessUpdatesPaymentAndOrder() {
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderRepository, times(1)).findById(order.getId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testSetStatusRejectedSetsFailedAndUpdatesOrderToFailed() {
        doReturn(order).when(orderRepository).findById(order.getId());

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("FAILED", result.getStatus());
        verify(paymentRepository, times(1)).save(payment);
        verify(orderRepository, times(1)).findById(order.getId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testGetPaymentIfIdFound() {
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid-id");

        Payment result = paymentService.getPayment("invalid-id");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = new ArrayList<>();
        Payment paymentTwo = new Payment(
                "98f0ee15-c7ab-4d5a-a0f6-7c5304c8cf26",
                "Cash On Delivery",
                paymentData
        );
        paymentTwo.setStatus("SUCCESS");
        payments.add(payment);
        payments.add(paymentTwo);

        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testSetStatusInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "MEOW"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
        verify(orderRepository, times(0)).save(any(Order.class));
    }
}
