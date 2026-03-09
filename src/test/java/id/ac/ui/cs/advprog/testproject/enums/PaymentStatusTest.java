package id.ac.ui.cs.advprog.testproject.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentStatusTest {
    @Test
    void containsShouldReturnTrueForKnownValues() {
        assertTrue(PaymentStatus.contains("PENDING"));
        assertTrue(PaymentStatus.contains("SUCCESS"));
        assertTrue(PaymentStatus.contains("FAILED"));
    }

    @Test
    void containsShouldReturnFalseForUnknownValues() {
        assertFalse(PaymentStatus.contains("pending"));
        assertFalse(PaymentStatus.contains("UNKNOWN"));
        assertFalse(PaymentStatus.contains(null));
    }

    @Test
    void getterShouldReturnExactEnumValue() {
        assertEquals("PENDING", PaymentStatus.PENDING.getValue());
        assertEquals("SUCCESS", PaymentStatus.SUCCESS.getValue());
        assertEquals("FAILED", PaymentStatus.FAILED.getValue());
    }
}
