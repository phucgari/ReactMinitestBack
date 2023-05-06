package com.reactminitest.service;

import com.reactminitest.model.Category;
import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.util.List;

public class ProductSpecification {
    private ProductSpecification(){}
    static Specification<Product> findByNameLike(String name){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%"+name+"%"));
    }
    static Specification<Product> findWithPriceBetween(int minPrice,int maxPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"),minPrice,maxPrice));
    }
    static Specification<Product> findByCategory(List<Category> categories){
        return (root, query, criteriaBuilder) -> root.join("categories",JoinType.INNER).in(categories);
    }
    static Specification<Product> findByOwner(User owner){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner"),owner));
    }
}
