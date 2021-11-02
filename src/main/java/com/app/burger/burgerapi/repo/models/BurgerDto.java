package com.app.burger.burgerapi.repo.models;

import java.util.List;

public class BurgerDto {

    private String name;
    private List<IngredientDto> ingredients;

    public BurgerDto() {
    }

    public BurgerDto(String name, List<IngredientDto> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
