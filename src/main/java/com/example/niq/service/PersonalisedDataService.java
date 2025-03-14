package com.example.niq.service;

import com.example.niq.dto.ProductDTO;
import com.example.niq.dto.ShopperRequestDTO;
import com.example.niq.dto.ShopperProductDTO;

import java.util.List;

public interface PersonalisedDataService {
    void saveShopperData(ShopperRequestDTO shopperRequestDTO);
    void saveProduct(ProductDTO productRequestDTO);
    List<ShopperProductDTO> getShopperProducts(String shopperId, String category, String brand, int limit);
}
