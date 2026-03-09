package id.ac.ui.cs.advprog.testproject.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    @Test
    void gettersAndSettersShouldWork() {
        Car car = new Car();
        car.setCarId("car-1");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        assertEquals("car-1", car.getCarId());
        assertEquals("Toyota", car.getCarName());
        assertEquals("Red", car.getCarColor());
        assertEquals(3, car.getCarQuantity());
    }
}
