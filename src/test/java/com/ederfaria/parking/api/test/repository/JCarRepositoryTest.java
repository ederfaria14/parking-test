package com.ederfaria.parking.api.test.repository;

import com.ederfaria.parking.api.entities.Car;
import com.ederfaria.parking.api.repository.ICarRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author eder
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class JCarRepositoryTest {

    @Autowired
    private ICarRepository repository;

    @Before
    public void preTest() {
        List<Car> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Car entity = new Car("Monza-" + i, "Prata", "GMO 8842", "GM");
            list.add(entity);
        }
        repository.saveAll(list);
    }

    @Test
    public void create() {
        System.out.println("\n\n===================== CREATE ==========================================");
        Car entity = new Car("Monza", "Prata", "GMO 8842", "GM");
        repository.save(entity);

        Assertions.assertThat(entity.getId()).isNotNull();
        Assertions.assertThat(entity.getName()).isEqualTo("Monza");
        Assertions.assertThat(entity.getColor()).isEqualTo("Prata");
        Assertions.assertThat(entity.getMark()).isEqualTo("GM");
    }

    @Test
    public void read() {
        System.out.println("\n\n===================== READ ============================================");
        List<Car> list = repository.findAll();
        list.forEach(e-> System.out.println(e));
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    public void delete() {
        repository.deleteAll();
        List<Car> list = repository.findAll();
        System.out.println("\n\n===================== DELETE ===========================================");
        Assertions.assertThat(list.isEmpty()).isEqualTo(true);
    }
}
