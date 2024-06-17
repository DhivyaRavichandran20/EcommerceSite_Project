package com.SpringBoot.EcommerceSiteProject.Product.Service;

import com.SpringBoot.EcommerceSiteProject.DTO.ProductDTO;
import com.SpringBoot.EcommerceSiteProject.Model.Category;
import com.SpringBoot.EcommerceSiteProject.Model.Product;
import com.SpringBoot.EcommerceSiteProject.Model.User;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.CategoryRepository;
import com.SpringBoot.EcommerceSiteProject.Product.Repository.ProductRepository;
import com.SpringBoot.EcommerceSiteProject.User.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    public Product addProduct(ProductDTO productDTO) throws Exception {

        Product product = productDTO.getProduct();
        Category category = categoryRepository.findById(productDTO.getTempCategoryId()).orElse(new Category());
        product.setCategory(category);
        User user = userRepository.findById(productDTO.getTemporaryUserId()).orElse(new User());
        product.setUser(user);
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
            oldProduct.setGstPercentage(updatedProduct.getGstPercentage());

            Category category = categoryRepository.findById(updatedProduct.getTempCategoryId()).orElseThrow(() -> new Exception("Category not found"));
            oldProduct.setCategory(category);

            productRepository.save(oldProduct);
        }else{
            throw new Exception("Product Not Found");
        }

      //  Category category = categoryRepository.findById(updatedProduct.getTempCategoryId()).orElseThrow(() -> new Exception("Category not found"));
       // updatedProduct.setCategory(category);

    }
}







