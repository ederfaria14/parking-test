package com.ederfaria.parking.api.controller;

import com.ederfaria.parking.api.dtos.CarDTO;
import com.ederfaria.parking.api.entities.Car;
import com.ederfaria.parking.api.response.JResponseStatus;
import com.ederfaria.parking.api.service.ICarService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eder
 */
@RestController
@RequestMapping("api/v1/car")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private ICarService service;

    @GetMapping
    public ResponseEntity<JResponseStatus<List<CarDTO>>> read() {
        System.out.println("--------- Read Car");
        List<Car> list = service.read();

        List<CarDTO> listDTO = list.stream().map(e -> getCarDTOByCar(e)).collect(Collectors.toList());

        JResponseStatus<List<CarDTO>> response = new JResponseStatus<>();
        response.setData(listDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<JResponseStatus<String>> save(@Valid @RequestBody CarDTO entity) {
        System.out.println("--------- Save Car - " + entity.toString());
        boolean ret = service.save(getCarByCarDTO(entity));

        JResponseStatus<String> response = new JResponseStatus<>();
        if (ret) {
            response.setData("SAVED_CAR_SUCCESS");
            return ResponseEntity.ok(response);
        } else {
            response.setData("SAVED_CAR_ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JResponseStatus<String>> delete(@PathVariable("id") Long id) {
        System.out.println("--------- Delete Car - " + id);
        boolean ret = service.delete(id);

        JResponseStatus<String> response = new JResponseStatus<>();
        if (ret) {
            response.setData("DELETED_CAR_SUCCESS");
            return ResponseEntity.ok(response);
        } else {
            response.setData("DELETED_CAR_ERROR");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(response);
        }
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<JResponseStatus<String>> deleteByName(@PathVariable("name") String name) {
        System.out.println("--------- Delete Car by name - " + name);
        boolean ret = service.deleteByName(name);

        JResponseStatus<String> response = new JResponseStatus<>();
        if (ret) {
            response.setData("DELETED_CAR_SUCCESS_BY_NAME");
            return ResponseEntity.ok(response);
        } else {
            response.setData("DELETED_CAR_ERROR_BY_NAME");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(response);
        }
    }

    private Car getCarByCarDTO(CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setName(dto.getName());
        car.setColor(dto.getColor());
        car.setMark(dto.getMark());
        car.setPlaque(dto.getPlaque());
        return car;
    }

    private CarDTO getCarDTOByCar(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setName(car.getName());
        carDTO.setColor(car.getColor());
        carDTO.setMark(car.getMark());
        carDTO.setPlaque(car.getPlaque());
        return carDTO;
    }

}
