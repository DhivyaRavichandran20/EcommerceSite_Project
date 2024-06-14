package com.SpringBoot.EcommerceSiteProject.Product.Controller;

import com.SpringBoot.EcommerceSiteProject.Common.ApiResponse;
import com.SpringBoot.EcommerceSiteProject.DTO.ProductDTO;
import com.SpringBoot.EcommerceSiteProject.Model.Address;
import com.SpringBoot.EcommerceSiteProject.Model.Category;
import com.SpringBoot.EcommerceSiteProject.Model.Product;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.CategoryRepository;
import com.SpringBoot.EcommerceSiteProject.Product.Service.CategoryService;
import com.SpringBoot.EcommerceSiteProject.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> product = productService.getAllProduct();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

   @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws Exception {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) throws Exception {
         productService.updateProduct(id,productDetails);
        return  ResponseEntity.ok("Product Updated Successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("delete success");
    }


    /*Optional<Product> product1 = productService.getProductById(id);
    if(product1.isPresent()){

        Product updatedProduct = product1.get();
        updatedProduct.setProduct_id();

    }
    }*/
}




