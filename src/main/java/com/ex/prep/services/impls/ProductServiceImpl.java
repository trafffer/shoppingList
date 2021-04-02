package com.ex.prep.services.impls;

import com.ex.prep.model.binding.ProductAddBindingModel;
import com.ex.prep.model.entity.Product;
import com.ex.prep.model.entity.enums.CategoryNames;
import com.ex.prep.model.view.ProductViewModel;
import com.ex.prep.repositories.ProductRepository;
import com.ex.prep.services.CategoryService;
import com.ex.prep.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper mapper;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository repository, CategoryService categoryService) {
        this.mapper = mapper;
        this.repository = repository;
        this.categoryService = categoryService;
    }


    @Override
    public void save(ProductAddBindingModel productModel) {
        Product product = mapper.map(productModel,Product.class);
        product.setCategory(categoryService.findByName(productModel.getCategory()));
        ifExist(product);
    }

    @Override
    public BigDecimal productSum() {
        return repository.findTotalProductsSum();
    }

    @Override
    public List<ProductViewModel> findAllByCategory(CategoryNames names) {
        List<Product>products=repository.findAllByCategory_Name(names);
        return viewModels(products);
    }

    @Override
    public void buyId(String id) {
        repository.deleteById(id);
    }

    @Override
    public void buyAll() {
        repository.deleteAll();
    }

    private void ifExist(Product product){
        Optional<Product> newProduct = repository.findByName(product.getName());
        if (newProduct.isEmpty()){
            this.repository.save(product);
        }
    }

    private List<ProductViewModel> viewModels(List<Product>products){
        return products.stream()
                .map(p-> mapper.map(p,ProductViewModel.class))
                .collect(Collectors.toList());
    }

}
