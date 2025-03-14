package com.example.niq.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopper_id", nullable = false)
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getRelevancyScore() {
		return relevancyScore;
	}

	public void setRelevancyScore(double relevancyScore) {
		this.relevancyScore = relevancyScore;
	}

	private double relevancyScore;
}
