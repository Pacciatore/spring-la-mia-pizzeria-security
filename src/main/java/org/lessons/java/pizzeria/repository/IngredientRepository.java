package org.lessons.java.pizzeria.repository;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	public List<Ingredient> findAllByOrderByName();

}
