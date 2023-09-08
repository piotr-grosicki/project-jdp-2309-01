package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    List<Product> findAllById(Long id);

    List<Product> findAllByGroup(Group group);
}