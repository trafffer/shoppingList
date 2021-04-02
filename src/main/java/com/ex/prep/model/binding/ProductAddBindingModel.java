package com.ex.prep.model.binding;

import com.ex.prep.model.entity.Category;
import com.ex.prep.model.entity.enums.CategoryNames;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddBindingModel {
    @NotBlank(message = "Cannot be empty!")
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters!")
    private String name;

    @Size(min = 5,message = "Description length must be more than 5 characters!")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past!")
    private LocalDateTime neededBefore;

    @NotNull
    @DecimalMin(value = "0", message = "Price must be a positive number!")
    private BigDecimal price;

    @NotNull(message = "Category must be selected!")
    private CategoryNames category;


    public ProductAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ProductAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductAddBindingModel setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryNames getCategory() {
        return category;
    }

    public ProductAddBindingModel setCategory(CategoryNames category) {
        this.category = category;
        return this;
    }
}
