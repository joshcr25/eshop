package id.ac.ui.cs.advprog.testproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class Payment {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private String method;
    @Getter @Setter
    private String status;
    @Getter @Setter
    private Map<String, String> paymentData;

}
