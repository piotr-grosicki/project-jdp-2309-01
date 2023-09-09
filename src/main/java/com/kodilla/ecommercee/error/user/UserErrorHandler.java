package com.kodilla.ecommercee.error.user;

import com.kodilla.ecommercee.error.user.dto.UserNotFoundDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class UserErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserNotFoundDto handleUserNotFoundException(UserNotFoundException exception) {
        log.info("There is no USER with ID={}", exception.getId());
        return new UserNotFoundDto("Bad User ID requested", HttpStatus.BAD_REQUEST);
    }
}
