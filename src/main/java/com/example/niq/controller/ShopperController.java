package com.example.niq.controller;

import com.example.niq.dto.ShopperRequestDTO;
import com.example.niq.dto.ShopperProductDTO;
import com.example.niq.dto.ProductDTO;
import com.example.niq.service.PersonalisedDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopperController {

    private final PersonalisedDataService shopperService;

    public ShopperController(PersonalisedDataService shopperService) {
        this.shopperService = shopperService;
    }

    @PostMapping("/internal/shoppers")
    public String saveShopper(@RequestBody ShopperRequestDTO shopperRequestDTO) {
        shopperService.saveShopperData(shopperRequestDTO);
        return "Shopper data saved successfully!";
    }
    @PostMapping("/internal/products")
    public String saveProduct(@RequestBody ProductDTO productDTO) {
        shopperService.saveProduct(productDTO);
        return "Product metadata saved successfully!";
    }
    @GetMapping("/products")
    public List<ShopperProductDTO> getShopperProducts(
            @RequestParam String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "10") int limit) {
        return shopperService.getShopperProducts(shopperId, category, brand, limit);
    }
}
