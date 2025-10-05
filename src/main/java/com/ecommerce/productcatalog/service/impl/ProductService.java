package com.ecommerce.productcatalog.service.impl;

import com.ecommerce.productcatalog.entity.Product;
import com.ecommerce.productcatalog.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepo productRepo;
    public List<Product> getAllProducts()
    {
       return productRepo.findAll();
    }


    public Product getProductById(int id)
    {
        return productRepo.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product); // This will update if ID exists
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    public void deleteProduct(int id)
    {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword)
    {
        return productRepo.searchProduct(keyword);
    }
}
