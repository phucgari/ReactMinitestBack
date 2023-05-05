package com.reactminitest.service;

import com.reactminitest.model.Category;
import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import com.reactminitest.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> complexSearch(Optional<String> name,
                                       Optional<Integer> minPrice,
                                       Optional<Integer> maxPrice,
                                       List<Category> categories) {
        Specification<Product> byName=ProductSpecification.findByNameLike(name.orElse(""));
        Specification<Product> byPrice=ProductSpecification.findWithPriceBetween(minPrice.orElse(0), maxPrice.orElse(Integer.MAX_VALUE));
        Specification<Product> byCategory=ProductSpecification.findByCategory(categories);
        return repository.findAll(byName.and(byCategory.and(byPrice)));
    }

    @Override
    public List<Product> getProductByOwner(User user) {
        return repository.getAllByOwner(user);
    }

    @Override
    public List<Product> complexSearch(Optional<String> name, Optional<Integer> minPrice, Optional<Integer> maxPrice, List<Category> categories, User owner) {
        Specification<Product> byName=ProductSpecification.findByNameLike(name.orElse(""));
        Specification<Product> byPrice=ProductSpecification.findWithPriceBetween(minPrice.orElse(0), maxPrice.orElse(Integer.MAX_VALUE));
        Specification<Product> byCategory=ProductSpecification.findByCategory(categories);
        Specification<Product> byOwner=ProductSpecification.findByOwner(owner);
        return repository.findAll(byName.and(byCategory.and(byPrice.and(byOwner))));
    }
}
