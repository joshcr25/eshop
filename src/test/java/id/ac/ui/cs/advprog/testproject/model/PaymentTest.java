package id.ac.ui.cs.advprog.testproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("bank", "TESTIBANK");
        paymentData.put("accountNumber", "1234567890");
        payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);


    }

    @Test
    void testGetId() {
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
    }

    @Test
    void testGetMethod() {
        assertEquals("BANK_TRANSFER", payment.getMethod());
    }

    @Test
    void testGetStatus() {
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testGetPaymentData() {
        assertEquals(2, payment.getPaymentData().size());
        assertEquals("TESTIBANK", payment.getPaymentData().get("bank"));
        assertEquals("1234567890", payment.getPaymentData().get("accountNumber"));
    }

    @Test
    void testGetIdNegativeScenario() {
        assertNotEquals("40671819-9282-3939-1901-9aaabbc1092f", payment.getId());
    }

    @Test
    void testGetMethodNegativeScenario() {
        assertNotEquals("CREDIT_CARD", payment.getMethod());
    }

    @Test
    void testGetStatusNegativeScenario() {
        assertNotEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetPaymentDataCanOverwriteValue() {
        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("wallet", "MockPay");

        payment.setPaymentData(newPaymentData);

        assertEquals(1, payment.getPaymentData().size());
        assertEquals("MockPay", payment.getPaymentData().get("wallet"));
    }

    @Test
    void testConstructorThrowsExceptionWhenPaymentDataIsEmpty() {
        Map<String, String> emptyPaymentData = new HashMap<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", emptyPaymentData)
        );

        assertEquals("Payment data is empty", exception.getMessage());
    }

    @Test
    void testSetStatusWithValidStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusWithInvalidStatusThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}
