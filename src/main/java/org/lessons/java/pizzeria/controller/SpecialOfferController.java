package org.lessons.java.pizzeria.controller;

import java.util.List;

import org.lessons.java.pizzeria.model.SpecialOffer;
import org.lessons.java.pizzeria.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/special-offers")
public class SpecialOfferController {

	@Autowired
	SpecialOfferRepository offerRepo;

	@GetMapping
	public String index(Model model) {
		List<SpecialOffer> specialOffers = offerRepo.findAll();

		model.addAttribute("specialOffer", specialOffers);
		return "specialOffers/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		SpecialOffer specialOffer = new SpecialOffer();
		model.addAttribute("specialOffer", specialOffer);

		return "specialOffers/create";
	}

	@PostMapping("/create")
	public String store(SpecialOffer formOffer, Model model) {
		offerRepo.save(formOffer);

		return "redirect:/";
	}

}
