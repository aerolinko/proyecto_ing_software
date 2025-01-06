package com.latmedina.Almacen_Datos.controllers;

import com.latmedina.Almacen_Datos.models.ProductModel;
import com.latmedina.Almacen_Datos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ArrayList<ProductModel> getProducts() {
        return this.productService.getProducts();
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody ProductModel product) {
        return this.productService.saveProduct(product);
    }

    @GetMapping(path = "/author/{author_id}")
    public ArrayList<ProductModel> getProductByAuthorId(@PathVariable("author_id") Long author_id){
        return this.productService.getProductsAllByAuthorId(author_id);
    }

    @GetMapping(path = "/{id}")
    public Optional<ProductModel> getProductById(@PathVariable("id") Long id) {
        return this.productService.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ProductModel updateProductById(@RequestBody ProductModel request, @PathVariable("id") Long id) {
        return this.productService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteProductById(@PathVariable("id") Long id) {
        boolean ok = this.productService.deleteProduct(id);
        if (ok) {
            return "Product with id " + id + "deleted Product";
        } else {
            return "Product with id " + id + " not deleted Product";
        }
    }

    @DeleteMapping(path = "/all/{id}")
    public String deleteAllProductByAuthorId(@PathVariable("id") Long id) {
        boolean ok = this.productService.deleteUserProducts(id);
        if (ok) {
            return "Products with AuthorId " + id + "deleted";
        } else {
            return "Product with AuthorId " + id + " not deleted";
        }
    }

}
