package com.example.niq.repo;

import com.example.niq.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopperRepository extends JpaRepository<Shopper, String> {
}
