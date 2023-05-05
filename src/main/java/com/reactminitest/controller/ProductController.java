package com.reactminitest.controller;

import com.reactminitest.model.Category;
import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import com.reactminitest.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping("{owner}")
    public ResponseEntity<List<Product>> complexSearchOwner(@RequestParam Optional<String> name,
                                                            @RequestParam Optional<Integer> minPrice,
                                                            @RequestParam Optional<Integer> maxPrice,
                                                            @RequestParam List<Category> categories,
                                                            @PathVariable User owner){
        return ResponseEntity.ok(productService.complexSearch(name, minPrice, maxPrice, categories, owner));
    }
    @GetMapping()
    public ResponseEntity<List<Product>> complexSearchBuyer(@RequestParam Optional<String> name,
                                                            @RequestParam Optional<Integer> minPrice,
                                                            @RequestParam Optional<Integer> maxPrice,
                                                            @RequestParam List<Category> categories){
        return ResponseEntity.ok(productService.complexSearch(name,minPrice,maxPrice,categories));
    }
}
