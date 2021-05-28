package com.ederfaria.parking.api.repository;

import com.ederfaria.parking.api.entities.Car;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author eder
 */
public interface ICarRepository  extends JpaRepository<Car, Long>, JpaSpecificationExecutor {

//    @Transactional(readOnly = true)
//    @Query(value = "SELECT _name FROM certificate WHERE TRUE order by _name", nativeQuery = true)
//    List<String> readOrderbyName();

    @Transactional
    Long deleteByName(String name);

    @Transactional
    Long deleteByColor(String color);

}