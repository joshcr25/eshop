package id.ac.ui.cs.advprog.testproject.service;

import id.ac.ui.cs.advprog.testproject.model.Car;
import id.ac.ui.cs.advprog.testproject.repository.CarRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepositoryInterface carRepository;

    private Car sampleCar;

    @BeforeEach
    void setUp() {
        sampleCar = new Car();
        sampleCar.setCarId("car-1");
        sampleCar.setCarName("Toyota");
        sampleCar.setCarColor("Black");
        sampleCar.setCarQuantity(1);
    }

    @Test
    void createShouldGenerateIdWhenMissing() {
        Car carWithoutId = new Car();
        carWithoutId.setCarName("Honda");
        carWithoutId.setCarColor("White");
        carWithoutId.setCarQuantity(2);

        Car created = carService.create(carWithoutId);

        assertNotNull(created.getCarId());
        assertFalse(created.getCarId().isBlank());
        verify(carRepository).create(carWithoutId);
    }

    @Test
    void createShouldKeepExistingId() {
        Car created = carService.create(sampleCar);

        assertEquals("car-1", created.getCarId());
        verify(carRepository).create(sampleCar);
    }

    @Test
    void findAllShouldConvertIteratorToList() {
        Car secondCar = new Car();
        secondCar.setCarId("car-2");
        List<Car> cars = Arrays.asList(sampleCar, secondCar);
        Iterator<Car> iterator = cars.iterator();
        doReturn(iterator).when(carRepository).findAll();

        List<Car> result = carService.findAll();

        assertEquals(2, result.size());
        assertSame(sampleCar, result.get(0));
        assertSame(secondCar, result.get(1));
    }

    @Test
    void findByIdShouldDelegateToRepository() {
        doReturn(sampleCar).when(carRepository).findById("car-1");
        assertSame(sampleCar, carService.findById("car-1"));
    }

    @Test
    void updateAndDeleteShouldDelegateToRepository() {
        carService.update("car-1", sampleCar);
        verify(carRepository).update("car-1", sampleCar);

        carService.deleteCarById("car-1");
        verify(carRepository).delete("car-1");
    }
}
