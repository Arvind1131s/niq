package com.example.niq.repo;

import com.example.niq.entity.ShopperProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShopperProductRepository extends JpaRepository<ShopperProduct, Long> {
    List<ShopperProduct> findByShopperShopperId(String shopperId);

	List<ShopperProduct> findByShopperShopperIdAndProductCategory(String shopperId, String category);

	List<ShopperProduct> findByShopperShopperIdAndProductBrand(String shopperId, String brand);
}
