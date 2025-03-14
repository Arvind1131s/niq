package com.example.niq.dto;


import java.util.List;


public class ShopperRequestDTO {
    private String shopperId;
    private List<ShopperProductDTO> shelf;
	public String getShopperId() {
		return shopperId;
	}
	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
	}
	public List<ShopperProductDTO> getShelf() {
		return shelf;
	}
	public void setShelf(List<ShopperProductDTO> shelf) {
		this.shelf = shelf;
	}
}
