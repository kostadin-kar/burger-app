package com.app.burger.burgerapi.controller;

import com.app.burger.burgerapi.repo.models.Ingredient;
import com.app.burger.burgerapi.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients/{id}")
    public Ingredient getOneById(@PathVariable Long id) {
        return ingredientService.getIngredientById(id);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getAllById() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping("/ingredients")
    public Ingredient createOne(@RequestBody Ingredient ingredient) {
        return ingredientService.createIngredient(ingredient);
    }

    @DeleteMapping("/ingredients/{id}")
    public void deleteOneById(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
    }
}
