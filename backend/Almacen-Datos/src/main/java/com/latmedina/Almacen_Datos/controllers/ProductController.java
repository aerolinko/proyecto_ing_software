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
}
