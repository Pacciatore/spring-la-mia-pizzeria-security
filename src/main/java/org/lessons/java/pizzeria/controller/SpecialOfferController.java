package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.Pizza;
import org.lessons.java.pizzeria.model.SpecialOffer;
import org.lessons.java.pizzeria.repository.PizzaRepository;
import org.lessons.java.pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/special-offers")
public class SpecialOfferController {

	@Autowired
	PizzaRepository pizzaRepo;

	@Autowired
	SpecialOfferRepository offerRepo;

	@GetMapping
	public String index(Model model) {
		List<SpecialOffer> specialOffers = offerRepo.findAll();

		model.addAttribute("specialOffers", specialOffers);
		return "specialOffers/index";
	}

	@GetMapping("/create")
	public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model)
			throws Exception {
		SpecialOffer specialOffer = new SpecialOffer();

		try {
			Pizza pizza = pizzaRepo.getReferenceById(pizzaId);
			specialOffer.setPizza(pizza);
		} catch (EntityNotFoundException e) {
			throw new Exception("Pizza not present. Id=" + pizzaId);
		}

		model.addAttribute("specialOffer", specialOffer);

		return "specialOffers/create";
	}

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formOffer, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors())
			return "specialOffers/create";

		offerRepo.save(formOffer);

		return "redirect:/";
	}

}
