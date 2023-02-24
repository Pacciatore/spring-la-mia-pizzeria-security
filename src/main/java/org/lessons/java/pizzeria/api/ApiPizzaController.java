package org.lessons.java.pizzeria.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("{id}")
	public ResponseEntity<Pizza> show(@PathVariable(name = "id") Integer id) {
		Optional<Pizza> result = pizzaRepo.findById(id);

		if (result.isPresent())
			return new ResponseEntity<Pizza>(result.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepo.save(pizza);
	}

}
