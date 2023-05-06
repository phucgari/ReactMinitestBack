package com.reactminitest.service;

import com.reactminitest.model.Category;
import com.reactminitest.model.Product;
import com.reactminitest.model.User;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product>{

    List<Product> complexSearch(Optional<String> name,
                                Optional<Integer> minPrice,
                                Optional<Integer> maxPrice,
                                Optional<List<Category>> categories);

    List<Product> getProductByOwner(User user);
    List<Product> complexSearch(Optional<String> name,
                                Optional<Integer> minPrice,
                                Optional<Integer> maxPrice,
                                Optional<List<Category>> categories,
                                User owner);
}
