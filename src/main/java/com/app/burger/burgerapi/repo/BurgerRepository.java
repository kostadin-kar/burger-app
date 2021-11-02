package com.app.burger.burgerapi.repo;

import com.app.burger.burgerapi.repo.models.Burger;
import com.app.burger.burgerapi.repo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BurgerRepository extends JpaRepository<Burger, Long> {

    Burger findBurgerByIdAndUserId(Long id, User userId);

    List<Burger> findByUserId(User userId);

    void deleteBurgerByIdAndUserId(Long id, User userId);

    void deleteBurgersByUserId(User userId);
}
