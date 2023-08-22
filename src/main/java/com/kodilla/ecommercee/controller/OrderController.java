package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @GetMapping
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orders = new ArrayList<>();
        orders.add(new OrderDto(1L, null, new Date(), "In progress", new ArrayList<>()));
        orders.add(new OrderDto(2L, null, new Date(), "Shipped", new ArrayList<>()));

        return orders;
    }


    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {

    }

    @PutMapping("/{id}")
    public UserDto updateOrder(@PathVariable Long id, @RequestBody UserDto updatedOrder) {
        return updatedOrder;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){

}

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        if (id == 1)
            return new OrderDto(1L, null, new Date(), "In progress", new ArrayList<>());
        if (id == 2)
            return new OrderDto(2L, null, new Date(), "Shipped", new ArrayList<>());
        else {
            return null;
        }

    }
}
