package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.cart.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Cart, Long> {


    List<Cart> findAll();
    List<Cart> findAllByUser(User user);
}