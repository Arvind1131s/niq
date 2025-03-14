package com.example.niq.dto;

import lombok.Getter;
import lombok.Setter;


public class ShopperProductDTO {
    private String productId;
    private double relevancyScore;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getRelevancyScore() {
		return relevancyScore;
	}
	public void setRelevancyScore(double relevancyScore) {
		this.relevancyScore = relevancyScore;
	}
}

