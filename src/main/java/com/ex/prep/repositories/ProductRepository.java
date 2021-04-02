package com.ex.prep.repositories;

import com.ex.prep.model.entity.Product;
import com.ex.prep.model.entity.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findAllByCategory_Name(CategoryNames category_name);
    Optional<Product> findByName(String name);
    void deleteByName(String name);
    @Query("select SUM(p.price) FROM Product p")
    BigDecimal findTotalProductsSum();


}
