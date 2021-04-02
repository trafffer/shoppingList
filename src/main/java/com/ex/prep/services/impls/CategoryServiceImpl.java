package com.ex.prep.services.impls;

import com.ex.prep.model.entity.Category;
import com.ex.prep.model.entity.enums.CategoryNames;
import com.ex.prep.repositories.CategoryRepository;
import com.ex.prep.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public void initCategories() {
       if (repository.count()==0){
           Arrays.stream(CategoryNames.values()).forEach(v -> {
                       Category category = new Category(v, "Description of " + v.name());
                       repository.save(category);
                   }
                   );
       }
    }

    @Override
    public Category findByName(CategoryNames names) {
        return repository.findByName(names).orElse(null);
    }

}
