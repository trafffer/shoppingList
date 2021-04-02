package com.ex.prep.repositories;

import com.ex.prep.model.entity.Category;
import com.ex.prep.model.entity.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category>findByName(CategoryNames name);
}
