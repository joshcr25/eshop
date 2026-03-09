package id.ac.ui.cs.advprog.testproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashOnDeliveryUnitTest {
    private CashOnDelivery cashOnDelivery;

    @BeforeEach
    void setUp() {
        cashOnDelivery = new CashOnDelivery();
    }

    @Test
    void isAddressEmptyShouldHandleNullBlankAndValid() {
        assertTrue(cashOnDelivery.isAddressEmpty(null));
        assertTrue(cashOnDelivery.isAddressEmpty("   "));
        assertFalse(cashOnDelivery.isAddressEmpty("Jl. Melati"));
    }

    @Test
    void isDeliveryFeeEmptyShouldHandleNullBlankAndValid() {
        assertTrue(cashOnDelivery.isDeliveryFeeEmpty(null));
        assertTrue(cashOnDelivery.isDeliveryFeeEmpty("   "));
        assertFalse(cashOnDelivery.isDeliveryFeeEmpty("10000"));
    }

    @Test
    void isValidDeliveryFeeShouldValidateFormatAndValue() {
        assertFalse(cashOnDelivery.isValidDeliveryFee(null));
        assertFalse(cashOnDelivery.isValidDeliveryFee(""));
        assertFalse(cashOnDelivery.isValidDeliveryFee("abc"));
        assertFalse(cashOnDelivery.isValidDeliveryFee("0"));
        assertFalse(cashOnDelivery.isValidDeliveryFee("-10"));
        assertTrue(cashOnDelivery.isValidDeliveryFee("1"));
    }

    @Test
    void isValidCashOnDeliveryShouldValidateMapContent() {
        assertFalse(cashOnDelivery.isValidCashOnDelivery(null));
        assertFalse(cashOnDelivery.isValidCashOnDelivery(Map.of()));

        Map<String, String> validData =
                cashOnDelivery.paymentDataWithAddressAndDeliveryFee("Jl. Mawar", "15000");
        assertTrue(cashOnDelivery.isValidCashOnDelivery(validData));
    }

    @Test
    void resolveStatusShouldReturnExpectedStatus() {
        Map<String, String> validData =
                cashOnDelivery.paymentDataWithAddressAndDeliveryFee("Jl. Mawar", "15000");
        assertEquals(CashOnDelivery.STATUS_SUCCESS, cashOnDelivery.resolveStatus(validData));
        assertEquals(CashOnDelivery.STATUS_REJECTED, cashOnDelivery.resolveStatus(Map.of()));
    }

    @Test
    void paymentDataWithAddressAndDeliveryFeeShouldCreateExpectedMap() {
        Map<String, String> paymentData =
                cashOnDelivery.paymentDataWithAddressAndDeliveryFee("Jl. Kenanga", "20000");

        assertEquals("Jl. Kenanga", paymentData.get(CashOnDelivery.ADDRESS_KEY));
        assertEquals("20000", paymentData.get(CashOnDelivery.DELIVERY_FEE_KEY));
    }
}
