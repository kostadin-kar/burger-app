package com.app.burger.burgerapi.services;

import com.app.burger.burgerapi.repo.BurgerIngredientRepository;
import com.app.burger.burgerapi.repo.BurgerRepository;
import com.app.burger.burgerapi.repo.IngredientRepository;
import com.app.burger.burgerapi.repo.UserRepository;
import com.app.burger.burgerapi.repo.models.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BurgerService {

    private static final double BREAD_CALORIES = 150;
    private static final double BREAD_WEIGHT_IN_GRAMS = 50;
    private static final double STARTING_PRICE = 2;

    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final BurgerIngredientRepository burgerIngredientRepository;

    public BurgerService(BurgerRepository burgerRepository,
                         IngredientRepository ingredientRepository,
                         UserRepository userRepository,
                         BurgerIngredientRepository burgerIngredientRepository) {
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.burgerIngredientRepository = burgerIngredientRepository;
    }

    public List<Burger> getAllBurgers(String username) {
        User user = userRepository.findByUsername(username);

        return burgerRepository.findByUserId(user);
    }

    public Burger getBurgerById(Long id, String username) {
        User user = userRepository.findByUsername(username);

        return burgerRepository.findBurgerByIdAndUserId(id, user);
    }

    @Transactional
    public Burger creteBurger(BurgerDto burgerDto, String username) {
        User user = userRepository.findByUsername(username);

        Burger burger = new Burger();
        burger.setName(burgerDto.getName());
        burger.setUserId(user);

        double calories = BREAD_CALORIES;
        double weight = BREAD_WEIGHT_IN_GRAMS;
        double price = STARTING_PRICE;

        List<Map.Entry<Ingredient, Integer>> ingredients = new ArrayList<>();
        for (IngredientDto ing : burgerDto.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findIngredientByName(ing.getName());

            calories += ingredient.getCalories() * ing.getCount();
            weight += ingredient.getWeight() * ing.getCount();
            price += ingredient.getPrice() * ing.getCount();
            ingredients.add(Map.entry(ingredient, ing.getCount()));
        }

        burger.setCalories(calories);
        burger.setPrice(price);
        burger.setWeight(weight);

        List<BurgerIngredient> burgerIngredients = toModel(burger, ingredients);
        burger.setIngredients(burgerIngredients);

        for (int i = 0; i < burgerIngredients.size(); i++) {
            burgerIngredientRepository.save(burgerIngredients.get(i));
        }
        burgerIngredientRepository.flush();

        return burgerRepository.save(burger);
    }

    @Transactional
    public void deleteBurgerById(Long id, String username) {
        User user = userRepository.findByUsername(username);

        burgerRepository.deleteBurgerByIdAndUserId(id, user);
    }

    @Transactional
    public void deleteAllBurgersPerUser(String username) {
        User user = userRepository.findByUsername(username);

        burgerRepository.deleteBurgersByUserId(user);
    }

    private List<BurgerIngredient> toModel(Burger burger, List<Map.Entry<Ingredient, Integer>> ingredients) {
        return ingredients.stream()
                .map(entry -> {
                    var ingredient = new BurgerIngredient();
                    ingredient.setBurger(burger);
                    ingredient.setIngredient(entry.getKey());
                    ingredient.setIngredientCount(entry.getValue());
                    return ingredient;
                })
                .collect(Collectors.toList());
    }
}
