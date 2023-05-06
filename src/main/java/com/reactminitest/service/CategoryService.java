package com.reactminitest.service;

import com.reactminitest.model.Category;
import com.reactminitest.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    ICategoryRepository repository;
    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
