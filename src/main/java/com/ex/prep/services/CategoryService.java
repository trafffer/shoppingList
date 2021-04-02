package com.ex.prep.services;

import com.ex.prep.model.entity.Category;
import com.ex.prep.model.entity.enums.CategoryNames;

public interface CategoryService {
    void initCategories();
    Category findByName(CategoryNames names);
}
