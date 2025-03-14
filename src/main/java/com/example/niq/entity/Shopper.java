package com.example.niq.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.example.niq.entity.ShopperProduct;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shopper {
    
    @Id
    private String shopperId;

    public String getShopperId() {
		return shopperId;
	}

	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
	}

	public List<ShopperProduct> getShelf() {
		return shelf;
	}

	public void setShelf(List<ShopperProduct> shelf) {
		this.shelf = shelf;
	}

	@OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShopperProduct> shelf;
}
