package com.ederfaria.parking.api.test.controller;

import com.ederfaria.parking.api.entities.Car;
import com.ederfaria.parking.api.repository.ICarRepository;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author eder faria
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class JCarControllerTest {

    @LocalServerPort
    private int port;

    @Value("${auth.server}")
    private String localhost;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ICarRepository repository;

    @Before
    public void setPorts() {
        List<Car> asList = Arrays.asList(new Car("Monza", "Prata", "GMO-8890", "GM"), new Car("Gol", "Vermelho", "zms-9876", "VW"), new Car("Uno", "Azul", "AXO-4567", "FIAT"));
        BDDMockito.when(repository.findAll()).thenReturn(asList);
    }

    @Test
    public void getCarNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity(localhost + port + "/parking/api/v1/carss", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getCars() {
        ResponseEntity<String> response = restTemplate.getForEntity(localhost + port + "/parking/api/v1/car", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void save() {
        Car car = new Car("Monza", "Prata", "GMO-8890", "GM");
        BDDMockito.when(repository.save(car)).thenReturn(car);
        ResponseEntity<String> response = restTemplate.postForEntity(localhost + port + "/parking/api/v1/car", car, String.class);
        System.out.println(response);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void notSave() {
        Car car = new Car();
        BDDMockito.when(repository.save(car)).thenReturn(car);
        ResponseEntity<Car> response = restTemplate.postForEntity(localhost + port + "/parking/api/v1/car", car, Car.class);
        System.out.println(response);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void notSaveWithoutName() {
        Car car = new Car(null, "Prata", "GMO-8890", "GM");
        ResponseEntity<Car> response = restTemplate.postForEntity(localhost + port + "/parking/api/v1/car", car, Car.class);
        System.out.println(response);
        System.out.println(response.getBody());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void delete() {
        BDDMockito.doNothing().when(repository).deleteById(Long.valueOf(1));
        ResponseEntity<String> response = restTemplate.exchange(localhost + port + "/parking/api/v1/car/{id}", HttpMethod.DELETE, null, String.class, Long.valueOf(1));
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo("{\"data\":\"DELETED_CAR_SUCCESS\"}");
    }

}
