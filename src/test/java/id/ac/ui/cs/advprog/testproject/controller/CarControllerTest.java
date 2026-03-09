package id.ac.ui.cs.advprog.testproject.controller;

import id.ac.ui.cs.advprog.testproject.model.Car;
import id.ac.ui.cs.advprog.testproject.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CarControllerTest {
    private CarController controller;
    private CarService carService;
    private Model model;

    @BeforeEach
    void setUp() {
        controller = new CarController();
        carService = mock(CarService.class);
        model = mock(Model.class);
        ReflectionTestUtils.setField(controller, "carService", carService);
    }

    @Test
    void createCarPageShouldReturnViewAndAttachEmptyCar() {
        String view = controller.createCarPage(model);
        assertEquals("createCar", view);
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void createCarPostShouldCallServiceAndRedirect() {
        Car car = new Car();
        String view = controller.createCarPost(car);
        verify(carService).create(car);
        assertEquals("redirect:/car/listCar", view);
    }

    @Test
    void carListPageShouldAttachCarsAndReturnView() {
        List<Car> cars = List.of(new Car());
        when(carService.findAll()).thenReturn(cars);

        String view = controller.carListPage(model);

        verify(model).addAttribute("cars", cars);
        assertEquals("carList", view);
    }

    @Test
    void editCarPageShouldHandleFoundAndNotFoundCases() {
        Car car = new Car();
        when(carService.findById("car-1")).thenReturn(car);
        when(carService.findById("missing")).thenReturn(null);

        String foundView = controller.editCarPage("car-1", model);
        assertEquals("editCar", foundView);
        verify(model).addAttribute("car", car);

        String notFoundView = controller.editCarPage("missing", model);
        assertEquals("redirect:/car/listCar", notFoundView);
    }

    @Test
    void editCarPostShouldDelegateAndRedirect() {
        Car car = new Car();
        car.setCarId("car-2");

        String view = controller.editCarPost(car);
        verify(carService).update("car-2", car);
        assertEquals("redirect:listCar", view);
    }

    @Test
    void deleteCarShouldDelegateAndRedirect() {
        String view = controller.deleteCar("car-3");
        verify(carService).deleteCarById("car-3");
        assertEquals("redirect:listCar", view);
    }
}
