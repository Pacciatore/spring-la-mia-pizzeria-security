package org.lessons.java.pizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

	@GetMapping
	public String index() {
		return "error";
	}

	@GetMapping("/{id}")
	public String show() {
		return "error";
	}

	@GetMapping("/edit/{id}")
	public String edit() {
		return "error";
	}

}
