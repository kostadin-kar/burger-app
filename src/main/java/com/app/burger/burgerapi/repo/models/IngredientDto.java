package com.app.burger.burgerapi.repo.models;

public class IngredientDto {

    private String name;
    private int count;

    public IngredientDto() {
    }

    public IngredientDto(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
