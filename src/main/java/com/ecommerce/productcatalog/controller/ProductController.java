package com.ecommerce.productcatalog.controller;

import com.ecommerce.productcatalog.entity.Product;
import com.ecommerce.productcatalog.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    private ProductService productService;

    //adding response entity to get data + status
    @GetMapping("/products")
    public ResponseEntity<List<Product>> fetchAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id)
    {
        Product product=productService.getProductById(id);
        if(product!=null)
        {
        return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile)
    {
        try {
            Product response_product = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(response_product,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
    {
        Product product = productService.getProductById(productId);
        if (product == null || product.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile)
    {

        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        // Update fields
        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setProductAvailable(product.isProductAvailable());
        existingProduct.setReleaseDate(product.getReleaseDate());

        // Update image if provided
        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
            try {
                existingProduct.setImageData(imageFile.getBytes());
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to read image", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        productService.saveProduct(existingProduct); // save the updated product
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product=productService.getProductById(id);
        if(product!=null)
        {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product Deleted Successfully !!!",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Product Not Found !!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(String keyword)
    {
        System.out.println("searching with ---->>>"+keyword);
     List<Product> products=productService.searchProducts(keyword);
     return new ResponseEntity<>(products,HttpStatus.OK);
    }


}
