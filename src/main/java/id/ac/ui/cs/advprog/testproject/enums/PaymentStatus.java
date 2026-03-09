package id.ac.ui.cs.advprog.testproject.enums;

import lombok.Getter;

public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    @Getter
    private final String value;

    PaymentStatus(String value) {this.value = value;}

    public static boolean contains(String param) {
        for (PaymentStatus paymentStatus : PaymentStatus. values()) {
            if (paymentStatus.name().equals(param)) {
                return true;

            }
        }
        return false;

    }
}
