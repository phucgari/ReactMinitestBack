package com.reactminitest.repository;

import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface IProductRepository extends JpaSpecificationExecutor<Product>, JpaRepository<Product,Long> {
    List<Product> getAllByOwner(@NotNull User owner);
    @EntityGraph(value = "product.categories" , type = EntityGraph.EntityGraphType.LOAD)
    List<Product> findAll(Specification<Product> spec);
}
