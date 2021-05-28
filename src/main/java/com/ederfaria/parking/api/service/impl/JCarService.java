package com.ederfaria.parking.api.service.impl;

import com.ederfaria.parking.api.entities.Car;
import com.ederfaria.parking.api.repository.ICarRepository;
import com.ederfaria.parking.api.service.ICarService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eder
 */
@Service
public class JCarService implements ICarService {

    @Autowired
    private ICarRepository repository;

    public JCarService() {
    }

    @PostConstruct
    private void insertDB() {
        System.out.println("================================ POVOANDO BANCO");
        List<Car> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                list.add(new Car("Monza - " + i, "Black", "GMO-8842", "GM"));
            } else {
                list.add(new Car("UNO - " + i, "White", "GMA-2233", "FIAT"));
            }
        }
        repository.saveAll(list);
    }

    @Override
    public boolean save(Car car) {
        if (car == null) {
            return false;
        }
        repository.save(car);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Car> read() {
        return repository.findAll();
    }

    @Override
    public boolean deleteByName(String name) {
        if (name == null) {
            return false;
        }
        repository.deleteByName(name);
        return true;
    }

}
