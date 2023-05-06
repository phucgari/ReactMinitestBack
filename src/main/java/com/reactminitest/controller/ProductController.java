package com.reactminitest.controller;

import com.reactminitest.model.Category;
import com.reactminitest.model.Product;
import com.reactminitest.model.User;
import com.reactminitest.service.ICategoryService;
import com.reactminitest.service.IProductService;
import com.reactminitest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    IUserService userService;
    @Autowired
    ICategoryService categoryService;
    @GetMapping("{owner}")
    public ResponseEntity<List<Product>> complexSearchOwner(@RequestParam Optional<String> name,
                                                            @RequestParam Optional<Integer> minPrice,
                                                            @RequestParam Optional<Integer> maxPrice,
                                                            @RequestParam Optional<List<Category>> categories,
                                                            @PathVariable User owner){
        List<Product> list = productService.complexSearch(name, minPrice, maxPrice, categories, owner);
        return ResponseEntity.ok(list);
    }
    @GetMapping()
    public ResponseEntity<List<Product>> complexSearchBuyer(@RequestParam Optional<String> name,
                                                            @RequestParam Optional<Integer> minPrice,
                                                            @RequestParam Optional<Integer> maxPrice,
                                                            @RequestParam Optional<List<Category>> categories){
        List<Product> list = productService.complexSearch(name, minPrice, maxPrice, categories);
        return ResponseEntity.ok(list);
    }
    @PostMapping("create")
    public ResponseEntity<String> createNewProduct(@RequestBody Product product){
        Optional<User> user = userService.getById(product.getOwner().getId());
        if(user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        product.setOwner(user.get());
        productService.save(product);
        return new ResponseEntity<>("created",HttpStatus.OK);
    }
    @GetMapping("categories")
    public ResponseEntity<List<Category>> getCategories(){
        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);
    }
}
