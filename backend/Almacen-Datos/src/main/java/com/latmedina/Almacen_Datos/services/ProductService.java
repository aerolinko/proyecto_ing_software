package com.latmedina.Almacen_Datos.services;

import com.latmedina.Almacen_Datos.models.ProductModel;
import com.latmedina.Almacen_Datos.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ArrayList<ProductModel> getProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ArrayList<ProductModel> getProductsAllByAuthorId(Long author_id){
        return productRepository.findAllByAuthorId(author_id);
    }


    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public Optional<ProductModel> getById(Long id){
        return productRepository.findById(id);
    }

    public ProductModel updateById(ProductModel request, Long id){
        ProductModel product = productRepository.findById(id).get();

        product.setProduct_id(request.getProduct_id());
        product.setProduct_description(request.getProduct_description());
        product.setProduct_name(request.getProduct_name());
        return productRepository.save(product); // Save the updated user
    }

    public Boolean deleteProduct(Long id){
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
