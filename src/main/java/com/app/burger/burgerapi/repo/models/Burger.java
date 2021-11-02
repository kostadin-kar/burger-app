package com.app.burger.burgerapi.repo.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Burgers")
public class Burger {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Column(name = "weight", nullable = false, precision = 2)
    private double weight;
    @Column(name = "calories", nullable = false, precision = 2)
    private double calories;
    @Column(name = "price", nullable = false, precision = 2)
    private double price;
    @OneToMany(mappedBy = "burger", cascade = CascadeType.ALL)
    private List<BurgerIngredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userId;

    public Burger() {
    }

    public Burger(Long id, String name, double weight, double calories, double price, User userId) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.price = price;
        this.userId = userId;
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

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<BurgerIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<BurgerIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Burger burger = (Burger) o;
        return Double.compare(burger.weight, weight) == 0 &&
                Double.compare(burger.calories, calories) == 0 &&
                Double.compare(burger.price, price) == 0 &&
                Objects.equals(id, burger.id) &&
                Objects.equals(name, burger.name) &&
                Objects.equals(userId, burger.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, calories, price, userId);
    }
}
