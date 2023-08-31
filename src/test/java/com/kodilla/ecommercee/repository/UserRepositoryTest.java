package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Test
    void shouldReturnProperValues_givenUserEntity_whenFetchDataFromDb() {
        
        //given
        User user = User.builder()
                        .username("user")
                        .email("email")
                        .password("password")
                        .generatedKey("abcd")
                        .expirationDate(Date.valueOf(LocalDate.now().plusDays(1)))
                        .isBlocked(false)
                        .build();
        userRepository.save(user);
        Cart cart = Cart.builder()
                        .user(user)
                        .created(LocalDate.now())
                        .build();
        cartRepository.save(cart);
        user.setCarts(List.of(cart));
        Long userId = user.getId();
        Long cartId = cart.getId();
        
        //when
        List<User> resultList = userRepository.findAll();
        Optional<User> foundById = userRepository.findById(userId);
        
        //then
        assertEquals(1, resultList.size());
        assertTrue(foundById.isPresent());
        assertEquals(user, foundById.get());
        assertEquals(user.getCarts().get(0), foundById.get().getCarts().get(0));
        
        //cleanUp
        cartRepository.deleteById(cartId);
        userRepository.deleteById(userId);
    }
}