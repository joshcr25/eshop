package id.ac.ui.cs.advprog.testproject.voucher;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentVoucherUnitTest {
    private PaymentVoucher paymentVoucher;

    @BeforeEach
    void setUp() {
        paymentVoucher = new PaymentVoucher();
    }

    @Test
    void isValidVoucherShouldCoverAllValidationPaths() {
        assertFalse(paymentVoucher.isValidVoucher((String) null));
        assertFalse(paymentVoucher.isValidVoucher("ESHOP1234ABC567"));
        assertFalse(paymentVoucher.isValidVoucher("ABCDE1234ABC5678"));
        assertFalse(paymentVoucher.isValidVoucher("ESHOPABCDEFGHIJK"));
        assertTrue(paymentVoucher.isValidVoucher("ESHOP1234ABC5678"));
    }

    @Test
    void mapVariantAndResolveStatusShouldReturnExpectedValues() {
        assertFalse(paymentVoucher.isValidVoucher((Map<String, String>) null));
        assertFalse(paymentVoucher.isValidVoucher(Map.of()));
        assertTrue(paymentVoucher.isValidVoucher(paymentVoucher.paymentDataWithVoucher("ESHOP1234ABC5678")));

        assertEquals(PaymentVoucher.STATUS_SUCCESS, paymentVoucher.resolveStatus("ESHOP1234ABC5678"));
        assertEquals(PaymentVoucher.STATUS_REJECTED, paymentVoucher.resolveStatus("INVALID"));
        assertEquals(PaymentVoucher.STATUS_SUCCESS,
                paymentVoucher.resolveStatus(paymentVoucher.paymentDataWithVoucher("ESHOP1234ABC5678")));
        assertEquals(PaymentVoucher.STATUS_REJECTED, paymentVoucher.resolveStatus(Map.of()));
    }

    @Test
    void paymentDataWithVoucherShouldSetVoucherCodeKey() {
        Map<String, String> paymentData = paymentVoucher.paymentDataWithVoucher("ESHOP1234ABC5678");
        assertEquals("ESHOP1234ABC5678", paymentData.get(PaymentVoucher.VOUCHER_CODE_KEY));
    }
}
