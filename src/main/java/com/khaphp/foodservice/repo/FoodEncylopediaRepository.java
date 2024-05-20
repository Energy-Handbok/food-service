package com.khaphp.foodservice.repo;

import com.khaphp.foodservice.entity.FoodEncylopedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodEncylopediaRepository extends JpaRepository<FoodEncylopedia, String> {
    FoodEncylopedia findByName(String name);
}
