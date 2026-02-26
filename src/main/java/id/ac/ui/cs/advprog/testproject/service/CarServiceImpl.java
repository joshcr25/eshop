package id.ac.ui.cs.advprog.testproject.service;

import id.ac.ui.cs.advprog.testproject.model.Car;
import id.ac.ui.cs.advprog.testproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null || car.getCarId().isBlank()) {
            car.setCarId(UUID.randomUUID().toString());
        }
        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }
}
