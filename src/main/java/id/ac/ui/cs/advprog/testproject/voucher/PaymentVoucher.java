package id.ac.ui.cs.advprog.testproject.voucher;

import java.util.HashMap;
import java.util.Map;

public class PaymentVoucher {
    public static final String VOUCHER_CODE_KEY = "voucherCode";
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_REJECTED = "REJECTED";
    public static final String REQUIRED_PREFIX = "ESHOP";
    public static final int REQUIRED_LENGTH = 16;
    public static final int REQUIRED_DIGIT_COUNT = 8;

    public boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != REQUIRED_LENGTH) {
            return false;
        }

        if (!voucherCode.startsWith(REQUIRED_PREFIX)) {
            return false;
        }

        long digitCount = voucherCode.chars().filter(Character::isDigit).count();
        return digitCount == REQUIRED_DIGIT_COUNT;
    }

    public boolean isValidVoucher(Map<String, String> paymentData) {
        if (paymentData == null) {
            return false;
        }
        return isValidVoucher(paymentData.get(VOUCHER_CODE_KEY));
    }

    public String resolveStatus(String voucherCode) {
        return isValidVoucher(voucherCode) ? STATUS_SUCCESS : STATUS_REJECTED;
    }

    public String resolveStatus(Map<String, String> paymentData) {
        return isValidVoucher(paymentData) ? STATUS_SUCCESS : STATUS_REJECTED;
    }

    public Map<String, String> paymentDataWithVoucher(String voucherCode) {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put(VOUCHER_CODE_KEY, voucherCode);
        return paymentData;
    }
}
