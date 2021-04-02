package com.ex.prep.services;

import com.ex.prep.model.binding.ProductAddBindingModel;
import com.ex.prep.model.entity.Product;
import com.ex.prep.model.entity.enums.CategoryNames;
import com.ex.prep.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void save(ProductAddBindingModel productModel);
    BigDecimal productSum();
    List<ProductViewModel> findAllByCategory(CategoryNames names);
    void buyId(String id);
    void buyAll();
}
