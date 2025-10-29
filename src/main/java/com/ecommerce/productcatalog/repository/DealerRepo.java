package com.ecommerce.productcatalog.repository;

import com.ecommerce.productcatalog.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepo extends JpaRepository<Dealer,Integer>
{

    Optional<Dealer> findByEmail(String email);
}
