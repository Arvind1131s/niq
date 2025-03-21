package com.example.niq.controller;

import com.example.niq.dto.ProductDTO;
import com.example.niq.dto.ShopperRequestDTO;
import com.example.niq.service.PersonalisedDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ShopperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonalisedDataServiceImpl shopperService;

    @BeforeEach
    void setUp() {
        // Reset database before each test
    	ProductDTO productRequestDTO = new ProductDTO();
        productRequestDTO.setProductId("BB-2144746855");
        productRequestDTO.setCategory("Books");
        productRequestDTO.setBrand("Happy");
        shopperService.saveProduct(productRequestDTO);
    }

    // ===========================
    // Test: Save Product
    // ===========================
    @Test
    void testSaveProduct() throws Exception {
        mockMvc.perform(post("/api/internal/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\":\"MB-2093193395\",\"category\":\"Mobiles\",\"brand\":\"Samsung\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product metadata saved successfully!"));
    }

    
    @Test
    void testSaveShopper() throws Exception {
        mockMvc.perform(post("/api/internal/shoppers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shopperId\":\"S-1000\",\"shelf\":[{\"productId\":\"BB-2144746855\",\"relevancyScore\":55.16}]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Shopper data saved successfully!"));
    }

    @Test
    void testGetShopperProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                        .param("shopperId", "S-1000")
                        .param("limit", "5"))
                .andExpect(status().isOk());
    
    
    }
}
