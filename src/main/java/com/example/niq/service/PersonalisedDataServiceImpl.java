package com.example.niq.service;

import com.example.niq.dto.ProductDTO;
import com.example.niq.dto.ShopperRequestDTO;
import com.example.niq.dto.ShopperProductDTO;
import com.example.niq.dto.ShopperProductDTO;
import com.example.niq.exception.ResourceNotFoundException;
import com.example.niq.entity.Product;
import com.example.niq.entity.Shopper;
import com.example.niq.entity.ShopperProduct;
import com.example.niq.repo.ProductRepository;
import com.example.niq.repo.ShopperProductRepository;
import com.example.niq.repo.ShopperRepository;
import com.example.niq.service.PersonalisedDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalisedDataServiceImpl implements PersonalisedDataService {

    private final ShopperRepository shopperRepository;
    private final ShopperProductRepository shopperProductRepository;
    private final ProductRepository productRepository;

    public PersonalisedDataServiceImpl(ShopperRepository shopperRepository, 
                              ShopperProductRepository shopperProductRepository,
                              ProductRepository productRepository) {
        this.shopperRepository = shopperRepository;
        this.shopperProductRepository = shopperProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveShopperData(ShopperRequestDTO shopperRequestDTO) {
        Shopper shopper = new Shopper();
        shopper.setShopperId(shopperRequestDTO.getShopperId());

        List<ShopperProduct> shopperProducts = shopperRequestDTO.getShelf().stream().map(dto -> {
            ShopperProduct shopperProduct = new ShopperProduct();
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + dto.getProductId()));
            shopperProduct.setShopper(shopper);
            shopperProduct.setProduct(product);
            shopperProduct.setRelevancyScore(dto.getRelevancyScore());
            return shopperProduct;
        }).collect(Collectors.toList());

        shopper.setShelf(shopperProducts);
        shopperRepository.save(shopper);
    }

    @Override
    public void saveProduct(ProductDTO productRequestDTO) {
        Product product = new Product();
        product.setProductId(productRequestDTO.getProductId());
        product.setCategory(productRequestDTO.getCategory());
        product.setBrand(productRequestDTO.getBrand());

        productRepository.save(product);
    }

    @Override
    public List<ShopperProductDTO> getShopperProducts(String shopperId, String category, String brand, int limit) {
        List<ShopperProduct> shopperProducts;

        if (category != null) {
            shopperProducts = shopperProductRepository.findByShopperShopperIdAndProductCategory(shopperId, category);
        } else if (brand != null) {
            shopperProducts = shopperProductRepository.findByShopperShopperIdAndProductBrand(shopperId, brand);
        } else {
            shopperProducts = shopperProductRepository.findByShopperShopperId(shopperId);
        }

        return shopperProducts.stream().limit(limit).map(sp -> {
            ShopperProductDTO responseDTO = new ShopperProductDTO();
            responseDTO.setProductId(sp.getProduct().getProductId());
           // responseDTO.setCategory(sp.getProduct().getCategory());
           // responseDTO.setBrand(sp.getProduct().getBrand());
            responseDTO.setRelevancyScore(sp.getRelevancyScore());
            return responseDTO;
        }).collect(Collectors.toList());
    }

	
}

