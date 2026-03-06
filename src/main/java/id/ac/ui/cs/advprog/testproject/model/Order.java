package id.ac.ui.cs.advprog.testproject.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Order {
    @Getter
    String id;
    @Getter
    List<Product> products;
    @Getter
    Long orderTime;
    @Getter
    String author;
    @Getter @Setter
    String status;

    public Order(String id, List<Product> products, Long orderTime, String author) {

    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {

    }


}
