package com.app.burger.burgerapi.repo;

import com.app.burger.burgerapi.repo.models.BurgerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerIngredientRepository extends JpaRepository<BurgerIngredient, Long> {


}
