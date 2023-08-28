package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository <Order, Long> {


    Optional<Order> findById(@NotNull Long id);

     void deleteById(@NotNull Long id);


}
