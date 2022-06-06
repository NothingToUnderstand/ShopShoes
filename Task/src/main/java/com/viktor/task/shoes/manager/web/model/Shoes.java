package com.viktor.task.shoes.manager.web.model;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * Database model for shoes.
 */
@Entity
@Table(name = "shoes")
public class Shoes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "weightKg")
    private Float weightKg;
    @Column(name = "color")
    private String color;
    @Column(name = "quantity")
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Float weightKg) {
        this.weightKg = weightKg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Shoes() {
    }
   

    public Shoes(Integer id, String name, Float weightKg, String color, Integer quantity) {
        this.id = id;
        this.name = name;
        this.weightKg = weightKg;
        this.color = color;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Shoes.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("weightKg=" + weightKg)
                .add("color='" + color + "'")
                .add("quantity=" + quantity)
                .toString();
    }
}
