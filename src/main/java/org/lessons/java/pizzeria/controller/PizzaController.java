package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Ingredient;
import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repository.IngredientRepository;
import org.lessons.java.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private IngredientRepository ingredientRepository;

	@GetMapping
	public String index(@RequestParam(name = "pizza", required = false) String keyword, Model model) {
		List<Pizza> pizzaList;

		if (keyword == null || keyword.isEmpty())
			pizzaList = pizzaRepository.findAll();
		else
			pizzaList = pizzaRepository.findByNameLike("%" + keyword + "%");

		model.addAttribute("pizzas", pizzaList);

		return "pizzas/index";
	}

	@GetMapping("{id}")
	public String show(@PathVariable(name = "id") Integer id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		return "pizzas/show";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		List<Ingredient> ingredients = ingredientRepository.findAll();

		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredientsList", ingredients);

		return "pizzas/edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		// TODO: Vedi se c'è un modo migliore
		if (bindingResult.hasErrors()) {
			model.addAttribute("ingredientsList", ingredientRepository.findAll());
			return "pizzas/edit";
		}

		pizzaRepository.save(formPizza);

		return "redirect:/";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();

		List<Ingredient> ingredients = ingredientRepository.findAll();

		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredientsList", ingredients);

		return "pizzas/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return "pizzas/create";

		pizzaRepository.save(formPizza);

		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		pizzaRepository.deleteById(id);

		return "redirect:/";
	}

}
