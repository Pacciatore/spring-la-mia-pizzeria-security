package org.lessons.java.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class SpecialOffers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	@FutureOrPresent(message = "Offer must begin now or in the future!")
	private LocalDate offerBegin;

	@NotNull
	@Future(message = "Offer must end in the future!")
	private LocalDate offerEnd;

	@NotNull
	@ManyToOne
	private Pizza pizza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getOfferBegin() {
		return offerBegin;
	}

	public void setOfferBegin(LocalDate offerBegin) {
		this.offerBegin = offerBegin;
	}

	public LocalDate getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(LocalDate offerEnd) {
		this.offerEnd = offerEnd;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
