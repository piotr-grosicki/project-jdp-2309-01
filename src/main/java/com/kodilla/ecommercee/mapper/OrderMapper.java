package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.order.OrderDto;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderMapper {

    OrderService orderService;
    ProductRepository productRepository;

    public OrderDto mapToOrderDto(Order order) {
        List<Long> productList = order.getProducts()
                            .stream()
                            .map(Product::getId)
                            .toList();
        return new OrderDto(order.getId(),
                            order.getUser().getId(),
                            order.getOrderDate(),
                            order.getStatus(),
                            productList);
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return orders.stream()
                     .map(this::mapToOrderDto)
                     .toList();
    }

    public Order mapToOrder(OrderDto orderDto) {
        User user = orderService.getOrderById(orderDto.getId()).getUser();
        List<Product> products = orderService.getOrderById(orderDto.getId()).getProducts();
        return new Order(orderDto.getId(), user, orderDto.getOrderDate(), orderDto.getStatus(), products);
    }
}
