package com.example.niq.service;

import com.example.niq.dto.ProductDTO;
import com.example.niq.dto.ShopperProductDTO;
import com.example.niq.dto.ShopperRequestDTO;
import com.example.niq.dto.ShopperProductDTO;
import com.example.niq.exception.ResourceNotFoundException;
import com.example.niq.entity.Product;
import com.example.niq.entity.Shopper;
import com.example.niq.entity.ShopperProduct;
import com.example.niq.repo.ProductRepository;
import com.example.niq.repo.ShopperProductRepository;
import com.example.niq.repo.ShopperRepository;
import com.example.niq.service.PersonalisedDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopperServiceTest {

    @Mock
    private ShopperRepository shopperRepository;

    @Mock
    private ShopperProductRepository shopperProductRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PersonalisedDataServiceImpl shopperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ===========================
    // Test: saveProduct
    // ===========================
    @Test
    void testSaveProduct() {
        ProductDTO productRequestDTO = new ProductDTO();
        productRequestDTO.setProductId("BB-2144746855");
        productRequestDTO.setCategory("Books");
        productRequestDTO.setBrand("Happy");

        shopperService.saveProduct(productRequestDTO);

        verify(productRepository, times(1)).save(any(Product.class));
    }

   
    @Test
    void testSaveShopperData() {
        ShopperRequestDTO shopperRequestDTO = new ShopperRequestDTO();
        shopperRequestDTO.setShopperId("S-1000");

        ShopperProductDTO shopperProductDTO = new ShopperProductDTO();
        shopperProductDTO.setProductId("BB-2144746855");
        shopperProductDTO.setRelevancyScore(55.16);

        shopperRequestDTO.setShelf(List.of(shopperProductDTO));

        Product product = new Product();
        product.setProductId("BB-2144746855");
        product.setCategory("Books");
        product.setBrand("Happy");
        when(productRepository.findById("BB-2144746855")).thenReturn(Optional.of(product));

        shopperService.saveShopperData(shopperRequestDTO);

        verify(shopperRepository, times(1)).save(any(Shopper.class));
    }

    @Test
    void testSaveShopperData_ProductNotFound() {
        ShopperRequestDTO shopperRequestDTO = new ShopperRequestDTO();
        shopperRequestDTO.setShopperId("S-1000");

        ShopperProductDTO shopperProductDTO = new ShopperProductDTO();
        shopperProductDTO.setProductId("BB-999999");
        shopperProductDTO.setRelevancyScore(50.0);

        shopperRequestDTO.setShelf(List.of(shopperProductDTO));

        when(productRepository.findById("BB-999999")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> shopperService.saveShopperData(shopperRequestDTO));
    }

   
    @Test
    void testGetShopperProducts_Success() {
        Shopper shopper = new Shopper();
        shopper.setShopperId("S-1000");

        
        Product product = new Product();
        product.setProductId("BB-2144746855");
        product.setCategory("Babies");
        product.setBrand("Babyom");
        ShopperProduct shopperProduct = new ShopperProduct();
        shopperProduct.setShopper(shopper);
        shopperProduct.setProduct(product);
        shopperProduct.setRelevancyScore(55.16);

        when(shopperProductRepository.findByShopperShopperId("S-1000"))
                .thenReturn(List.of(shopperProduct));

        List<ShopperProductDTO> response = shopperService.getShopperProducts("S-1000", null, null, 5);

        assertEquals(1, response.size());
        assertEquals("BB-2144746855", response.get(0).getProductId());
    }

    @Test
    void testGetShopperProducts_NoResults() {
        when(shopperProductRepository.findByShopperShopperId("S-9999")).thenReturn(List.of());

        List<ShopperProductDTO> response = shopperService.getShopperProducts("S-9999", null, null, 5);

        assertTrue(response.isEmpty());
    }
}
