package com.app.burger.burgerapi.repo;

import com.app.burger.burgerapi.repo.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findIngredientByName(String name);
}
