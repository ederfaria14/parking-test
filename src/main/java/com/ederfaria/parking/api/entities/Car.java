package com.ederfaria.parking.api.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eder
 */
@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "_name", nullable = false, length = 250)
    private String name;
    @Column(name = "color", nullable = false, length = 50)
    private String color;
    @Column(name = "plaque", nullable = false, length = 50)
    private String plaque;
    @Column(name = "mark", nullable = false, length = 50)
    private String mark;

    public Car() {
    }

    public Car(String name, String color, String plaque, String mark) {
        this.name = name;
        this.color = color;
        this.plaque = plaque;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", name=" + name + ", color=" + color + ", plaque=" + plaque + ", mark=" + mark + '}';
    }

}
