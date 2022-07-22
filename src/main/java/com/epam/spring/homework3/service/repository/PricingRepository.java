package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.model.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingRepository extends JpaRepository<Pricing,Long> {
}
