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

    public Product getProductById(final Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(id);
    }

    public Product addProductToGroup(Long productId, Long groupId) throws GroupNotFoundException, ProductNotFoundException {
        Product product = getProductById(productId);
        Group group = groupService.getGroupById(groupId);
        group.getProducts().add(product);
        product.setGroup(group);
        saveProduct(product);
        groupService.saveGroup(group);
        return product;
    }

    public void removeProductFromGroup(Long productId) throws ProductNotFoundException, GroupNotFoundException {
        Product product = getProductById(productId);
        Group group = groupService.getGroupById(product.getGroup().getId());
        group.getProducts().remove(product);
        product.setGroup(null);
        groupService.saveGroup(group);
    }
}

