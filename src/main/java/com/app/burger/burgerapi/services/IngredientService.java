package com.app.burger.burgerapi.services;

import com.app.burger.burgerapi.repo.IngredientRepository;
import com.app.burger.burgerapi.repo.models.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(new Ingredient());
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }
}
