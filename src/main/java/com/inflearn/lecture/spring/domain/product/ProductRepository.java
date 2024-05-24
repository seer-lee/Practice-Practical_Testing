package com.inflearn.lecture.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * SELECT *
     * FROM product
     * WHERE selling_status IN ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);
    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    @Query(value = "SELECT p.product_number FROM product p ORDER BY p.id DESC LIMIT 1", nativeQuery = true)
    String findLatestProductNumber();
}
