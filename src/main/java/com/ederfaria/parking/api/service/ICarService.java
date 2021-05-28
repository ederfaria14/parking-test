package com.ederfaria.parking.api.service;

import com.ederfaria.parking.api.entities.Car;
import java.util.List;

/**
 *
 * @author eder
 */
public interface ICarService {

    public boolean save(Car car);

    public boolean delete(Long id);

    public boolean deleteByName(String name);

    public List<Car> read();

}
