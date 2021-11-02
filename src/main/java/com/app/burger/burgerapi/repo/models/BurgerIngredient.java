package com.app.burger.burgerapi.repo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "burger_ingredient")
@JsonIgnoreProperties(value = { "id", "burger" })
public class BurgerIngredient {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "burger_id")
    private Burger burger;

    @ManyToOne()
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_count", nullable = false)
    private Integer ingredientCount;

    public BurgerIngredient() {
    }

    public BurgerIngredient(Long id, Burger burger, Ingredient ingredient, Integer ingredientCount) {
        this.id = id;
        this.burger = burger;
        this.ingredient = ingredient;
        this.ingredientCount = ingredientCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Burger getBurger() {
        return burger;
    }

    public void setBurger(Burger burger) {
        this.burger = burger;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(Integer ingredientCount) {
        this.ingredientCount = ingredientCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurgerIngredient that = (BurgerIngredient) o;
        return Objects.equals(burger, that.burger) &&
                Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(ingredientCount, that.ingredientCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(burger, ingredient, ingredientCount);
    }
}
