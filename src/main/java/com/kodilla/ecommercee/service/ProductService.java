package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.error.product.GroupNotFoundException;
import com.kodilla.ecommercee.error.product.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final GroupService groupService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setGroup(updatedProduct.getGroup());

        return saveProduct(existingProduct);
    }

    public Product addProductToGroup(Long productId, Long groupId) throws GroupNotFoundException {
        Product product = getProductById(productId);
        Group group = groupService.readGroupById(groupId);
        group.getProducts().add(product);
        product.setGroup(group);
        saveProduct(product);
        groupService.saveGroup(group);
        return product;
    }

    public void removeProductFromGroup(Long productId) throws GroupNotFoundException {
        Product product = getProductById(productId);
        Group group = groupService.readGroupById(product.getGroup().getId());
        group.getProducts().remove(product);
        product.setGroup(null);
        groupService.saveGroup(group);
    }
}
