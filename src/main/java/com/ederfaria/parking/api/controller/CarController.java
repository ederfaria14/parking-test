package com.ederfaria.parking.api.controller;

import com.ederfaria.parking.api.entities.Car;
import com.ederfaria.parking.api.repository.ICarRepository;
import com.ederfaria.parking.api.response.JResponseStatus;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
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
    private ICarRepository repository;

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

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de carros"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar"),
        @ApiResponse(code = 500, message = "Gerada uma exceção ao tentar consultar carros"),})
    @GetMapping
    public ResponseEntity<JResponseStatus<List<Car>>> read() {
        System.out.println("--------- Read Car Controller");
        List<Car> list = repository.findAll();

        JResponseStatus<List<Car>> response = new JResponseStatus<>();
        response.setData(list);
        return ResponseEntity.ok(response);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cadastro/Atualização de carro com sucesso"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar"),
        @ApiResponse(code = 500, message = "Gerada uma exceção ao tentar salvar"),})
    @PostMapping
    public ResponseEntity<Car> save(@Valid @RequestBody Car entity) {
        System.out.println("--------- Save Car Controller - " + entity.toString());
        entity = repository.save(entity);
        return ResponseEntity.ok(entity);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Exclusão de carro com sucesso"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar"),
        @ApiResponse(code = 500, message = "Gerada uma exceção ao excluir"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<JResponseStatus<String>> delete(@PathVariable("id") Long id) {
        System.out.println("--------- Delete Car Controller - " + id);

        JResponseStatus<String> response = new JResponseStatus<>();
        List<Car> list = repository.findAll();
        Optional<Car> option = list.stream().filter(c -> c.getId() == id).findFirst();
        if (option != null) {
            repository.deleteById(id);
            response.setData("DELETED_CAR_SUCCESS");
            return ResponseEntity.ok(response);
        }
        response.setData("CAR_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

}
