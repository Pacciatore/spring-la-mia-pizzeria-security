package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping
	public String index(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		model.addAttribute("ingredients", ingredients);

		return "ingredients/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);

		return "ingredients/create";
	}

	@PostMapping("/create")
	public String store(Ingredient formIngredient, Model model) {

		ingredientRepository.save(formIngredient);

		return "redirect:/ingredients";
	}

}
