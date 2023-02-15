package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaRepository;

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

}
