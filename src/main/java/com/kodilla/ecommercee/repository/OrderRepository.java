package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface OrderRepository extends CrudRepository <Order, Long> {


    Optional<Order> findById(Long id);

     void deleteById(Long id);


}
