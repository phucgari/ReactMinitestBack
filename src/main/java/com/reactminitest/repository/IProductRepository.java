package com.reactminitest.repository;

import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface IProductRepository extends JpaSpecificationExecutor<Product>, JpaRepository<Product,Long> {
    List<Product> getAllByOwner(@NotNull User owner);
}
