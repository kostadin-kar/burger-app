package com.app.burger.burgerapi.repo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Ingredients")
@JsonIgnoreProperties(value = { "burgers" })
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Column(name = "weight", nullable = false, unique = true, precision = 2)
    private double weight;
    @Column(name = "price", nullable = false, precision = 2)
    private double price;
    @Column(name = "calories", nullable = false, precision = 2)
    private double calories;
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<BurgerIngredient> burgers;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, double weight, double price, double calories) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.calories = calories;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public List<BurgerIngredient> getBurgers() {
        return burgers;
    }

    public void setBurgers(List<BurgerIngredient> burgers) {
        this.burgers = burgers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.weight, weight) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.calories, calories) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, price, calories);
    }
}
