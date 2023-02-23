package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping
	public String index(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAllByOrderByName();
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
	public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors())
			return "ingredients/create";

		ingredientRepository.save(formIngredient);

		return "redirect:/ingredients";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Ingredient ingredient = ingredientRepository.getReferenceById(id);
		model.addAttribute("ingredient", ingredient);

		return "ingredients/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors())
			return "ingredients/edit";

		ingredientRepository.save(formIngredient);

		return "redirect:/ingredients";
	}

}
