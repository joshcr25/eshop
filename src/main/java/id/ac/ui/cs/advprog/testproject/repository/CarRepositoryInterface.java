package id.ac.ui.cs.advprog.testproject.repository;

import id.ac.ui.cs.advprog.testproject.model.Car;

import java.util.Iterator;

public interface CarRepositoryInterface {
    Car create(Car car);
    Iterator<Car> findAll();
    Car findById(String id);
    Car update(String id, Car updatedCar);
    void delete(String id);
}
