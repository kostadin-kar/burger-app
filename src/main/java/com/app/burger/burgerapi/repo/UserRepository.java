package com.app.burger.burgerapi.repo;

import com.app.burger.burgerapi.repo.models.User;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    User findByUsername(String username);

    List<User> findAll();
}
