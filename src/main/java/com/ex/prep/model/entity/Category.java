package com.ex.prep.model.entity;

import com.ex.prep.model.entity.enums.CategoryNames;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
       private CategoryNames name;
       private String description;

    public Category() {
    }

    public Category(CategoryNames name, String s) {
        this.name=name;
        this.description=s;
    }

    @Enumerated(EnumType.STRING)
    public CategoryNames getName() {
        return name;
    }

    public Category setName(CategoryNames name) {
        this.name = name;
        return this;
    }

    @Column(name = "description",columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
