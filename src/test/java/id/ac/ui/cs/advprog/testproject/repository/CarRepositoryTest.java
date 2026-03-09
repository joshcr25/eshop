package id.ac.ui.cs.advprog.testproject.repository;

import id.ac.ui.cs.advprog.testproject.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarRepositoryTest {
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void createAndFindAllShouldReturnSavedCarsInOrder() {
        Car car1 = createCar("car-1", "Toyota", "Black", 1);
        Car car2 = createCar("car-2", "Honda", "White", 2);

        carRepository.create(car1);
        carRepository.create(car2);

        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
        assertEquals("car-1", iterator.next().getCarId());
        assertTrue(iterator.hasNext());
        assertEquals("car-2", iterator.next().getCarId());
        assertFalse(iterator.hasNext());
    }

    @Test
    void findByIdShouldReturnCarWhenFoundAndNullWhenMissing() {
        Car car = createCar("car-10", "Suzuki", "Blue", 5);
        carRepository.create(car);

        assertNotNull(carRepository.findById("car-10"));
        assertNull(carRepository.findById("missing-id"));
    }

    @Test
    void updateShouldModifyExistingCarAndReturnNullWhenNotFound() {
        carRepository.create(createCar("car-20", "Nissan", "Silver", 1));

        Car updatedCar = createCar("car-20", "Nissan Livina", "Gray", 7);
        Car updated = carRepository.update("car-20", updatedCar);
        assertNotNull(updated);
        assertEquals("Nissan Livina", updated.getCarName());
        assertEquals("Gray", updated.getCarColor());
        assertEquals(7, updated.getCarQuantity());

        Car notFoundResult = carRepository.update("missing-id", updatedCar);
        assertNull(notFoundResult);
    }

    @Test
    void deleteShouldRemoveMatchingCarAndIgnoreMissingId() {
        carRepository.create(createCar("car-30", "Mazda", "Red", 3));
        carRepository.create(createCar("car-31", "BMW", "Green", 4));

        carRepository.delete("car-30");
        assertNull(carRepository.findById("car-30"));
        assertNotNull(carRepository.findById("car-31"));

        carRepository.delete("does-not-exist");
        assertNotNull(carRepository.findById("car-31"));
    }

    private Car createCar(String id, String name, String color, int quantity) {
        Car car = new Car();
        car.setCarId(id);
        car.setCarName(name);
        car.setCarColor(color);
        car.setCarQuantity(quantity);
        return car;
    }
}
