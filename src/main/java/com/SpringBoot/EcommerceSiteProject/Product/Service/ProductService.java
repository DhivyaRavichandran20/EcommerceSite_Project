package com.SpringBoot.EcommerceSiteProject.Product.Service;

import com.SpringBoot.EcommerceSiteProject.Model.Category;
import com.SpringBoot.EcommerceSiteProject.Model.Product;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.CategoryRepository;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) throws Exception  {
        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new Exception("Category not found"));
        product.setCategory(category);

        Product products = new Product();
        products.setProduct_id(product.getProduct_id());
        products.setProduct_Name(product.getProduct_Name());
        products.setPrice(product.getPrice());
        products.setDescription(product.getDescription());
        products.setGst(product.getGst());
        products.setCategory(category);

        return productRepository.save(product);

    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Integer id, Product updatedProduct) throws Exception  {
       
        Optional<Product> maybeProduct = productRepository.findById(id);
        
        if(maybeProduct.isPresent()) {

            Product oldProduct = maybeProduct.get();
            oldProduct.setProduct_Name(updatedProduct.getProduct_Name());
            oldProduct.setDescription(updatedProduct.getDescription());
            oldProduct.setPrice(updatedProduct.getPrice());
            oldProduct.setGst(updatedProduct.getGst());
            //product.setCategory(category);
            productRepository.save(oldProduct);
        }else{
            throw new Exception("Address Not Found");
        }

        Category category = categoryRepository.findById(updatedProduct.getCategoryId()).orElseThrow(() -> new Exception("Category not found"));
        updatedProduct.setCategory(category);

    }
}







