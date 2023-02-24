package org.lessons.java.pizzeria.api;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class ApiPizzaController {

	@Autowired
	private PizzaRepository pizzaRepo;

	@GetMapping
	public List<Pizza> index(@RequestParam(name = "pizza", required = false) String keyword) {

		if (keyword == null || keyword.isEmpty())
			return pizzaRepo.findAll();
		else
			return pizzaRepo.findByNameLike("%" + keyword + "%");

	}

}
