package com.app.burger.burgerapi.controller;

import com.app.burger.burgerapi.repo.models.Burger;
import com.app.burger.burgerapi.repo.models.BurgerDto;
import com.app.burger.burgerapi.services.BurgerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
public class BurgerController {

    private final BurgerService burgerService;

    public BurgerController(BurgerService burgerService) {
        this.burgerService = requireNonNull(burgerService);
    }

    @GetMapping("/burgers/{id}")
    public Burger getOneById(@PathVariable Long id) {
        return burgerService.getBurgerById(id, getUsername());
    }

    @GetMapping("/burgers")
    public List<Burger> getAllById() {
        return burgerService.getAllBurgers(getUsername());
    }

    @PostMapping("/burgers")
    public Burger createOne(@RequestBody BurgerDto burger) {
        return burgerService.creteBurger(burger, getUsername());
    }

    @DeleteMapping("/burgers/{id}")
    public void deleteOneById(@PathVariable Long id) {
        burgerService.deleteBurgerById(id, getUsername());
    }

    @DeleteMapping("/burgers")
    public void deleteAll() {
        burgerService.deleteAllBurgersPerUser(getUsername());
    }

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
