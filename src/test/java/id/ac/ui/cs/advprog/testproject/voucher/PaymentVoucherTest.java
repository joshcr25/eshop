package id.ac.ui.cs.advprog.testproject.voucher;

import id.ac.ui.cs.advprog.testproject.model.Order;
import id.ac.ui.cs.advprog.testproject.model.Payment;
import id.ac.ui.cs.advprog.testproject.model.Product;
import id.ac.ui.cs.advprog.testproject.repository.OrderRepository;
import id.ac.ui.cs.advprog.testproject.repository.PaymentRepository;
import id.ac.ui.cs.advprog.testproject.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentVoucherTest {
    private static final String PAYMENT_METHOD = "Voucher Code";
    private static final String VALID_VOUCHER_CODE = "ESHOP1234ABC5678";
    private static final String INVALID_LENGTH_VOUCHER = "ESHOP1234ABC567";
    private static final String INVALID_PREFIX_VOUCHER = "TOKO1234ABC5678";
    private static final String INVALID_DIGIT_COUNT_VOUCHER = "ESHOP12ABCD345678";

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    private Order order;
    private PaymentVoucher paymentVoucher;

    @BeforeEach
    void setUp() {
        paymentVoucher = new PaymentVoucher();

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
    }

    @Test
    void testAddPaymentWithValidVoucherCodeShouldSetStatusSuccess() {
        Map<String, String> paymentData = paymentVoucher.paymentDataWithVoucher(VALID_VOUCHER_CODE);
        createVoucherPaymentAndSetStatus(paymentData, PaymentVoucher.STATUS_SUCCESS, "SUCCESS");
        verify(paymentRepository, times(2)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testAddPaymentWithVoucherCodeLengthNot16ShouldSetStatusFailed() {
        Map<String, String> paymentData = paymentVoucher.paymentDataWithVoucher(INVALID_LENGTH_VOUCHER);
        createVoucherPaymentAndSetStatus(paymentData, PaymentVoucher.STATUS_REJECTED, "FAILED");
    }

    @Test
    void testAddPaymentWithVoucherCodeNotStartingWithEshopShouldSetStatusFailed() {
        Map<String, String> paymentData = paymentVoucher.paymentDataWithVoucher(INVALID_PREFIX_VOUCHER);
        createVoucherPaymentAndSetStatus(paymentData, PaymentVoucher.STATUS_REJECTED, "FAILED");
    }

    @Test
    void testAddPaymentWithVoucherCodeNumericCharsNot8ShouldSetStatusFailed() {
        Map<String, String> paymentData = paymentVoucher.paymentDataWithVoucher(INVALID_DIGIT_COUNT_VOUCHER);
        createVoucherPaymentAndSetStatus(paymentData, PaymentVoucher.STATUS_REJECTED, "FAILED");
    }

    private void createVoucherPaymentAndSetStatus(
            Map<String, String> paymentData,
            String voucherStatus,
            String expectedPaymentStatus
    ) {
        doReturn(order).when(orderRepository).findById(order.getId());
        doReturn(new Payment(order.getId(), PAYMENT_METHOD, paymentData))
                .when(paymentRepository).save(any(Payment.class));

        Payment createdPayment = paymentService.addPayment(order, PAYMENT_METHOD, paymentData);
        Payment updatedPayment = paymentService.setStatus(createdPayment, voucherStatus);

        assertEquals(expectedPaymentStatus, updatedPayment.getStatus());
    }
}
